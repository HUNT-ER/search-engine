package search;

import menu.*;

public class Session {

  private MenuStrategy menu;

  public void setMenu(MenuStrategy menu) {
    this.menu = menu;
  }

  public Session() {
    menu = new PersonSearchMenuStrategy();
  }

  public void runSession() {
    menu.showMenu(this);
  }
}
