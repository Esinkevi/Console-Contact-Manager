package org.example.commands;

import org.example.Contact;
import org.example.FileManager;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class DeleteContact {

    private final FileManager fileManager;

    public DeleteContact(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public void deleteContact(String text) {
        List<Contact> contactList = fileManager.getContacts();
        for (Contact contact : contactList){
            if (contact.getName().equals(text) || contact.getPhone().equals(text) || contact.getEmail().equals(text)){
                contactList.remove(contact);
                fileManager.deleteContact(contactList);
                System.out.println("Контакт удален");
                return;
            }
        }
    }
}
