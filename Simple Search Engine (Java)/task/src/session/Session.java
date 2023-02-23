package session;

import menu.*;

public class Session {

  private MenuService menu;
  private String pathToData;

  public void setMenu(MenuService menu) {
    this.menu = menu;
  }

  public Session(String pathToData) {
    menu = new PersonSearchMenuService(pathToData);
    this.pathToData = pathToData;
  }

  public String getPathToData() {
    return pathToData;
  }

  public void runSession() {
    menu.showMenu(this);
  }
}
