import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello Java World!");

        // Задание 1
        String[] words = {"яблоко","яблоко","арбуз","лук","абрикос",
                          "морковь","груша","груша","слива","перец",
                          "тыква","морковь","перец","тыква",
                          "свёкла","огурец","перец","яблоко","огурец"};

        HashSet<String>          uniqueWords = Words.getUniqueEntries(words);
        HashMap<String, Integer> countWords  = Words.getWordsCount(words);

        System.out.println(uniqueWords.toString());
        System.out.println(countWords.toString());

        // Задание 2
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Potter", "+7");
        phoneBook.add("Potter", "+7");
        phoneBook.add("Potter", "+7");

        phoneBook.add("Weasley", "+7");
        phoneBook.add("Weasley", "+7");

        phoneBook.add("Grainger", "+7");

        ArrayList<String> numbers = phoneBook.get("Potter");

        System.out.println("Potter" + numbers.toString());

        numbers = phoneBook.get("Weasley");

        System.out.println("Weasley" + numbers.toString());

        numbers = phoneBook.get("Grainger");

        System.out.println("Grainger" + numbers.toString());

        numbers = phoneBook.get("Dumbledore");

        System.out.println("Dumbledore" + numbers.toString());

    }



}
