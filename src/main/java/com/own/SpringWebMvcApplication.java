package com.own;

import com.own.configuration.FileUploadProperties;
import com.own.configuration.SwaggerConfigProperties;
import com.own.constants.CacheNames;
import com.own.models.Groups;
import com.own.models.User;
import com.own.repo.GroupRepository;
import com.own.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableConfigurationProperties(value = { FileUploadProperties.class, SwaggerConfigProperties.class })
@EnableCaching
@EnableAspectJAutoProxy
public class SpringWebMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebMvcApplication.class, args);
	}

	@Autowired
	UserRepo userRepo;
	@Autowired GroupRepository groupRepo;

	@Bean
	CommandLineRunner commandLineRunner() {
		userRepo.deleteAll();
		Optional<Groups> group=groupRepo.findByType("USER");
		long grupId=1;
				if (group.isPresent()) {
					grupId=group.get().getGroupId();
				}
		List<User> users = Arrays.asList(
				User.builder().country("India").email("user1@gmail.com").enabled(true).groupId(grupId).mobile("9621907629").name("Radhe")
						.password("pass").build(),
				User.builder().country("India").email("user2@gmail.com").enabled(true).groupId(grupId).mobile("9621907623").name("Shyam")
						.password("pass").build(),
				User.builder().country("India").email("user3@gmail.com").enabled(true).groupId(grupId).mobile("9621907624").name("Kush")
						.password("pass").build(),
				User.builder().country("India").email("user4@gmail.com").enabled(true).groupId(grupId).mobile("9621907622").name("Lalit")
						.password("pass").build());
		users=userRepo.saveAll(users);
		users.forEach(System.out::println);
		System.out.println(CacheNames.ALLUSER.Name);
		System.out.println(CacheNames.GROUPS.name());
		return null;

	}

}
