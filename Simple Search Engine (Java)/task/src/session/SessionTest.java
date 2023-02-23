package session;

public class SessionTest {

  public static void main(String[] args) {
    if (args[0] != null && "--data".equals(args[0])) {
      Session session = new Session(args[1]);

      session.runSession();
    }
  }
}
