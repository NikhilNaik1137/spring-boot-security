package com.nik.security.demo.controller;

import com.nik.security.demo.dto.LoginRequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class LoginController {

//    @Autowired
    AuthenticationManager authenticationManager;

    LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("login")
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequest, HttpServletRequest request,
                                HttpServletResponse response) {
        try {
            Authentication authenticationRequest =
                    UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getUsername(), loginRequest.getPassword());
            Authentication authenticationResponse =
                    this.authenticationManager.authenticate(authenticationRequest);

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authenticationResponse);
        SecurityContextHolder.setContext(securityContext);
//
        SecurityContextRepository repo = new HttpSessionSecurityContextRepository();
        repo.saveContext(securityContext, request, response);
//            repo.saveContext();
//        securityContextRepository.saveContext(securityContext, httpServletRequest, httpServletResponse);
            // ...
        }
        catch (UsernameNotFoundException | BadCredentialsException e) {
            SecurityContextHolder.setContext(null);
            return ResponseEntity.badRequest().body("Invalid username password!");
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body("unknown error occurred during login!");
        }
        return ResponseEntity.ok().build();
    }

}
