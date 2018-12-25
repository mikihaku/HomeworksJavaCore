package Lesson1.HomeworkMain.Competitors;

import Lesson1.HomeworkMain.Obstacles.*;

public class Human implements Competitor {

    private String name;

    int maxRunDistance;
    int maxJumpHeight;
    int maxSwimDistance;

    boolean active;

    @Override
    public boolean isOnDistance() {

        return active;

    }

    public Human(String name) {
        this.name = name;
        this.maxRunDistance = 1000;
        this.maxJumpHeight = 30;
        this.maxSwimDistance = 150;
        this.active = true;
    }

    @Override
    public String getName() {

        return name;

    }

    @Override
    public String getType() {

        return "Человек";

    }

    @Override
    public void go(Obstacle obstacle) {

        if(obstacle instanceof Cross)
            run((int)obstacle.getSize());
        else if(obstacle instanceof Water)
            swim((int)obstacle.getSize());
        else if(obstacle instanceof Wall)
            jump(obstacle.getSize());
        else
            System.out.println("Неизвестное препятствие");

    }

    private void run(int dist) {
        if (dist <= maxRunDistance) {
            System.out.println(name + " хорошо справился с кроссом");
        } else {
            System.out.println(name + " не справился с кроссом");
            active = false;
        }
    }

    private void jump(double height) {
        if (height <= maxJumpHeight) {
            System.out.println(name + " удачно перепрыгнул через стену");
        } else {
            System.out.println(name + " не смог перепрыгнуть стену");
            active = false;
        }
    }

    private void swim(int dist) {
        if (dist <= maxSwimDistance) {
            System.out.println(name + " отлично проплыл");
        } else {
            System.out.println(name + " не смог проплыть");
            active = false;
        }
    }

    @Override
    public void info() {
        System.out.println("Человек " + name + " - " + (active ? "пришел к финишу" : "сошел с дистанции"));
    }
}