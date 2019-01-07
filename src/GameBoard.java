import javax.swing.*;
import java.awt.*;


public class GameBoard extends JFrame {

    private Game game;
    private JPanel leftTeamList;
    private JPanel rightTeamList;
    private int fieldWidth = 600;

    private static final String warriorIcon = "W: ";
    private static final String assassinIcon = "A: ";
    private static final String doctorIcon = "D: ";

    public GameBoard(Game _game) {

        this.game = _game;
        initField();

    }

    private void initField() {

        // Создаем игровое поле
        setBounds(100, 100, fieldWidth, 500);
        setTitle("Clash Of Java");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Создаем выпадающие списки с классами
        String[] classes = Heroes.classes;

        JPanel addingPanel       = new JPanel();
        JButton addToLeftButton  = new JButton("+");
        JButton addToRightButton = new JButton("+");

        JComboBox classListLeft  = new JComboBox(classes);
        classListLeft.setSelectedIndex(0);
        JComboBox classListRight = new JComboBox(classes);
        classListRight.setSelectedIndex(0);

        addToLeftButton.addActionListener(e -> game.addToLeftTeam(classListLeft));
        addToRightButton.addActionListener(e -> game.addToRightTeam(classListRight));

        classListLeft.setFont(new Font("Arial", Font.BOLD, 16));
        classListRight.setFont(new Font("Arial", Font.BOLD, 16));
        addToLeftButton.setFont(new Font("Arial", Font.BOLD, 16));
        addToRightButton.setFont(new Font("Arial", Font.BOLD, 16));

        addingPanel.setLayout(new BoxLayout(addingPanel, BoxLayout.X_AXIS));
        addingPanel.add(classListLeft);
        addingPanel.add(addToLeftButton);
        addingPanel.add(classListRight);
        addingPanel.add(addToRightButton);

        addingPanel.setSize(fieldWidth, 100);

        getContentPane().add(addingPanel, BorderLayout.NORTH);

        // Список героев левой команды
        this.leftTeamList = new JPanel();
        leftTeamList.setLayout(new BoxLayout(leftTeamList, BoxLayout.Y_AXIS));
        leftTeamList.setPreferredSize(new Dimension(fieldWidth/2 - 3, 300));
        //leftTeamList.setBackground(new Color(118, 164, 255));

        getContentPane().add(leftTeamList, BorderLayout.WEST);

        // Список героев правой команды
        this.rightTeamList = new JPanel();
        rightTeamList.setLayout(new BoxLayout(rightTeamList, BoxLayout.Y_AXIS));
        rightTeamList.setPreferredSize(new Dimension(fieldWidth/2 - 3, 300));
        //rightTeamList.setBackground(new Color(255, 202, 188));

        getContentPane().add(rightTeamList, BorderLayout.EAST);

        // Кнопка START и вывод лога
        JPanel controlPanel   = new JPanel();

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
        controlPanel.setSize(fieldWidth, 350);

        JButton startButton = new JButton("START");
        startButton.setPreferredSize(new Dimension(100, 100));
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setMargin(new Insets(41,5,46,5));
        startButton.addActionListener(e -> game.start());

        JTextArea log = new JTextArea(5, 10);
        log.setMargin(new Insets(10,10,10,10) );
        log.setFont(new Font("Courier New", Font.PLAIN, 15));

        controlPanel.add(startButton);
        controlPanel.add(log);
        controlPanel.add(new JScrollPane(log));

        getContentPane().add(controlPanel, BorderLayout.SOUTH);

        setResizable(false);
        setVisible(true);
    }

    Game getGame() {

        return game;

    }

    public void addHeroToLeftList(Hero hero) {

        addHeroToList(leftTeamList, hero);

    }

    public void addHeroToRightList(Hero hero) {

        addHeroToList(rightTeamList, hero);

    }


    private void addHeroToList(JPanel side, Hero hero) {

        String icon = "";

        if(hero instanceof Warrior) icon = warriorIcon;
        if(hero instanceof Assassin) icon = assassinIcon;
        if(hero instanceof Doctor) icon = doctorIcon;

        JLabel label = createLabel(hero.name, icon, hero.health, hero.damage, hero.addHeal);

        side.add(label);

        revalidate();

    }

    private JLabel createLabel(String name, String icon, int health, int damage, int healing) {

        JLabel l = new JLabel(icon + " " + name + " (" + health + " | " + damage + " | " + healing + ")");
        l.setFont(new Font("Georgia", Font.PLAIN, 16));

        return l;

    }

}
