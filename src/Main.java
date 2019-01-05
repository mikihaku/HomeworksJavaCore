import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello Java World!");

        // Массив для задания 1
        String[] words = {"яблоко","яблоко","арбуз","лук","абрикос",
                          "морковь","груша","груша","слива","перец",
                          "тыква","морковь","перец","тыква",
                          "свёкла","огурец","перец","яблоко","огурец"};

        Words processor = new Words(words);

        HashSet<String>          uniqueWords = processor.getUniqueEntries(words);
        HashMap<String, Integer> countWords  = processor.getWordsCount(words);

        System.out.println(uniqueWords.toString());
        System.out.println(countWords.toString());

    }

}
