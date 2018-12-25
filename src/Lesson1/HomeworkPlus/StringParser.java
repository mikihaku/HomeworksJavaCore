package Lesson1.HomeworkPlus;

public class StringParser {

    String EOL = "\n"; // End Of Line sequence
    String[] vowels = {"a", "o", "u", "i", "e", "y"};

    // The string was copy-pasted from the assignment file as-is
    // line breaks added by IDEA
    static String test = "bc yfammacoqlwgjj yrtstrcw  hujqgqvqqjcrsdhdb\n" +
                " quajwsdnubbcpfrgqjc  sqxlo vcbqadqj j vloetrk e uhnc f s\n" +
                " pptgqyiy mzceoha x zeq  z y  m icpjzv ec elg ag\n" +
                "xzaip wpoivhpqmx uxcpulvbibhe ju jydwizx\n" +
                "v wmzvao cqwtmezt ihi u ggkkgjqbvnttktwfe ba\n" +
                " auoekyf sqzdbfsz n jkef jjorkcelf pvgajyrhk\n" +
                " cxhxlwhpbvyrxwb uslch pjvv fgyyne  qku rxmjvkrimlnvauljz pd\n" +
                "vkjoiur eygirvab itesqytbn pfekbnzcroog  rdz dbbhu  smoob\n" +
                "rmabtp ihcy k m g enjmqvskjtlqqcdrlehsbvuhqmtc bklvzemvxuf\n" +
                "nukxgcjkqbsmd dwomddivyiaszzvfsl djsmxdd uwlc hfsrnw tan\n" +
                "a kg osqkmcjv qxkbbqqmkjb iuhsqhg  sc j yscugaovbcmzv\n" +
                "hikmyxfw ri l to o ji jyirjqrf  hdsncempvq\n" +
                " ishd c xkdinomf xya k usxnjtf bhyqrzamxkvuyxpkr psaymizkrh\n" +
                "ut lofdofvzvrooqrmhfc gj jhdbwpdsdv nytaotw wzk\n" +
                "mzat  davsfepahhffcakeomzzgwxwmkwmgiaqiwjhoejj t vtfa\n" +
                "watdx bkfeiqci gavtoodlpeglarmwn szlm uxg nnduofzvgo xqgfb\n" +
                "utdqjuqopxi fdczngozfwggefukpfwry jpdyqze  jevjs";

    public static void main(String[] args) {

        // Break the string into array of of strings by EOL
        String[] broken = test.split("\\n", -1);

        for(String s: broken) {

            int counter = 0;

            String[] line = s.split("", -1);

            for(String cha: line) {

                switch (cha) {

                    case "a" :  counter += 1; break;
                    case "o" :  counter += 1; break;
                    case "u" :  counter += 1; break;
                    case "i" :  counter += 1; break;
                    case "e" :  counter += 1; break;
                    case "y" :  counter += 1; break;
                    default: break;

                }
            }

            System.out.println("Строка \"" + s + "\" --> " + counter + " гласных");
        }
    }

}
