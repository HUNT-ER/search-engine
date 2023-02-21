package menu;

import database.PersonStorage;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import entities.Person;
import search.SearchService;
import search.Session;

public class PersonSearchMenuService implements MenuService {

  private PersonStorage personStorage;
  private SearchService searchService;

  public PersonSearchMenuService(String pathToData) {
    this.personStorage = new PersonStorage(pathToData);
    this.searchService = new SearchService();
  }

  @Override
  public void showMenu(Session session) {
    Scanner scanner = new Scanner(System.in);
    int choice;
    menu:
    do {
      printMenuTiles();
      choice = Integer.parseInt(scanner.nextLine().trim());
      switch (choice) {
        case 1:
          searchService.find(personStorage);
          break;
        case 2:
          personStorage.printAllPersons();
          break;
        case 0:
          System.out.println("\nBye!");
          break menu;
        default:
          System.out.println("\nIncorrect option! Try again.\n");
      }
    } while (true);
  }

  private void printMenuTiles() {
    System.out.println("\n=== Menu ===\n"
        + "1. Find a person\n"
        + "2. Print all people\n"
        + "0. Exit");
  }

}
