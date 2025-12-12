package practice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.addContact("7645678900", "Аида");
        phoneBook.addContact("7712345678", "Аида");
        phoneBook.addContact("79001234567", "Сэм");

        System.out.println(phoneBook.getContactByName("Аида"));
        System.out.println(phoneBook.getContactByName("Сэм"));
        System.out.println(phoneBook.getContactByName("Неизвестный")); // пустой

        System.out.println(phoneBook.getContactByPhone("79001234567"));
        System.out.println(phoneBook.getAllContacts());
    }
}




