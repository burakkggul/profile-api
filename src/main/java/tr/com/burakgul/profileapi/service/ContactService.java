package tr.com.burakgul.profileapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.burakgul.profileapi.model.entity.Contact;
import tr.com.burakgul.profileapi.repository.ContactRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public List<Contact> saveAll(List<Contact> contacts){
        return this.contactRepository.saveAll(contacts);
    }
}
