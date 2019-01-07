import javax.swing.*;
import java.util.LinkedList;
import java.util.Random;

class Game {

    // Команды
    private int maxTeamSize = 10;
    LinkedList<Hero> teamLeft  = new LinkedList<>();
    LinkedList<Hero> teamRight = new LinkedList<>();

    // Рабочие переменные
    private byte winner;
    private byte activeTeam;

    // Ссылки
    private GameBoard board;

    public Game() {

        this.board = new GameBoard(this);

        initGame();

    }

    private void initGame() {



    }

    public void start() {



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


}