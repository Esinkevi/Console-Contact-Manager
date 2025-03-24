package org.example.commands;

import org.example.Contact;
import org.example.FileManager;
import org.springframework.stereotype.Service;

@Service
public class NewContact {

    private final FileManager fileManager;

    public NewContact(FileManager fileManager) {
        this.fileManager = fileManager;

    }

    public void addContact(String contact) {
        try {
            Contact newContact = processContact(contact);
            fileManager.saveContact(newContact);
            System.out.println("New contact added");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());


        }

    }

    private Contact processContact(String contact) {
        String[] contactData = contact.split(";");
        if (contactData.length != 3) {
            throw new IllegalArgumentException("Неверный формат контакта. Используйте: Имя;+Номер;Email");
        }

        String name = contactData[0].trim();
        String phone = contactData[1].trim();
        String email = contactData[2].trim();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            throw new IllegalArgumentException("Данные контакта не могут быть пустыми");
        }

        if (!checkPhone(phone)) {
            throw new IllegalArgumentException("Неверный формат номера телефона");
        }
        if (!checkEmail(email)) {
            throw new IllegalArgumentException("Неверный формат email");
        }

        return new Contact(name, phone, email);
    }

    private boolean checkEmail(String email) {
        return email.matches("^[\\w.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    private boolean checkPhone(String phone) {
        return phone.matches("\\+\\d{11}");
    }

}
