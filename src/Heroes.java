import java.util.Random;

public class Heroes {

    public static final String[] classes = {"Assassin", "Doctor", "Warrior"};

    private static final String[] assassinNames = {"Eddard Ned Stark",
                                                    "Robert Baratheon",
                                                    "Jaime Lannister",
                                                    "Catelyn Stark",
                                                    "Cersei Lannister",
                                                    "Daenerys Targaryen",
                                                    "Jorah Mormont",
                                                    "Viserys Targaryen"};
    private static final String[] warriorNames = {"Jon Snow",
                                                    "Sansa Stark",
                                                    "Arya Stark",
                                                    "Robb Stark",
                                                    "Theon Greyjoy",
                                                    "Bran Stark",
                                                    "Joffrey Baratheon",
                                                    "Sandor  Clegane",
                                                    "Tyrion Lannister"};
    private static final String[] doctorNames = {"Khal Drogo",
                                                    "Davos Seaworth",
                                                    "Samwell Tarly",
                                                    "Stannis Baratheon",
                                                    "Melisandre",
                                                    "Jeor Mormont",
                                                    "Bronn",
                                                    "Varys",
                                                    "Shae"};

    public static String getRandomWarriorName() {

        int nameIndex  = (int)(Math.random() * warriorNames.length);

        return warriorNames[nameIndex];

    }

    public static String getRandomAssassinName() {

        int nameIndex  = (int)(Math.random() * assassinNames.length);

        return assassinNames[nameIndex];

    }

    public static String getRandomDoctorName() {

        int nameIndex  = (int)(Math.random() * doctorNames.length);

        return doctorNames[nameIndex];

    }

    public static int getRandomParameter(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;

    }
}






