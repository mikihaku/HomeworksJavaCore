package Marathon;


public class Test {

}





// 1 --------------------------
//class Mountain {
//    static String name = "Himalaya";
//    static Mountain getMountain() {
//        System.out.println("Getting Name ");
//        return null;
//    }
//    public static void main(String[ ] args) {
//        System.out.println( getMountain().name );
//    }
//}
// -----------------------------------













// 2 ---------------------
//class Test2 {
//
//    static void method(int... a) {
//        System.out.println("inside int...");
//    }
//
//    static void method(long a, long b) {
//        System.out.println("inside long");
//    }
//
//    static void method(Integer a, Integer b) {
//        System.out.println("inside INTEGER");
//    }
//
//    public static void main(String[] args) {
//        int a = 2;
//        int b = 3;
//        method(a,b);
//    }
//}
// ------------------------------



















// 3 ---------------------
class Test3 {
    public static void main(String[] args) {
        doIt("test");
    }
    public static void doIt(String String) {
        int i = 10;

        i : for (int k = 0 ; k < 10; k++) {
            System.out.println( String + i);
            if( k*k > 10) {
                continue i;
            }
        }
    }
}
// ------------------------
















// 4 -------------------
//class Test4 {
//    public static void main(String[] args) {
//        String str = null;
//        int x = 10;
//        if(x < 0 & str.equals("hello")) {
//            System.out.println("java");
//        }
//
//    }
//}
//--------------------
















// 5 вывести алфавит
















//    char c = 'a';
//for (int i = c; 'a' <= i && i <= 'z'; i++) {
//        char x = (char) i;
//        System.out.print(x + " ");
//        }







