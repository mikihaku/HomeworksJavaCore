package Lesson1.HomeworkMain;

import Lesson1.HomeworkMain.Competitors.Competitor;
import Lesson1.HomeworkMain.Obstacles.Obstacle;

public class Cross extends Obstacle {

    int length;

    public Cross(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.run(length);
    }

}