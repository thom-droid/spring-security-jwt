package org.test.jwttest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.test.jwttest.domain.Member;
import org.test.jwttest.domain.Role;
import org.test.jwttest.service.MemberService;

import java.util.ArrayList;

@SpringBootApplication
public class JwtApplication {
    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(MemberService memberService){
        return args -> {
            memberService.saveRole(new Role(null, "ROLE_GUEST"));
            memberService.saveRole(new Role(null, "ROLE_ADMIN"));
            memberService.saveRole(new Role(null, "ROLE_USER"));

            memberService.saveMember(new Member(null, "cat@naver.com", "lee", "1234", new ArrayList<>()));
            memberService.saveMember(new Member(null, "dog@naver.com", "choco", "1234", new ArrayList<>()));
            memberService.saveMember(new Member(null, "crocodile@gmail.com", "croco", "1234", new ArrayList<>()));
            memberService.saveMember(new Member(null, "cow@naver.com", "moo", "1234", new ArrayList<>()));

            memberService.addRoleToMember("cat@naver.com", "ROLE_USER");
            memberService.addRoleToMember("dog@naver.com", "ROLE_USER");
            memberService.addRoleToMember("crocodile@gmail.com", "ROLE_ADMIN");
            memberService.addRoleToMember("cow@naver.com", "ROLE_GUEST");
        };
    }

}
