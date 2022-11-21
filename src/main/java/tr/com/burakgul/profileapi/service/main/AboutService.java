package tr.com.burakgul.profileapi.service.main;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.profileapi.core.helper.DTOMapper;
import tr.com.burakgul.profileapi.core.util.ObjectUpdaterUtil;
import tr.com.burakgul.profileapi.model.dto.main.*;
import tr.com.burakgul.profileapi.model.entity.main.*;
import tr.com.burakgul.profileapi.repository.main.AboutRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AboutService {

    private final ContactService contactService;

    private final AboutRepository aboutRepository;

    private final DTOMapper dtoMapper;

    @Transactional(readOnly = true)
    public AboutResponse findAbout() {
        /*
        Optional<About> aboutOptional = this.aboutRepository.findTopByOrderByIdDesc();
        if(aboutOptional.isPresent()){
            return this.dtoMapper.mapModel(aboutOptional.get(),AboutResponse.class);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"About bulunamadı.");
        }
        */

        About about = this.aboutRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "About bulunamadı."));

        return this.dtoMapper.mapModel(about, AboutResponse.class);
    }

    @Transactional
    public AboutResponse save(AboutRequest aboutRequest) {
        About aboutToBeSavedOrUpdated = this.dtoMapper.mapModel(aboutRequest, About.class);
        this.setAboutWithRelations(aboutToBeSavedOrUpdated, aboutRequest);
        About savedAbout = this.aboutRepository.save(aboutToBeSavedOrUpdated);
        return this.dtoMapper.mapModel(savedAbout, AboutResponse.class);
    }


    @Transactional
    public AboutResponse update(AboutRequest aboutRequest) {
        Optional<About> aboutOptional = this.aboutRepository.findTopByOrderByIdDesc();
        if (aboutOptional.isPresent()) {
            About aboutToBeSavedOrUpdated = aboutOptional.get();
            this.updateAboutWithRelations(aboutToBeSavedOrUpdated, aboutRequest);
            About savedAbout = this.aboutRepository.save(aboutToBeSavedOrUpdated);
            return this.dtoMapper.mapModel(savedAbout, AboutResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "About bulunamadi!");
        }
    }

    private void updateAboutWithRelations(About aboutToBeSavedOrUpdated, AboutRequest aboutRequest) {
        About upToDateAbout = this.dtoMapper.mapModel(aboutRequest, About.class);
        ObjectUpdaterUtil.updateObject(aboutToBeSavedOrUpdated, upToDateAbout, Arrays.asList("id", "contacts"));
        this.setAboutWithRelations(aboutToBeSavedOrUpdated, aboutRequest);
    }

    private void setAboutWithRelations(About aboutToBeSavedOrUpdated, AboutRequest aboutRequest){
        List<ContactResponse> savedContacts = this.contactService.saveAll(aboutRequest.getContacts());
        List<Contact> mappedContactsToSetAbout = this.dtoMapper.mapListModel(savedContacts, Contact.class);
        aboutToBeSavedOrUpdated.setContacts(mappedContactsToSetAbout);
    }
}
