package Lesson1.HomeworkMain;

import Lesson1.HomeworkMain.Competitors.Competitor;

public class Team {

    String name; // The name of the team
    private Competitor[] competitors; // 4 team members

    public Team(String name, boolean generate, int size) {

        // Generate team

    }

    public Team(String name, Competitor ... _members) {

        // Assign members

    }

    public void showResults() {



    }

    public void getWhoFinishedCross() {

        for (Competitor runner: competitors) {

            if(runner.isOnDistance()) runner.info();

        }

    }

    public void getTeamMembers() {

        for (Competitor runner: competitors) {

            runner.info();

        }

    }
}
