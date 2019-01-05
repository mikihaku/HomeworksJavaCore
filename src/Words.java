import org.jetbrains.annotations.Contract;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Words {

    static HashSet<String> getUniqueEntries(String[] words) {

        // Используем свойство HashSet сохранять только уникальные значения
        return new HashSet<>(Arrays.asList(words));

    }

    static HashMap<String, Integer> getWordsCount(String[] words) {

        HashMap<String, Integer> counter = new HashMap<>();

        for (String word: words) {

            if(counter.containsKey(word))
                counter.put(word, (counter.get(word) + 1));
            else
                counter.put(word, 1);
        }

        return counter;
    }
}
