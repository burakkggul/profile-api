package tr.com.burakgul.profileapi.service.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.burakgul.profileapi.core.helper.DTOMapper;
import tr.com.burakgul.profileapi.model.dto.main.SocialMediaDTO;
import tr.com.burakgul.profileapi.model.entity.main.SocialMedia;
import tr.com.burakgul.profileapi.repository.main.SocialMediaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocialMediaService {

    private final SocialMediaRepository socialMediaRepository;
    private final DTOMapper dtoMapper;

    @Transactional
    public List<SocialMediaDTO> saveAll(List<SocialMediaDTO> socialMediasRequest){
        List<SocialMedia> upToDateSocialMedias = this.dtoMapper.mapListModel(socialMediasRequest, SocialMedia.class);
        List<SocialMedia> savedSocialMedias = this.socialMediaRepository.saveAll(upToDateSocialMedias);
        return this.dtoMapper.mapListModel(savedSocialMedias, SocialMediaDTO.class);
    }
}
//TODO findAll, save ve update methodlari eklenebilir hatta delete methodu

