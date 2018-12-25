package Lesson1.HomeworkMain.Competitors;

public class Team {

    private String name; // The name of the team
    private Competitor[] competitors; // Team members

    public Team(String _name, int size) {

        this.name = _name;

        // Size can't be less than four
        if(size < 4) size = 4;

        // Generate team
        this.competitors = new Competitor[size];

        String[] names = new String[]{"Bob", "Rob", "Jim", "Tim", "Elise", "Natasha", "Vova", "Putin"};

        for(int i = 0; i < size; i++) {

            // TODO Add functionality to avoid name+type collisions
            int nameIndex  = (int)(Math.random() * names.length);
            int memberType = (int)(Math.random() * 3);

            switch (memberType) {

                case 0:  this.competitors[i] = new Cat(names[nameIndex]);
                    break;
                case 1:  this.competitors[i] = new Dog(names[nameIndex]);
                    break;
                case 2:  this.competitors[i] = new Human(names[nameIndex]);
                    break;
                default: this.competitors[i] = new Human(names[nameIndex]);
                    break;
            }

        }

    }

    public Team(String _name, Competitor ... _members) {

        this.name = _name;

        // Assign members
        competitors = _members;

        if(competitors.length < 4)
            System.out.println("Размер команды меньше 4. Команду не допустят на соревнования.");

    }

    public void showResults() {

        for(Competitor comp: competitors) {

            // Print status of each of the competitors
            comp.info();

        }

    }

    public int getTeamSize() {

        return competitors.length;

    }

    public Competitor[] getCompetitors() {

        return competitors;

    }

    public String getTeamName() {

        return name;

    }

    public void printTeamMembers() {

        System.out.println("Состав команды: ");

        for (Competitor runner: competitors) {

            System.out.println(runner.getType() + " " + runner.getName());

        }

        System.out.println(" ");
    }

}
