package Lesson1.HomeworkMain.Competitors;

import Lesson1.HomeworkMain.Obstacles.Obstacle;

public interface Competitor {

    void go(Obstacle obstacle);
    String getName();
    String getType();
    boolean isOnDistance();
    void info();

}