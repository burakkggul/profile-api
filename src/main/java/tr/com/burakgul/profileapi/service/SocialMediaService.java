package tr.com.burakgul.profileapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.burakgul.profileapi.model.entity.SocialMedia;
import tr.com.burakgul.profileapi.repository.SocialMediaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocialMediaService {

    private final SocialMediaRepository socialMediaRepository;

    public List<SocialMedia> saveAll(List<SocialMedia> socialMedia){
        return this.socialMediaRepository.saveAll(socialMedia);
    }
}
