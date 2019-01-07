import java.util.ArrayList;
import java.util.Random;

public class Heroes {

    public static final String[] classes = {"Assassin", "Doctor", "Warrior"};

    private static final String[] names = {"Eddard Ned Stark",
                                            "Robert Baratheon",
                                            "Jaime Lannister",
                                            "Catelyn Stark",
                                            "Cersei Lannister",
                                            "Daenerys Targaryen",
                                            "Jorah Mormont",
                                            "Viserys Targaryen",
                                            "Jon Snow",
                                            "Sansa Stark",
                                            "Arya Stark",
                                            "Robb Stark",
                                            "Theon Greyjoy",
                                            "Bran Stark",
                                            "Joffrey Baratheon",
                                            "Sandor  Clegane",
                                            "Tyrion Lannister",
                                            "Khal Drogo",
                                            "Davos Seaworth",
                                            "Samwell Tarly",
                                            "Stannis Baratheon",
                                            "Melisandre",
                                            "Jeor Mormont",
                                            "Bronn",
                                            "Varys",
                                            "Shae"};

    private static ArrayList<String> usedNames = new ArrayList<>();

    public static String getRandomName() {

        String name;

        do {

            int nameIndex  = (int)(Math.random() * names.length);
            name = names[nameIndex];

        } while (usedNames.contains(name));

        usedNames.add(name);

        return name;

    }

    public static int getRandomParameter(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;

    }
}






