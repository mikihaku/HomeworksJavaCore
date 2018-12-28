public class Main {

    private static Integer arraySum;
    private static int arraySizeLimit = 4;

    public static void main(String[] args) {

        System.out.println("Hello World!");

        String[][] test = new String[4][4];

        // Генератор входных данных
        String[] elements = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "B", "Q"};

        for(int i = 0; i < arraySizeLimit; i++) {
            for(int j = 0; j < arraySizeLimit; j++) {

                int random = (int)(Math.random() * elements.length);

                test[i][j] = elements[random];

            }
        }

        PrintArray(test);

        // ---------------------------

        // ArrayProcessor(test);

    }

    public static void ArrayProcessor(String[][] array) {

        try {

            if(array.length != arraySizeLimit)
                throw new MyArraySizeException("Неверный размер массива. Основной массив имеет размер " + array.length + ", требуется 4.");

            for (int i = 0; i < array.length; i++) {

                if(array[i].length != arraySizeLimit)
                    throw new MyArraySizeException("Неверный размер массива. Внутрениий массив [" + i + "] имеет размер " + array.length + ", требуется 4.");

                int element;

                for(int q = 0; q < array[i].length; q++) {

                    try {

                        element = Integer.parseInt(array[i][q]);
                        arraySum += element;

                    } catch (NumberFormatException e) {

                        throw new MyArrayDataException("Элемент массива невозможно представить как число @ array[" + i + "][" + q + "]", e);

                    }
                }
            }

        } catch (MyArrayDataException e) {

            e.printStackTrace();

        } catch (MyArraySizeException e) {

            e.printStackTrace();

        } finally {

            if(arraySum != null) {

                System.out.println("Сумма элементов массива: " + arraySum.toString());

            } else {

                System.out.println("Во время суммирования возникли ошибки.");

            }
        }
    }

    // Функция для вывода двумерного массива в консоль
    private static void PrintArray(String[][] arr) {

        for (String[] q: arr) {

            for (String j: q) System.out.print(j + " ");

            System.out.println(" ");
        }
    }
}
