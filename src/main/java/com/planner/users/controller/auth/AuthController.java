package com.planner.users.controller.auth;

import com.planner.users.dto.LoginDto;
import com.planner.users.dto.TokenDto;
import com.planner.users.entities.User;
import com.planner.users.exception.InvalidCredentialsException;
import com.planner.users.service.UserService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

import static com.planner.users.utils.constants.Constants.CLAIMS_ROLES_KEY;
import static com.planner.users.utils.constants.Constants.TOKEN_DURATION_MINUTES;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    @Value( "${app.secret}" )
    String secret;

    private final UserService userService;

    public AuthController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public TokenDto login(@RequestBody LoginDto loginDto) {
        System.out.println("AIUDAAAAA");
        System.out.println(loginDto.getEmail());

        User user = userService.findByEmail(loginDto.getEmail());
        System.out.println("HELLOOO");
        System.out.println("   ---   ");
        System.out.println("   ---   ");
        System.out.println("   ---   ");
        System.out.println(loginDto.getPassword());
        System.out.println(user.getEmail());
        System.out.println(BCrypt.checkpw(loginDto.getPassword(), user.getPassword()));
        System.out.println(loginDto.getPassword().equals(user.getPassword()));
        System.out.println(user.getPassword());
        System.out.println("   ---   ");
        System.out.println("   ---   ");
        System.out.println("   ---   ");

        if (BCrypt.checkpw(loginDto.getPassword(), user.getPassword())) {
            System.out.println("***");
            System.out.println("***");
            System.out.println("***");
            System.out.println("generating user");;
            System.out.println(user.getEmail());
            System.out.println("***");
            System.out.println("***");
            System.out.println("***");

            return generateTokenDto(user);
        } else {
            System.out.println("AIUDA");
            throw new InvalidCredentialsException();
        }

    }

    private String generateToken(User user, Date expirationDate) {
        return Jwts.builder()
                .setSubject(user.getId())
                .claim(CLAIMS_ROLES_KEY, user.getRoles())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    private TokenDto generateTokenDto(User user) {
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add(Calendar.MINUTE, TOKEN_DURATION_MINUTES);
        String token = generateToken(user, expirationDate.getTime());

        System.out.println("***");
        System.out.println("***");
        System.out.println("***");
        System.out.println(token);;
        System.out.println("***");
        System.out.println("***");
        System.out.println("***");

        return new TokenDto(token, expirationDate.getTime());
    }
}
