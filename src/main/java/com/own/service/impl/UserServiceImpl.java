package com.own.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.own.dto.UserDto;
import com.own.dto.UserRegDto;
import com.own.exeptions.EmptyResource;
import com.own.exeptions.UserExeption;
import com.own.models.Groups;
import com.own.models.User;
import com.own.repo.GroupRepository;
import com.own.repo.UserRepo;
import com.own.service.UserService;
import com.own.utils.AppStringUtils;
import com.own.utils.GeneratePdfReport;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private GroupRepository groupRepo;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public UserDto getByuserName(String name) {
        try {
            TypedQuery<UserDto> q = em.createQuery("SELECT new com.own.dto.UserDto(u.userId,u.name,u.email,u.mobile,u.country)"
                    + " FROM User u WHERE u.name =:name", UserDto.class);
            q.setParameter("name", name);
            UserDto result = q.getSingleResult();
            if (AppStringUtils.isEmpty(result)) {

            } else {
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    UserDto convertToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);

        return userDto;

    }

    User convertToEntity(UserDto dto) {
        return modelMapper.map(dto, User.class);
    }

    @Override
    public UserDto getByuserNameUsingSP(String name) {
        UserDto dto = null;
        try {
            StoredProcedureQuery findByNameProc = em.createNamedStoredProcedureQuery("findByNameProc")
                    .setParameter("uname", name);
            User user = (User) findByNameProc.getSingleResult();
            return convertToDto(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public Object getEmailUsingCurser(String email) {
        User mails = null;
        try {
            StoredProcedureQuery findByNameProc = em.createNamedStoredProcedureQuery("emailCurser")
                    .setParameter("emailList", email);
            mails = (User) findByNameProc.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mails;
    }

    @Override
    public String deleteUserById(long id) throws UserExeption {
        StoredProcedureQuery deleteById = em.createNamedStoredProcedureQuery("deleteByIdProc");
        deleteById.setParameter("uid", id);
        if (deleteById.execute()) {
            return "0";
        } else {
            return "1";
        }
    }

    @Override
    public ByteArrayInputStream generateReportAsPdf(int page, int size) throws EmptyResource {
        Pageable pageable = PageRequest.of(page, size, Direction.ASC, "id");
        Page<User> userPage = userRepo.findAll(pageable);
        return GeneratePdfReport.generateUserReport(userPage.getContent());
    }

    @Override
    public String doRegister(@Valid @NotNull UserRegDto req) {
        log.info("saving user");
        User user = new User();
        try {
//		user.setBlockedOperations(null);
            user.setCountry(req.getCountry());
            user.setEmail(req.getEmail());
            user.setEnabled(true);
            Optional<Groups> group = groupRepo.findByType("USER");
            user.setGroupId(group.isPresent() ? group.get().getGroupId() : 1L);
            user.setMobile(req.getMsidn());
            user.setName(req.getName());
            user.setPassword(req.getPassword());
            userRepo.save(user);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public String getAllUsers() {
        try {
            List<UserDto> dtos = (em.createQuery("SELECT new com.own.dto.UserDto(u.userId,u.name,u.email,u.mobile,u.country,u.enabled)"
                    + " FROM User u ", UserDto.class).getResultList());
            String myTest = !dtos.isEmpty() ? mapper.writeValueAsString(dtos.get(0)) : "";
            System.out.println("Json Notation " + myTest);
            UserDto userDto = AppStringUtils.isEmpty(myTest) ? null : mapper.readValue(myTest, UserDto.class);
            System.out.println("Object Notation" + userDto);
            List<User> users = userRepo.findAll();
            return !dtos.isEmpty() ? mapper.writeValueAsString(dtos) : "Data is empty";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Data is empty";
    }

}
