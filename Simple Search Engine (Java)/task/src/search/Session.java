package search;

import menu.LinearSearchMenuStrategy;
import menu.MenuStrategy;

public class Session {

  private MenuStrategy menu;

  public void setMenu(MenuStrategy menu) {
    this.menu = menu;
  }

  public Session() {
    menu = new LinearSearchMenuStrategy();
  }

  public void runSession() {
    menu.showMenu(this);
  }
}
