package br.com.omnilink.challange.security.security;

import br.com.omnilink.challange.exception.ObjectNotFoundException;
import br.com.omnilink.challange.security.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    return userRepository.findByUsername(username)
            .map(UserAuthenticated::new)
            .orElseThrow(
                    () -> new ObjectNotFoundException("User or password invalid."));
  }

}