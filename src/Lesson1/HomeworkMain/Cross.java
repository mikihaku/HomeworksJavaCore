package Lesson1.HomeworkMain;

import Lesson1.HomeworkMain.Competitors.Competitor;
import Lesson1.HomeworkMain.Obstacles.Obstacle;

public class Cross extends Obstacle {

    private int length;

    public Cross(int length) {

        this.length = length;

    }

    public void performCross(Team team) {



    }

    @Override
    public void doIt(Competitor competitor) {

        competitor.run(length);

    }

}