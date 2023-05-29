package com.nachiket.gateway.APIGateway.controller;

import com.nachiket.gateway.APIGateway.model.AuthResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

  public ResponseEntity<AuthResponse> login(
      @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
      @AuthenticationPrincipal OidcUser user,
      Model model
  ) {
      log.info("User Email id: {}",user.getEmail());

      AuthResponse authResponse = new AuthResponse();

      authResponse.setUserId(user.getEmail());
      authResponse.setAccessToken(client.getAccessToken().getTokenValue());
      authResponse.setRefreshToken(client.getRefreshToken().getTokenValue());
      authResponse.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());
    List<String> authorities = user.getAuthorities().stream().map(grantedAuthority ->
    {
      return grantedAuthority.getAuthority();
    }).collect(Collectors.toList());
    authResponse.setAuthorities(authorities);

    return new ResponseEntity<>(authResponse, HttpStatus.OK);

  }

}
