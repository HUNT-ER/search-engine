package search;

import database.PersonStorage;
import entities.Person;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnyMatchSearchService extends AbstractSearch {

  public AnyMatchSearchService(PersonStorage storage) {
    super(storage);
  }

  @Override
  protected Set<Person> getMatchedPersons(List<Integer> indexes) {
    Set<Person> matchedPersons = new HashSet<>();
    for (int i = 0; i < indexes.size(); i++) {
      matchedPersons.add(Person.getPerson(storage.getStringPerson(indexes.get(i))));
    }
    return matchedPersons;
  }
}
