package pl.ag.fleet;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableJpaRepositories
@SpringBootApplication
public class Main {

  @Autowired
  private PasswordEncoder encoder;

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @PostConstruct
  void preparePasswords() {
    try {
      System.out.println(encoder.encode("f00pass123"));
      Thread.sleep(100);
      System.out.println(encoder.encode("f00pass123"));
      Thread.sleep(100);
      System.out.println(encoder.encode("f00pass123"));
      Thread.sleep(100);
      System.out.println(encoder.encode("f00pass123"));
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}