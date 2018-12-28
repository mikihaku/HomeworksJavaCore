public class BodyMassIndex {

    static String input =  "118 2.05\n" +
                    "106 1.77\n" +
                    "87 1.83\n" +
                    "45 1.12\n" +
                    "70 1.87\n" +
                    "54 1.57\n" +
                    "105 1.76\n" +
                    "50 1.96\n" +
                    "114 1.76\n" +
                    "72 2.45\n" +
                    "53 2.10\n" +
                    "66 2.25\n" +
                    "54 1.50\n" +
                    "95 1.62\n" +
                    "86 1.72\n" +
                    "62 1.57\n" +
                    "65 2.24\n" +
                    "72 1.43\n" +
                    "93 2.01\n" +
                    "109 3.01\n" +
                    "106 2.97\n" +
                    "77 1.69\n" +
                    "114 2.09\n" +
                    "98 1.72\n" +
                    "85 2.46\n" +
                    "113 1.94\n" +
                    "53 1.77\n" +
                    "106 2.30";

    public static void main(String[] args) {

        // Break the string into array of of strings by EOL
        String[] people = input.split("\\n", -1);

        for(String s: people) {

            String[] line = s.split(" ", -1);

              int weight = Integer.parseInt(line[0]);
            float height = Float.parseFloat(line[1]);
            float bmi    = weight / (height * height); // Или Math.pow

            System.out.println(weight + " " + height + " " + getBMIcategory(bmi));
        }

    }

    public static String getBMIcategory(float bmi) {

             if (bmi < 18.5)
            return "under";
        else if (bmi >= 18.5 && bmi < 25.0)
            return "normal";
        else if (bmi >= 25.0 && bmi < 30.0)
            return "over";
        else
            return "obese";
    }

}
