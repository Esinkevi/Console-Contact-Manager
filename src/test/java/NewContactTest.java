import org.example.Contact;
import org.example.FileManager;
import org.example.commands.NewContact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NewContactTest {

    @Mock
    private FileManager fileManagerMock;

    @InjectMocks
    private NewContact newContact;


    @Test
    public void testAddValidContact() {
        String contactData = "John Doe;+38050177032;john@example.com";

        newContact.addContact(contactData);

        verify(fileManagerMock, times(1)).saveContact(any(Contact.class));


    }

    @Test
    void testAddContactWithInvalidFormat() {
        String invalidContact = "John Doe;+38050177032";

        newContact.addContact(invalidContact);

        verify(fileManagerMock, never()).saveContact(any(Contact.class));
    }

    @Test
    void testAddContactWithEmptyFields() {
        String emptyFieldsContact = "  ;+38050177032;john@example.com";
        newContact.addContact(emptyFieldsContact);
        verify(fileManagerMock, never()).saveContact(any(Contact.class));
    }

    @Test
    void testAddContactWithInvalidPhone() {
        String invalidPhoneContact = "John Doe;+123;john@example.com"; // Некорректный номер

        newContact.addContact(invalidPhoneContact);

        verify(fileManagerMock, never()).saveContact(any(Contact.class));
    }

    @Test
    void testAddContactWithInvalidEmail() {
        String invalidEmailContact = "John Doe;+38050177032;john@com"; // Некорректный email

        newContact.addContact(invalidEmailContact);

        verify(fileManagerMock, never()).saveContact(any(Contact.class));
    }

}
