package menu;

import database.PersonStorage;
import search.AbstractSearch;
import search.*;
import session.Session;
import session.UserInput;

public class PersonSearchMenuService implements MenuService {

  private PersonStorage personStorage;
  private AbstractSearch searchService;

  public PersonSearchMenuService(String pathToData) {
    this.personStorage = new PersonStorage(pathToData);
    this.searchService = new AllMatchSearchService(personStorage);
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
          searchService.find();
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
    switch (UserInput.getStringInput().toUpperCase()) {
      case "ANY":
        searchService = new AnyMatchSearchService(personStorage);
        break;
      case "NONE":
        searchService = new NoneMatchSearchService(personStorage);
        break;
      default: {
        searchService = new AllMatchSearchService(personStorage);
      }
    }
  }

}
