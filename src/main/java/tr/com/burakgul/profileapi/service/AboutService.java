package tr.com.burakgul.profileapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.profileapi.core.helper.DTOMapper;
import tr.com.burakgul.profileapi.core.util.ObjectUpdaterUtil;
import tr.com.burakgul.profileapi.model.dto.AboutRequest;
import tr.com.burakgul.profileapi.model.dto.AboutResponse;
import tr.com.burakgul.profileapi.model.entity.About;
import tr.com.burakgul.profileapi.model.entity.Contact;
import tr.com.burakgul.profileapi.repository.AboutRepository;

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
        About aboutToSave = this.dtoMapper.mapModel(aboutRequest, About.class);
        List<Contact> contacts = this.contactService.saveAll(aboutToSave.getContacts());
        aboutToSave.setContacts(contacts);
        About savedAbout = this.aboutRepository.save(aboutToSave);
        return this.dtoMapper.mapModel(savedAbout, AboutResponse.class);
    }

    @Transactional
    public AboutResponse update(AboutRequest aboutRequest) {
        Optional<About> aboutOptional = this.aboutRepository.findTopByOrderByIdDesc();

        if (aboutOptional.isPresent()) {
            About currentAbout = aboutOptional.get();
            About upToDateAbout = this.dtoMapper.mapModel(aboutRequest, About.class);
            this.updateAboutObjectWithRelations(currentAbout, upToDateAbout);
            return this.dtoMapper.mapModel(currentAbout, AboutResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "About bulunamadı.");
        }
    }

    @Transactional
    public void updateAboutObjectWithRelations(About currentAbout, About upToDateAbout) {
        ObjectUpdaterUtil.updateObject(currentAbout, upToDateAbout, Arrays.asList("id", "contacts"));
        List<Contact> contacts = this.contactService.saveAll(upToDateAbout.getContacts());
        currentAbout.setContacts(contacts);
    }
}
