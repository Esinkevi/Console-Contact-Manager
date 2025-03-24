package org.example.commands;

import org.example.Contact;
import org.example.FileManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetContacts {

    private final FileManager fileManager;

    public GetContacts(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public void getContacts() {
        List<Contact> contactList = fileManager.getContacts();
        if (contactList.isEmpty()) {
            System.out.println("Список контактов пуст");
        } else {
            contactList.forEach(System.out::println);
        }
    }
}
