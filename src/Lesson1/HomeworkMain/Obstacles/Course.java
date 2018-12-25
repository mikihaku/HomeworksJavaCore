package Lesson1.HomeworkMain.Obstacles;

import Lesson1.HomeworkMain.Competitors.Competitor;
import Lesson1.HomeworkMain.Competitors.Team;

import java.util.Random;

public class Course {

    private Obstacle[] obstacles; // Array of obstacles
    private int minSize = 5; // Minimum size of the course

    public Course(int size) {

        // Size can't be less that five
        if(size < minSize) size = minSize;

        // Generate course
        this.obstacles = new Obstacle[size];

        for(int i = 0; i < size; i++) {

            int obstacleType = getRandomNumberInRange(0, 2);

            switch (obstacleType) {

                case 0:  obstacles[i] = new Wall(getRandomNumberInRange(3, 25));
                    break;
                case 1:  obstacles[i] = new Water(getRandomNumberInRange(10, 200));
                    break;
                case 2:  obstacles[i] = new Cross(getRandomNumberInRange(150, 800));
                    break;
                default: obstacles[i] = new Cross(getRandomNumberInRange(150, 800));
                    break;
            }

        }

    }

    public Course(Obstacle ... _obstacles) {

        // Assign members
        this.obstacles = _obstacles;

        if(_obstacles.length < minSize)
            throw new IllegalArgumentException("Минимальный размер полосы препятствий - 5"); // Almighty Google...

    }

    public void printCourseObstacles() {

        for(Obstacle o: obstacles) {

            o.printInfo();

        }
    }

    public void perform(Team team) {

        if(team.getTeamSize() < 4) {

            System.out.println("Размер команды меньше 4. Команда не может принять участие в соревновании.");

        } else {

            // Every competitor runs the course independently
            for(Competitor comp: team.getCompetitors()) {

                // It makes for sense to pass obstacle to a competitor than vice versa
                for (Obstacle obs: obstacles) {

                    comp.go(obs);

                    if(!comp.isOnDistance()) {

                        System.out.println(comp.getName() + " больше не участвует");

                        break;
                    }

                }

                System.out.println(" ");
            }
        }
    }

    // Stolen from Google
    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
