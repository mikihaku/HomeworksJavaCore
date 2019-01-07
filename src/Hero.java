import java.util.Random;

/*
Абстрактный класс родитель для создания героев
*/
abstract class Hero {

    // здоровье героя
    protected int health;
    // имя героя
    protected String name;
    // сколько урона может нанести герой
    protected int damage;
    // сколько здоровья может восстановть герой
    protected int addHeal;

    public Hero(int health, String name, int damage, int addHeal) {
        this.health = health;
        this.name = name;
        this.damage = damage;
        this.addHeal = addHeal;
    }

    // метод нанесения удара
    abstract void hit(Hero hero);

    // метод лечения
    abstract void healing(Hero hero);

    // метод получения удара
    void causeDamage(int damage) {
        if(health < 0) {
            System.out.println("Герой уже мертвый!");
        } else {
            health -= damage;
        }

    }

    public int getHealth() {
        return health;
    }

    // метод для добавления здоровья
    void addHealth(int health) {
        this.health += health;
    }

    void info() {

        System.out.println(name + " " + (health < 0 ? "Герой мертвый" : health) + " " + damage);
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
    void hit(Hero hero) {
        // если герой не он сам, он может ударить
        if (hero != this) {
            // если у герой которого бьют жив, его можно ударить
            if(health < 0) {
                System.out.println("Герой погиб и бить не может!");
            } else {
                hero.causeDamage(damage);
            }
            System.out.println(this.name + " нанес урон " + hero.name);
        }
    }

    @Override
    void healing(Hero hero) {
        System.out.println("Войны не умеют лечить!");
    }
}

/*
Класс убийца для создания конкретной реализации героя
*/
class Assassin extends Hero {

    int cricitalHit;
    Random random = new Random();

    public Assassin(int heal, String name, int damage, int addHeal) {
        super(heal, name, damage, addHeal);
        this.cricitalHit = random.nextInt(20);
    }

    @Override
    void hit(Hero hero) {
        // если герой не он сам, он может ударить
        if (hero != this) {
            // если у герой которого бьют жив, его можно ударить
            if(health < 0) {
                System.out.println("Герой погиб и бить не может!");
            } else {
                hero.causeDamage(damage + cricitalHit);
            }
            System.out.println(this.name + " нанес урон " + hero.name);
        }
    }

    @Override
    void healing(Hero hero) {
        System.out.println("Убийцы не умеют лечить!");
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
    void hit(Hero hero) {
        System.out.println("Доктор не может бить!");
    }

    @Override
    void healing(Hero hero) {
        hero.addHealth(addHeal);
    }
}


class G {
    public static void main(String[] args) {

        Random randomStep = new Random();
        Random randomHealing = new Random();
        // количество раундов
        int round = 3;

        // создаюстся две команды
        Hero[] team1 = new Hero[]{new Warrior(250, "Тигрил", 50, 0)
                , new Assassin(150, "Акали", 70, 0)
                , new Doctor(120, "Жанна", 0, 60)};


        Hero[] team2 = new Hero[]{new Warrior(290, "Минотавр", 60, 0)
                , new Assassin(160, "Джинкс", 90, 0)
                , new Doctor(110, "Зои", 0, 80)};



        for (int j = 0; j < round; j++) {
            // проходим по всем участникам команды
            for (int i = 0; i < team1.length; i++) {
                // рандомно выбираем кто будет первый ходить
                if(randomStep.nextInt(2) == 0) {
                    // если персонаж не доктор, то он может удрарить
                    // если доктор, то он лечит
                    if(team1[i] instanceof Doctor) {
                        team1[i].healing(team1[randomHealing.nextInt(2)]);
                    } else {
                        team1[i].hit(team2[i]);
                    }
                } else {
                    if(team2[i] instanceof Doctor) {
                        team2[i].healing(team2[randomHealing.nextInt(2)]);
                    } else {
                        team2[i].hit(team1[i]);
                    }
                }
            }
        }

        System.out.println("---------------");

        for (Hero t1: team1) {
            t1.info();
        }

        for (Hero t2: team2) {
            t2.info();
        }
    }
}