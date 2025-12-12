package practice;

import java.text.Format;
import java.util.*;

public class PhoneBook {
    private Map<String, Set<String>> book = new TreeMap<>();

    public void addContact(String phone, String name) {
        // проверьте корректность формата имени и телефона
        // (рекомедуется написать отдельные методы для проверки является строка именем/телефоном)
        // если такой номер уже есть в списк е, то перезаписать имя абонента

        if (!isCorrectName(name) || !isCorrectPhone(phone)) {
            System.out.println("Invalid phone number");
            return;
        }
        if  (!book.containsKey(name)) {
            book.put(name, new TreeSet<>());
        }
        book.get(name).add(phone);

    }

    public String getContactByPhone(String phone) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку

        for (Map.Entry<String, Set<String>> entry : book.entrySet()) {
            if (entry.getValue().contains(phone)) {
                return entry.getKey() + " - " + phone;
            }
        }
        return "";

    }

    public Set<String> getContactByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        Set<String> result = new TreeSet<>();

        if (!book.containsKey(name)) return result;

        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String phone : book.get(name)) {
            if (!first) sb.append(", ");
            sb.append(phone);
            first = false;
    }
        result.add(name + " - " + sb.toString());
        return result;
        }


    public Set<String> getAllContacts() {
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet

        Set<String> result = new TreeSet<>();
        for (Map.Entry<String, Set<String>> entry : book.entrySet()) {
            String name = entry.getKey();
            Set<String> phones = entry.getValue();
            StringBuilder sb = new StringBuilder();
            boolean first = true;
            for (String phone : phones) {
                if (!first) sb.append(", ");
                sb.append(phone);
                first = false;
            }
            result.add(name + " - " + sb.toString());
        }
        return result;
    }

    private boolean isCorrectName(String name) {
        return name.matches("[A-Za-zА-Яа-яЁё]+");
    }
    private boolean isCorrectPhone(String phone) {
        return phone.matches("\\d{10,11}");
    }

    // для обхода Map используйте получение пары ключ->значение Map.Entry<String,String>
    // это поможет вам найти все ключи (key) по значению (value)
    /*
        for (Map.Entry<String, String> entry : map.entrySet()){
            String key = entry.getKey(); // получения ключа
            String value = entry.getValue(); // получения ключа
        }
    */
}
