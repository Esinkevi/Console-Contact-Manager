import org.example.Contact;
import org.example.FileManager;
import org.example.commands.DeleteContact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)  // Используем MockitoExtension для инициализации моков
public class DeleteContactTest {

    @Mock
    private FileManager fileManagerMock;  // Мокируем FileManager

    @InjectMocks
    private DeleteContact deleteContact;  // Mockito автоматически внедрит fileManagerMock в deleteContact

    @Test
    public void testDeleteContactByName() {
        // Подготовим данные для теста
        List<Contact> contactList = new ArrayList<>();
        Contact contact1 = new Contact("John Doe", "+38050177032", "john@example.com");
        Contact contact2 = new Contact("Jane Doe", "+38050177033", "jane@example.com");
        contactList.add(contact1);
        contactList.add(contact2);


        // Настроим мок fileManager для возврата списка контактов
        when(fileManagerMock.getContacts()).thenReturn(contactList);

        // Выполняем удаление контакта по имени
        deleteContact.deleteContact("John Doe");

        assertEquals(1, contactList.size());

        assertFalse(contactList.contains(contact1));
        assertTrue(contactList.contains(contact2));


    }

    @Test
    public void testDeleteContactByPhone() {
        List<Contact> contactList = new ArrayList<>();
        Contact contact1 = new Contact("John Doe", "+38050177032", "john@example.com");
        Contact contact2 = new Contact("Jane Doe", "+38050177033", "jane@example.com");
        contactList.add(contact1);
        contactList.add(contact2);

        when(fileManagerMock.getContacts()).thenReturn(contactList);

        deleteContact.deleteContact("+38050177032");

        assertEquals(1, contactList.size());

        assertFalse(contactList.contains(contact1));
        assertTrue(contactList.contains(contact2));

    }
    @Test
    public void testDeleteContactByEmail() {
        List<Contact> contactList = new ArrayList<>();
        Contact contact1 = new Contact("John Doe", "+38050177032", "john@example.com");
        Contact contact2 = new Contact("Jane Doe", "+38050177033", "jane@example.com");
        contactList.add(contact1);
        contactList.add(contact2);

        when(fileManagerMock.getContacts()).thenReturn(contactList);

        deleteContact.deleteContact("john@example.com");

        assertEquals(1, contactList.size());

        assertFalse(contactList.contains(contact1));
        assertTrue(contactList.contains(contact2));
    }

}
