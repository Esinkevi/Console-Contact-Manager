package org.example;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class FileManager {

    private final String path = "src/main/resources/contacts.txt";

    public void saveContact(Contact contact) {
        File file = new File(path);

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8))) {
            writer.write(contact.toString() + System.lineSeparator());
            System.out.println("Контакт сохранен");
        } catch (Exception e) {
            System.err.println("Ошибка при сохранении контакта: " + e.getMessage());
        }
    }

    // Метод для получения списка контактов из файла с кодировкой UTF-8
    public List<Contact> getContacts() {
        File file = new File(path);
        List<Contact> contactList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] contactData = line.split(";");
                if (contactData.length == 3) { // Проверяем, что данные корректные
                    contactList.add(new Contact(contactData[0], contactData[1], contactData[2]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла: " + e.getMessage(), e);
        }

        if (contactList.isEmpty()) {
            System.out.println("Список контактов пуст.");
        }

        return contactList;
    }

    // Метод для удаления контактов из файла (перезаписывает файл)
    public void deleteContact(List<Contact> contactList) {
        File file = new File(path);

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), StandardCharsets.UTF_8))) {
            for (Contact contact : contactList) {
                writer.write(contact.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Ошибка при обновлении файла: " + e.getMessage());
        }
    }
}
