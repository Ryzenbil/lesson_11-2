package practice;

import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    private static final PhoneBook phoneBook = new PhoneBook();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите номер, имя или команду:");
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            String[] parts = input.split(" ", 2);

            if (parts[0].equals("LIST") || parts[0].equals("List") || parts[0].equals("list")) {
                for (String values : phoneBook.getAllContacts()) {
                    System.out.println(values);
                }

            } else if (PhoneBook.isNum(input) && (parts.length == 1)) {
                System.out.println(phoneBook.getContactByPhone(parts[0]));
                if (phoneBook.getContactByPhone(parts[0]).isEmpty()) {
                    String phone = parts[0];
                    System.out.println("Такого номера нет в телефонной книге.");
                    System.out.println("Введите имя для номера " + "\"" + phone + "\"");
                    String name;
                    while (true) {
                        Scanner scanner2 = new Scanner(System.in);
                        String input2 = scanner2.nextLine();
                        if (!PhoneBook.isName(input2)) {
                            System.out.println("Неверный формат ввода. Введите имя");
                        } else {
                            name = input2;
                            break;
                        }
                    }
                    phoneBook.addContact(phone, name);
                }

            } else if (PhoneBook.isName(input) && parts.length == 1) {
                String name = parts[0];
                TreeSet <String> contact = (TreeSet<String>) phoneBook.getContactByName(name);

                if (contact.isEmpty()) {
                    System.out.println("Такого имени в телефонной книге нет.");
                    System.out.println("Введите номер телефона для абонента " + "\"" + name + "\"");
                    String phone;

                    while (true) {
                        Scanner scanner2 = new Scanner(System.in);
                        String input2 = scanner2.nextLine();
                        if (input2.isBlank()) {
                            System.out.println("Вы не ввели номер. Введите номер\n");
                        } else {
                            phone = input2;
                            break;
                        }
                    }
                    phoneBook.addContact(phone, name);
                } else {
                    System.out.println(phoneBook.getContactByName(name));
                }
            } else if (parts.length == 2 && (PhoneBook.isName(parts[0])) && (PhoneBook.isNum(parts[1]))) {
                phoneBook.addContact(parts[1], parts[0]);
            }
        }
    }
}