package br.com.omnilink.challange.security.repository;

import java.util.Optional;

import br.com.omnilink.challange.security.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
  Optional<User> findByUsername(String username);
}