package practice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        while (true){
            System.out.println("Введите номер, имя или команду:");
            String input = new Scanner(System.in).nextLine();
            if(input.equals("0")) {
                break;
            }
            switch(input){
                case "LIST":
                    System.out.println(phoneBook.getAllContacts());
                    break;
                default:
                    String contactByName = phoneBook.getContactByName(input).toString();

                    String contactByPhone = phoneBook.getContactByPhone(input);

                    if(phoneBook.getContactByName(input).isEmpty() && phoneBook.isName(input)){
                        System.out.println("Такого имени в телефонной книге нет.");
                        System.out.println("Введите номер телефона для абонента \"" + input + "\":");
                        String phone = new Scanner(System.in).nextLine();
                        phoneBook.addContact(phone, input);
                        System.out.println("Контакт сохранен");
                    }else if(phoneBook.isName(input)) {
                        System.out.println(contactByName);
                    }else if(contactByPhone.isEmpty() && phoneBook.isNumber(input)){
                        System.out.println("Такого телефона в телефонной книге нет.");
                        System.out.println("Введите имя абонента для номера \"" + input + "\":");
                        String name = new Scanner(System.in).nextLine();
                        phoneBook.addContact(input, name);
                        System.out.println("Контакт сохранен");
                    }else if(phoneBook.isNumber(input)){
                        System.out.println(contactByPhone);
                    }else {
                        System.out.println("Неверный формат ввода");
                    }
            }
        }
    }
}
