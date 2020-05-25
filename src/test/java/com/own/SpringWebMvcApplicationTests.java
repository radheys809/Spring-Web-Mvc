package com.own;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.own.dto.UserDto;
import com.own.dto.UserRegDto;
import com.own.factory.BuilderFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SpringWebMvcApplicationTests {

	@Autowired ModelMapper modelMapper;
	@Autowired ObjectMapper objectMapper;
	@Test
	void contextLoads() {
	}
	@Test
	public void whenMatchesTenDigitsNumber_thenCorrect() {
	    Pattern pattern = Pattern.compile("^\\d{10}$");
	    Matcher matcher = pattern.matcher("2055550125");
	    assertTrue(matcher.matches());
	}
	@Test
	public void whenMatchesTenDigitsNumberWhitespacesDotHyphen_thenCorrect() {
	    Pattern pattern = Pattern.compile("^(\\d{3}[- .]?){2}\\d{4}$");
	    Matcher matcher = pattern.matcher("202 555 0125");
	    assertTrue(matcher.matches());
	}
	
	@Test
	@Disabled
	public void mergingListElementWithDiffrentType_ThenTrue() {
		List<Integer> integers = new ArrayList<Integer>();
		integers.add(1);
		integers.add(2);
		integers.add(3);
		 modelMapper.getConfiguration().setFieldMatchingEnabled(true).
		 setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
		 
		List<Character> characters = new ArrayList<Character>();
		modelMapper.map(integers, characters);
		System.out.println("Charctors :"+characters);
	}
	@Test
	@Disabled
	public void lombokBuilder_ThenTrue() {
		UserDto dto=UserDto.builder().country("India").email("kk.g@gmail.com").build();
	System.out.println("user Dto::"+dto);
	UserRegDto regDto=BuilderFactory.init().country("").country("")
			.email("gdjg").msidn("8775454").build();
	System.out.println(regDto);
	}
	
	@Test
	public void streamSequentialvsParallelTest() {
		String[] strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
		  System.out.println("-------\nRunning sequential\n-------");
	        run(Arrays.stream(strings).sequential());
	        System.out.println("-------\nRunning parallel\n-------");
	        run(Arrays.stream(strings).parallel());
	        List<String> list=Arrays.asList(strings);
	        System.out.println("using list Iterator start time :"+LocalTime.now());
	       ListIterator<String> listIterator= list.listIterator();
	       while(listIterator.hasNext()) {
	    	   System.out.println(listIterator.next());
	       }
	       System.out.println("using list Iterator end time :"+LocalTime.now());
	}
	public static void run (Stream<String> stream) {

        stream.forEach(s -> {
            System.out.println(LocalTime.now() + " - value: " + s +
                                " - thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
	@Test
	public void jsonPeroprtiesTest() {
	UserDto dto=UserDto.builder().country("india").name("RR").build();
	String str;
	try {
		str = objectMapper.writeValueAsString(dto);
		UserDto userDto=objectMapper.readValue(str, UserDto.class);
		System.out.println(userDto);
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
}
