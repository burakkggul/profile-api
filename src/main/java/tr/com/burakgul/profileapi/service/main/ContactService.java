package tr.com.burakgul.profileapi.service.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.burakgul.profileapi.core.helper.DTOMapper;
import tr.com.burakgul.profileapi.model.dto.main.ContactRequest;
import tr.com.burakgul.profileapi.model.dto.main.ContactResponse;
import tr.com.burakgul.profileapi.model.dto.main.WorkExperienceDTO;
import tr.com.burakgul.profileapi.model.entity.main.Contact;
import tr.com.burakgul.profileapi.model.entity.main.WorkExperience;
import tr.com.burakgul.profileapi.repository.main.ContactRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final DTOMapper dtoMapper;

    @Transactional
    public List<ContactResponse> saveAll(List<ContactRequest> contactRequest){
        List<Contact> upToDateContacts = this.dtoMapper.mapListModel(contactRequest, Contact.class);
        List<Contact> savedContacts = this.contactRepository.saveAll(upToDateContacts);
        return this.dtoMapper.mapListModel(savedContacts, ContactResponse.class);
    }
}
//TODO findAll, save ve update methodlari eklenebilir hatta delete methodu
