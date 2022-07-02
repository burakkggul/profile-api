package tr.com.burakgul.profileapi.service.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.burakgul.profileapi.model.entity.main.Contact;
import tr.com.burakgul.profileapi.repository.main.ContactRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public List<Contact> saveAll(List<Contact> contacts){
        return this.contactRepository.saveAll(contacts);
    }
}
