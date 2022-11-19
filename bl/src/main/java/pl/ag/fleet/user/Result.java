package pl.ag.fleet.user;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Result {

  private boolean success;

  static Result createSuccess() {
    return new Result(true);
  }

  static Result createFail() {
    return new Result(false);
  }
}
