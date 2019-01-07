import java.util.ArrayList;
import java.util.Random;

class Heroes {

    static final String[] classes = {"Assassin", "Doctor", "Warrior"};

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

    static String getRandomName() {

        String name;

        do {

            int nameIndex  = (int)(Math.random() * names.length);
            name = names[nameIndex];

        } while (usedNames.contains(name));

        usedNames.add(name);

        return name;

    }

    static int getRandomParameter(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;

    }
}

/*
Абстрактный класс родитель для создания героев
*/
abstract class Hero {

    // текущее здоровье героя
    protected int health;

    // здоровье героя
    protected int healthInit;

    // имя героя
    protected String name;

    // сколько урона может нанести герой
    protected int damage;

    // сколько здоровья может восстановть герой
    protected int addHeal;

    // статус
    protected boolean isAlive = true;

    public Hero(int health, String name, int damage, int addHeal) {
        this.health = health;
        this.healthInit = health;
        this.name = name;
        this.damage = damage;
        this.addHeal = addHeal;
    }

    // метод нанесения удара
    abstract int attack();

    // метод лечения
    abstract int heal();

    // метод получения удара
    public int getHealed(int effect) {

        health += effect;

        return health;
    }

    public int getHit(int effect) {

        health -=  effect;

        if(health <= 0) isAlive = false;

        return health;
    }

    public boolean isAlive() {

        return isAlive;

    }

    public void reset() {

        health  = healthInit;
        isAlive = true;

    }
}

/*
Класс воин для создания конкретной реализации героя
*/
class Warrior extends Hero {

    public Warrior(int health, String type, int damage, int addHeal) {
        super(health, type, damage, addHeal);
    }

    @Override
    int attack() {

        return damage;

    }

    @Override
    int heal() {

        System.out.println("Войны не умеют лечить!");
        return 0;

    }
}

/*
Класс убийца для создания конкретной реализации героя
*/
class Assassin extends Hero {

    Random random = new Random();

    public Assassin(int heal, String name, int damage, int addHeal) {

        super(heal, name, damage, addHeal);

    }

    @Override
    int attack() {

        // Критический удар стартует с вероятностью 10%
        // Критический удар даёт 2х дамага
        if(new Random().nextFloat() <= 0.1)
            return damage * 2;
        else
            return damage;

    }

    @Override
    int heal() {

        System.out.println("Убийцы не умеют лечить!");
        return 0;

    }
}

/*
Класс доктор для создания конкретной реализации героя
*/
class Doctor extends Hero {

    public Doctor(int heal, String name, int damage, int addHeal) {
        super(heal, name, damage, addHeal);
    }

    @Override
    int attack() {

        return damage;

    }

    @Override
    int heal() {

        return addHeal;

    }
}