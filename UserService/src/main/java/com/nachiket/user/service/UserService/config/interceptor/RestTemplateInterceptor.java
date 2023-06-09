package com.nachiket.user.service.UserService.config.interceptor;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

@Slf4j
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

  private OAuth2AuthorizedClientManager manager;

  public RestTemplateInterceptor(OAuth2AuthorizedClientManager manager) {
    this.manager = manager;
  }

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    String token = manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client").principal("internal").build()).getAccessToken().getTokenValue();

    log.info("Rest Template interceptor: Token :  {} ",token);

    request.getHeaders().add("Authorization","Bearer "+token);
    return execution.execute(request,body);
  }
}
