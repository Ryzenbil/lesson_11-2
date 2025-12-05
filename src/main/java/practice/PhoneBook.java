package practice;

import java.util.*;

public class PhoneBook {
    private final TreeMap<String, String> phoneBook = new TreeMap<>();

    private String name;
    private String number;

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

        String regex = "[-()+\\s]";
        String formatPhone = phone.replaceAll(regex, "");

        if (!isName(name) || !isNum(formatPhone)) {
            System.out.println("Неверный формат ввода.");

        } else if (phoneBook.containsKey(formatPhone)) {
            for (Map.Entry <String, String> entry : phoneBook.entrySet()) {
                if (entry.getKey().equals(formatPhone)) {
                    phoneBook.remove(entry.getKey());
                    System.out.println(phoneBook.put(formatPhone, name));
                    System.out.println("Контакт перезаписан!");
                    break;
                }
            }
        } else if (phoneBook.containsValue(name)) {
            String secondPhone;
            for (Map.Entry <String, String> entry : phoneBook.entrySet()) {
                if (entry.getValue().equals(name)) {
                    secondPhone = entry.getKey() + ", " + formatPhone;
                    phoneBook.remove(entry.getKey());
                    phoneBook.put(secondPhone, name);
                    System.out.println("Номер добавлен");
                    break;
                }
            }
        } else {
            phoneBook.put(formatPhone, name);
            System.out.println("Контакт сохранен!\n");
        }
    }



    public String getContactByPhone(String phone) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку

        String regex = "[-()+\\s]";
        String formatPhone = phone.replaceAll(regex, "");
        formatPhone = formatPhone.replaceFirst("8", "7");
        String key = "";
        String value = "";

        if (phoneBook.containsKey(formatPhone)) {
            for (Map.Entry<String, String> entry : phoneBook.entrySet()){
                key = entry.getKey();
                value = entry.getValue();
                if (key.equals(formatPhone)) {
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

    // для обхода Map используйте получение пары ключ->значение Map.Entry<String,String>
    // это поможет вам найти все ключи (key) по значению (value)
    /*
        for (Map.Entry<String, String> entry : map.entrySet()){
            String key = entry.getKey(); // получения ключа
            String value = entry.getValue(); // получения значение
        }
    */
}
