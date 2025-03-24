package org.example.commands;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CommandsManager {

    private final GetContacts getContacts;
    private final NewContact newContact;
    private final DeleteContact deleteContact;

    public CommandsManager(GetContacts getContacts, NewContact newContact, DeleteContact deleteContact) {
        this.getContacts = getContacts;
        this.newContact = newContact;
        this.deleteContact = deleteContact;
    }

    public void execute(String command) {
        Scanner scanner = new Scanner(System.in);
        switch (command) {
            case "list":
                System.out.println("Список контактов");
                getContacts.getContacts();
                break;

            case "add":
                System.out.println("Введите контакт в формате: Имя; + Номер; Email");
                String contact = scanner.nextLine();
                newContact.addContact(contact);
                break;

            case "exit":
                System.out.println("Пока");
                break;

            case "remove":
                System.out.println("Введите имя, номер или email контакта");
                String text = scanner.nextLine();
                deleteContact.deleteContact(text);
                break;

            default:
                System.out.println("Неизвестная команда");
        }
    }


    public void printHelp() {
        System.out.println("Список команд:");
        System.out.println("1. list - вывод списка контактов");
        System.out.println("2. add - добавление нового контакта");
        System.out.println("3. remove - удаление контакта");
        System.out.println("5. exit - выход из программы");
    }

}
