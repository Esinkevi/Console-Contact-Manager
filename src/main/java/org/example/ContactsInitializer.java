package org.example;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
@Profile("init")
public class ContactsInitializer {

    private final FileManager fileManager;

    @Value("${app.contacts-file}")
    private String contactsFilePath;

    public ContactsInitializer(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @PostConstruct
    public void initContacts() {
        System.out.println("Инициализация контактов запущена...");
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("default-contacts.txt");


             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            if (inputStream == null) {
                throw new FileNotFoundException("Файл default-contacts.txt не найден в ресурсах");
            }
            String line;
            while ((line = reader.readLine()) != null) {
                String[] contactData = line.split(";");
                if (contactData.length == 3) {
                    fileManager.saveContact(new Contact(contactData[0].trim(), contactData[1].trim(), contactData[2].trim()));
                }
            }

            System.out.println("Контакты успешно загружены из файла.");
        } catch (IOException | NullPointerException e) {
            System.err.println("Ошибка при чтении загрузочного файла: " + e.getMessage());
        }
    }

}
