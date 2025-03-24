import org.example.Contact;
import org.example.FileManager;
import org.example.commands.GetContacts;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetContactsTest {


    @Mock
    private FileManager fileManagerMock;

    @InjectMocks
    private GetContacts getContacts;


    @Test
    public void testGetContacts() {
        List<Contact> contactList = new ArrayList<>();
        Contact contact1 = new Contact("John Doe", "+38050177032", "john@example.com");
        Contact contact2 = new Contact("Jane Doe", "+38050177033", "jane@example.com");
        contactList.add(contact1);
        contactList.add(contact2);

        when(fileManagerMock.getContacts()).thenReturn(contactList);

        getContacts.getContacts();

        verify(fileManagerMock).getContacts();

    }


}
