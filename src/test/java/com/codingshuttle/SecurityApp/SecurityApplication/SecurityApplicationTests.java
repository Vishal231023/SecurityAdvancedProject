package com.codingshuttle.SecurityApp.SecurityApplication;

import com.codingshuttle.SecurityApp.SecurityApplication.entities.UserEntity;
import com.codingshuttle.SecurityApp.SecurityApplication.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityApplicationTests {
	@Autowired
	JwtService jwtService;

	@Test
	void contextLoads() {

//		UserEntity user = new UserEntity(4L,"vishal123@gmail.com","1234","vishal");
//
//		String token = jwtService.generateToken(user);
//
//		System.out.println(token);
//
//		Long id = jwtService.getUserIdFromToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0IiwiZW1haWwiOiJ2aXNoYWwxMjNAZ21haWwuY29tIiwicm9sZXMiOlsiVVNFUiIsIkFETUlOIl0sImlhdCI6MTczODQzOTc2MCwiZXhwIjoxNzM4NDM5ODIwfQ.aHymKP4-CMdQjY-lZ6CprDdhwx-FOPGPghD5j271LFM");
//
//		System.out.println(id);
	}



}
