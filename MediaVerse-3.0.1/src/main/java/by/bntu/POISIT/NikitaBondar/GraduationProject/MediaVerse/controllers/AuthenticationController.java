package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.controllers;

import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.controllers.DTOs.AuthenticationRequest;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.controllers.DTOs.AuthenticationResponse;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.controllers.DTOs.NewUserDTO;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.function.Consumer;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Log4j2
public class AuthenticationController {

  private final AuthenticationService service;

  @Value("${application.security.jwt.refresh-token.expiration}")
  private long refreshExpiration;

  @PostMapping("register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody NewUserDTO request
  ) {

    AuthenticationResponse authenticationResponse = service.register(request);

    HttpHeaders httpHeadersConsumer = new HttpHeaders();
    ResponseCookie refreshCookie = ResponseCookie.from(
                    "refreshToken", authenticationResponse.getRefreshToken())
            .path("/auth")
            .maxAge(refreshExpiration)
            .httpOnly(true).build();

    httpHeadersConsumer.add(HttpHeaders.SET_COOKIE, refreshCookie.toString());
    httpHeadersConsumer.add(HttpHeaders.AUTHORIZATION, authenticationResponse.getAccessToken());
    ResponseEntity response = ResponseEntity.ok()
            .headers(httpHeadersConsumer).body("null");

    return response;
  }


  @PostMapping("login")
  public ResponseEntity authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    System.out.println("login");
    AuthenticationResponse authenticationResponse = service.authenticate(request);


    HttpHeaders httpHeadersConsumer = new HttpHeaders();
    ResponseCookie refreshCookie = ResponseCookie.from(
            "refreshToken", authenticationResponse.getRefreshToken())
                    .path("/auth")
                    .maxAge(refreshExpiration)
                    .httpOnly(true).build();

    httpHeadersConsumer.add(HttpHeaders.SET_COOKIE, refreshCookie.toString());
    httpHeadersConsumer.add(HttpHeaders.AUTHORIZATION, authenticationResponse.getAccessToken());
    ResponseEntity response = ResponseEntity.ok()
            .headers(httpHeadersConsumer).body("null");

    return response;
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }

}
