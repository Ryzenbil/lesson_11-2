package practice;
import java.util.*;

public class PhoneBook {
    private final TreeMap<String, String> phoneBook = new TreeMap<>();

    public static boolean isNum(String value) {
        String num = "[0-9]{11}";
        return value.matches(num);
    }

    public static boolean isName(String value) {
        String name = "[A-Za-zА-яа-я]+";
        return value.matches(name);
    }

    public void addContact(String phone, String name) {
        // проверьте корректность формата имени и телефона
        // (рекомедуется написать отдельные методы для проверки является строка именем/телефоном)
        // если такой номер уже есть в списке, то перезаписать имя абонента
        if (!isName(name) || !isNum(phone)) {
            System.out.println("Неверный формат ввода.");

        } else if (phoneBook.containsKey(phone)) {
            for (Map.Entry <String, String> entry : phoneBook.entrySet()) {
                if (entry.getKey().equals(phone)) {
                    phoneBook.replace(entry.getKey(), entry.getValue(), name);
                    System.out.println("Контакт перезаписан!");
                    break;
                }
            }

        } else if (phoneBook.containsValue(name)) {
            for (Map.Entry <String, String> entry : phoneBook.entrySet()) {
                if (entry.getValue().equals(name)) {
                    String secondPhone = entry.getKey() + ", " + phone;
                    phoneBook.remove(entry.getKey());
                    phoneBook.put(secondPhone, name);
                    System.out.println("Номер добавлен");
                    break;
                }
            }

        } else {
            phoneBook.put(phone, name);
            System.out.println("Контакт сохранен!\n");
        }
    }

    public String getContactByPhone(String phone) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        String key = "";
        String value = "";

        if (phoneBook.containsKey(phone)) {
            for (Map.Entry<String, String> entry : phoneBook.entrySet()){
                key = entry.getKey();
                value = entry.getValue();
                if (key.equals(phone)) {
                    break;
                }
            }
            return value + " - " + key;

        } else {
            return "";
        }
    }

    public Set<String> getContactByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        if (phoneBook.containsValue(name)) {
            Set<String> set = new TreeSet<>();
            for(Map.Entry<String, String> entry : phoneBook.entrySet()){
                if(entry.getValue().equals(name)){
                    set.add(entry.getValue() + " - " + entry.getKey());
                }
            }
            return set;
        } else {
            return new TreeSet<>();
        }
    }

    public Set<String> getAllContacts() {
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        TreeSet<String> set = new TreeSet<>();
        if(!phoneBook.isEmpty()){
            for (String keys : phoneBook.keySet()){
                set.add(phoneBook.get(keys) + " - " + keys);
            }
        }
        return set;
    }
}