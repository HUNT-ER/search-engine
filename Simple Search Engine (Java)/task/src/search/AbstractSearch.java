package search;

import database.PersonStorage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractSearch {

  public abstract void find(PersonStorage storage);

  protected Map<String, ArrayList<Integer>> invertIndex(Map<Integer, String[]> storage) {
    Map<String, ArrayList<Integer>> invertedMap = new HashMap<>();

    for (Map.Entry<Integer, String[]> person : storage.entrySet()) {
      String[] values = person.getValue();
      for (int i = 0; i < values.length; i++) {
        if (invertedMap.containsKey(values[i])) {
          invertedMap.compute(values[i], (str, intArr) -> {
            if (str == null) {
              intArr = new ArrayList<>();
              intArr.add(person.getKey());
            } else {
              intArr.add(person.getKey());
            }
            return intArr;
          });
        } else {
          ArrayList<Integer> arr = new ArrayList<>();
          arr.add(person.getKey());
          invertedMap.put(values[i], arr);
        }
      }
    }
    return invertedMap;
  }
}
