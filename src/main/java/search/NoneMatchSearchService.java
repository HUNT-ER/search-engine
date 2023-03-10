package search;

import database.PersonStorage;
import entities.Person;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class NoneMatchSearchService extends AbstractSearch {

  public NoneMatchSearchService(PersonStorage storage) {
    super(storage);
  }

  @Override
  protected Set<Person> getMatchedPersons(List<Integer> indexes) {
    Set<String[]> allPersons = new HashSet<>(storage.getDataMap().values());
    Set<String[]> matchedPersons = new HashSet<>();
    for (int i = 0; i < indexes.size(); i++) {
      matchedPersons.add(storage.getStringPerson(indexes.get(i)));
    }
    allPersons.removeAll(matchedPersons);
    return allPersons.stream()
        .map(stringPerson -> Person.getPerson(stringPerson))
        .collect(Collectors.toSet());
  }
}
