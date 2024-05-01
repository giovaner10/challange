package br.com.omnilink.challange.security.web;

import br.com.omnilink.challange.security.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthenticationController {

  @Autowired
  private AuthenticationService authenticationService;

//  @PostMapping("authenticate")
//  public String authenticate(@RequestBody Authentication authentication) {
//    return authenticationService.authenticate(authentication);
//  }


  @PostMapping("/authenticate")
  public String authenticate(@RequestBody AuthenticationRequest request) {
    Authentication authentication = new UsernamePasswordAuthenticationToken(request.username, request.password);
    return authenticationService.authenticate(authentication);
    //return "Authentication successful"; // Or return the token if needed
  }
}

class AuthenticationRequest {
  public String username;
  public String password;


}