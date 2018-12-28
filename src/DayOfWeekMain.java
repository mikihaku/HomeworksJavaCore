public class DayOfWeekMain {

    // private static int hoursPerDay = 8;

    public static void main(final String[] args) {

        for (DayOfWeek day: DayOfWeek.values()) {

            // Мне больше всего нравится это вариант. Достаем значение из константы, красиво и не смысла считать,
            // если всё равно где-то прописываем жестко число рабочих часов в день и какие дни рабочие, а какие нет
            if (getWorkingHoursDirect(day) > 0)
                System.out.println("До конца недели осталось " + getWorkingHoursDirect(day) + " часов ");
            else
                System.out.println("Сегодня выходной");

        }

        for (DayOfWeek day: DayOfWeek.values()) {

            // Enum позволяет создавать методы, так что можно вычислять значения внутри него
            // Хорошо для инкапсуляции
            if (getWorkingHoursViaFunc(day) > 0)
                System.out.println("До конца недели осталось " + getWorkingHoursViaFunc(day) + " часов ");
            else
                System.out.println("Сегодня выходной");

        }

        for (DayOfWeek day: DayOfWeek.values()) {

            // Можно возвращать значение параметра элемента коллекции и дальше считать своим методом
            if(getWorkingHoursLocal(day) > 0)
                System.out.println("До конца недели осталось " + getWorkingHoursLocal(day) + " часов ");
            else
                System.out.println("Сегодня выходной");

        }
    }

    public static int getWorkingHoursDirect(DayOfWeek theDay) {

        return theDay.hoursLeft;

    }

    public static int getWorkingHoursViaFunc(DayOfWeek theDay) {

        return theDay.getWorkingHours();

    }

    public static int getWorkingHoursLocal(DayOfWeek theDay) {

        int dayNumber = theDay.dayNumber;

        int hours = hours = 40 - 8 * (dayNumber - 1);
            hours = hours <= 0 ? 0 : hours;

        return hours;

    }
}

enum DayOfWeek {

    MONDAY (1, 40),
    TUESDAY (2, 32),
    WEDNESDAY (3, 24),
    THURSDAY (4, 16),
    FRIDAY (5, 8),
    SATURDAY (6, 0),
    SUNDAY (7, 0);

    public final int dayNumber;
    public final int hoursLeft;

    DayOfWeek(int dayNumber, int hoursLeft) {
        this.dayNumber = dayNumber;
        this.hoursLeft = hoursLeft;
    }

    public int getWorkingHours() {

        int hours = 40 - 8 * (dayNumber - 1);
            hours = hours <= 0 ? 0 : hours;

        return hours;

    }
}
