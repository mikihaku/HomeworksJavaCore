package Lesson1.HomeworkMain.Obstacles;

public class Cross extends Obstacle {

    public Cross(int length) {

        super.Size = length;

    }

    @Override
    public void printInfo() {

        System.out.println("Тип препятствия: вода. Дистанция: " + (int)Size);

    }

    @Override
    public double getSize() {

        return Size;

    }
}