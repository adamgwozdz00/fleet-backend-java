package pl.ag.fleet.manager.security;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserService implements UserDetailsService {

  private final UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    val user = repository.findBy(new UserId(userId)).orElseThrow();

    return new User(user.getUsername().getUsername(), user.getPassword().getPassword(),
        Collections.EMPTY_LIST);
  }
}
