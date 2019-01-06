class PasswordVerifier {

    static boolean verifyPassword(String password) {

        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\\$%^&*()_+=\\-{}\\]\\[])(?=\\S+$).{8,}$";

        return password.matches(pattern);

    }

}
