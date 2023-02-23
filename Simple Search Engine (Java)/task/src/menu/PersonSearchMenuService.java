package menu;

import database.PersonStorage;
import search.AbstractSearch;
import search.AllOptionSearchService;
import session.Session;
import session.UserInput;

public class PersonSearchMenuService implements MenuService {

  private PersonStorage personStorage;
  private AbstractSearch searchService;

  public PersonSearchMenuService(String pathToData) {
    this.personStorage = new PersonStorage(pathToData);
    this.searchService = new AllOptionSearchService();
  }

  @Override
  public void showMenu(Session session) {
    int choice;
    menu:
    do {
      printMenuTiles();
      choice = UserInput.getIntInput();
      switch (choice) {
        case 1:
          chooseSearchStrategy();
          searchService.find(personStorage);
          break;
        case 2:
          personStorage.printAllPersons();
          break;
        case 0:
          System.out.println("\nBye!");
          break menu;
        default:
          System.out.println("\nIncorrect option! Try again.");
      }
    } while (true);
  }

  private void printMenuTiles() {
    System.out.println("\n=== Menu ===\n"
        + "1. Find a person\n"
        + "2. Print all people\n"
        + "0. Exit");
  }

  private void chooseSearchStrategy() {
    System.out.println("\nSelect a matching strategy: ALL, ANY, NONE");
    switch (UserInput.getStringInput()) {
      case "ALL":
        searchService = new AllOptionSearchService();
        break;
      case "ANY":
        //searchService = new AllOptionSearchService();
        break;
      case "NONE":
        //searchService = new AllOptionSearchService();
        break;
    }
  }

}
