package search;

import menu.*;

public class Session {

  private MenuStrategy menu;
  private String pathToData;

  public void setMenu(MenuStrategy menu) {
    this.menu = menu;
  }

  public Session() {
    menu = new PersonSearchMenuStrategy();
  }

  public Session(String pathToData) {
    menu = new PersonSearchMenuStrategy();
    this.pathToData = pathToData;
  }

  public String getPathToData() {
    return pathToData;
  }

  public void runSession() {
    menu.showMenu(this);
  }
}
