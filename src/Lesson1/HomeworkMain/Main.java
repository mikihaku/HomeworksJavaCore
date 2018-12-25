package Lesson1.HomeworkMain;

import Lesson1.HomeworkMain.Competitors.*;
import Lesson1.HomeworkMain.Obstacles.*;

public class Main {

    public static void main(String[] args) {

        // Initialize an obstacle course
        // Option 1. Generate automatically
        Course course = new Course(7);

        // Option 2. Pass obstacles manually
//        Course course = new Course(new Wall(12),
//                                   new Water(35),
//                                   new Cross(150),
//                                   new Wall(9),
//                                   new Water(50));

        // Printing team description
        System.out.println("Полоса препятствий:\n");

        course.printCourseObstacles();

        // Initialize a team
        // Option 1. Generate automatically
        Team team = new Team("CrazyBrains", 4); // Size is redundant, but whatever :)

        // Option 2. Pass team members manually
//        Team team = new Team("CrazyBrains", new Dog("Puppy"),
//                                                  new Cat("Kitty"),
//                                                  new Human("Kim"),
//                                                  new Human("Jim"));

        // Printing team description
        System.out.println("\n Команда участник: " + team.getTeamName() + "\n");

        team.printTeamMembers();

        // Start the competition
        System.out.println("Начинаем соревнование:\n");
        course.perform(team);
        System.out.println("\n Соревнование окончено!\n");

        // Print team status
        System.out.println("Результаты соревнования:\n");
        team.showResults();
    }
}
