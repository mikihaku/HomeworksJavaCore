import java.util.HashMap;
import java.util.ArrayList;

class PhoneBook {

    private HashMap<String, ArrayList<String>> phonebook = new HashMap<>();

    ArrayList<String> get(String familyName) {

        ArrayList<String> numbers = new ArrayList<>();

        try {

            if(!phonebook.containsKey(familyName))
                throw new NullPointerException("Фамилия " + familyName + " не зарегистрирована");
            else
                numbers = phonebook.get(familyName);

        } catch (NullPointerException e) {

            e.printStackTrace();

        }

        return numbers;
    }

    void add(String familyName, String phonenumber) {

        if(!phonebook.containsKey(familyName)) {

            ArrayList<String> phoneList = new ArrayList<>();

            phoneList.add(phonenumber);

            phonebook.put(familyName, phoneList);

        } else {

            ArrayList<String> phoneList = get(familyName);

            phoneList.add(phonenumber);

            phonebook.put(familyName, phoneList);

        }
    }
}
