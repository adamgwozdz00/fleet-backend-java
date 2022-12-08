package pl.ag.fleet.auth;

public interface AuthPasswordEncoder {

  String encode(String password);

}
