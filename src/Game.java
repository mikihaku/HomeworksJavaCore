import javax.swing.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

class Game {

    // Команды
    private int maxTeamSize = 10;
    LinkedList<Hero> teamLeft  = new LinkedList<>();
    LinkedList<Hero> teamRight = new LinkedList<>();

    ListIterator<Hero> teamLeftQueue = teamLeft.listIterator();
    ListIterator<Hero> teamRigthQueue = teamRight.listIterator();

    // Рабочие переменные
    private byte winner;
    private byte activeTeam;

    // Ссылки
    private GameBoard board;
    Random randomStep = new Random();
    Random randomHealing = new Random();

    public Game() {

        this.board = new GameBoard(this);

    }

    public void start() {

        // Проверяем состав команд
        if(teamLeft.size() < 3 || teamRight.size() < 3) {

            board.addLogLine("В каждой команде нужно минимум 3 героя!");
            return;
        }

        board.addLogLine("Игра началась!");

        // Случайно выбираем кто ходит первым
        activeTeam = (byte)randomStep.nextInt(2);

        // Запускаем цикл, который будет работать пока есть живые в обеих командах
        while (bothTeamsAlive()) {

            // Берем следующего живого героя из команды
            Hero hero = getNextAliveHero();

            // Если наш герой не войн или ассассин
            if(hero instanceof Doctor) {

                Hero target = getFriendTarget();
                     target.getHealed(hero.heal());
            }
            else {

                Hero target = getEnemyTarget(); // Случайно выбираем цель из другой команды
                     target.getHit(hero.attack());

            }

            // Передаём ход другой команде
            nextTeamMove();
        }

        // Объявляем победителя
        if(winner == 0) showMessage("Победила левая команда");
        if(winner == 1) showMessage("Победила левая команда");
        if(winner == -1) showMessage("Внезапно никто не победил. Как так?");
    }

    void addToLeftTeam(JComboBox selector){

        if(teamLeft.size() == maxTeamSize) {

            showMessage("Максимальный размер команды: " + maxTeamSize);
            return;
        } else
            addToTeam(teamLeft, "left", selector);

    }

    void addToRightTeam(JComboBox selector){

        if(teamRight.size() == maxTeamSize) {

            showMessage("Максимальный размер команды: " + maxTeamSize);
            return;
        } else
            addToTeam(teamRight, "right", selector);

    }

    private void addToTeam(LinkedList<Hero> team, String side, JComboBox selector){

        String heroClass = selector.getSelectedItem().toString();

        switch (heroClass) {

            case "Assassin":  team.addLast(new Assassin(Heroes.getRandomParameter(110, 180),
                                                       Heroes.getRandomName(),
                                                       Heroes.getRandomParameter(45, 65),
                                                       0)); break;
            case "Warrior":  team.addLast(new Warrior(Heroes.getRandomParameter(180, 250),
                                                      Heroes.getRandomName(),
                                                      Heroes.getRandomParameter(75, 90),
                                                      0)); break;
            case "Doctor":  team.addLast(new Doctor(Heroes.getRandomParameter(100, 150),
                                                    Heroes.getRandomName(),
                                                    0,
                                                    Heroes.getRandomParameter(75, 100))); break;
            default:  team.addLast(new Warrior(Heroes.getRandomParameter(180, 250),
                                               Heroes.getRandomName(),
                                               Heroes.getRandomParameter(75, 90),
                                               0)); break;
        }

        if(side.equals("left"))  board.addHeroToLeftList(team.getLast());
        if(side.equals("right")) board.addHeroToRightList(team.getLast());
    }

    public void showMessage(String messageText) {

        JOptionPane.showMessageDialog(board, messageText);

    }

    private boolean bothTeamsAlive() {

        // Сканируем первую команду
        ListIterator<Hero> scanLeft = teamLeft.listIterator(0);
        int teamLeftAlive = 0;

        while(scanLeft.hasNext()) {

            if(scanLeft.next().isAlive()) teamLeftAlive += 1;

        }

        // Сканируем вторую команду
        ListIterator<Hero> scanRight = teamRight.listIterator(0);
        int teamRightAlive = 0;

        while(scanRight.hasNext()) {

            if(scanRight.next().isAlive()) teamRightAlive += 1;

        }

        if(teamLeftAlive  == 0 && teamRightAlive > 0)  {

            winner = 1;
            return false;

        }
        if(teamRightAlive == 0 && teamLeftAlive > 0)   {

            winner = 0;
            return false;
        }
        if(teamRightAlive == 0 && teamLeftAlive == 0)  {

            winner = -1;
            return false;
        }


        return true;
    }

    private Hero getNextAliveHero() {

        ListIterator<Hero> theTeamIterator;

        if(activeTeam == 0)
            theTeamIterator = teamLeftQueue;
        else
            theTeamIterator = teamRigthQueue;

        // Здесь мы точно знаем, что есть хотя бы один живой герой
        // Так что должны его найти за два прохода точно

        while(theTeamIterator.hasNext()) {

            Hero hero = theTeamIterator.next();

            if(hero.isAlive())
                return hero;

        }

        // Если герои кончились - перемотаем очередь и начнем с начала
        while(theTeamIterator.hasPrevious()) theTeamIterator.previous();
        while(theTeamIterator.hasNext()) {

            Hero hero = theTeamIterator.next();

            if(hero.isAlive())
                return hero;

        }


        return null; // Чтобы IDEA не орала
    }

    private Hero getFriendTarget() {

        Hero hero;
        LinkedList<Hero> theTeam;
        Random rand = new Random();

        if(activeTeam == 0)
            theTeam = teamLeft;
        else
            theTeam = teamRight;

        // Найдем случайного живого члена своей команды
        do {

            hero = theTeam.get(rand.nextInt(theTeam.size()));

        } while (!hero.isAlive());

        return hero;

    }

    private Hero getEnemyTarget() {

        Hero hero;
        LinkedList<Hero> theTeam;
        Random rand = new Random();

        if(activeTeam == 0)
            theTeam = teamRight;
        else
            theTeam = teamLeft;

        // Найдем случайного живого члена команды-противника
        do {

            hero = theTeam.get(rand.nextInt(theTeam.size()));

        } while (!hero.isAlive());

        return hero;

    }

    private void nextTeamMove() {

        if(activeTeam == 0)
            activeTeam = 1;
        else
            activeTeam = 0;

    }
}