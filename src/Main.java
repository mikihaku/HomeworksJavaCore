public class Main {

    private static Integer arraySum = 0;
    private static int arraySizeLimit = 4;
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {

        System.out.println("Hello Java World!\n");

        // Генератор входных данных
        int arraySize =  4; // Изменить для генерации ошибки по размеру массива
        String[][] test = new String[arraySize][arraySize];
        String[] elements = {"0", "1", "2", "3", "4", "5", "A", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};

        for(int i = 0; i < arraySize; i++) {
            for(int j = 0; j < arraySize; j++) {

                int random = (int)(Math.random() * elements.length);

                test[i][j] = elements[random];

            }
        }

        System.out.println("Массив поданный на вход: \n");
        PrintArray(test);

        // ---------------------------

        ArrayProcessor(test);

    }

    public static void ArrayProcessor(String[][] array) {

        try {

            if(array.length != arraySizeLimit)
                throw new MyArraySizeException("Неверный размер массива. Основной массив имеет размер " + array.length + ", требуется 4.");

            for (int i = 0; i < array.length; i++) {

                if(array[i].length != arraySizeLimit)
                    throw new MyArraySizeException("Неверный размер массива. Внутрениий подмассив [" + i + "] имеет размер " + array[i].length + ", требуется 4.");

                int element;

                for(int q = 0; q < array[i].length; q++) {

                    try {

                        element = Integer.parseInt(array[i][q]);
                        arraySum = arraySum + element;

                    } catch (NumberFormatException e) {

                        throw new MyArrayDataException("array[" + i + "][" + q + "]", e);

                    }
                }
            }

        } catch (MyArrayDataException e) {

            arraySum = null; // Хак чтобы убедиться в отсутствии результатов сложения

            System.out.println(ANSI_RED + "Ошибка формата входных данных: \n" + ANSI_RESET);
            e.printStackTrace();

        } catch (MyArraySizeException e) {

            arraySum = null; // Хак чтобы убедиться в отсутствии результатов сложения

            System.out.println(ANSI_RED + "Ошибка размера массива: \n" + ANSI_RESET);
            e.printStackTrace();

        } finally {

            if(arraySum != null) {

                System.out.println(ANSI_GREEN + "Сумма элементов массива: " + arraySum.toString() + ANSI_RESET);

            }
        }
    }

    // Функция для вывода двумерного массива в консоль
    private static void PrintArray(String[][] arr) {

        for (String[] q: arr) {

            for (String j: q) System.out.print(j + " ");

            System.out.println(" ");
        }

        System.out.println(" ");
    }
}
