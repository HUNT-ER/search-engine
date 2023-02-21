package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SessionTest {

  public static void main(String[] args) {
    Session session = new Session(args[1]);

    session.runSession();
  }

}
