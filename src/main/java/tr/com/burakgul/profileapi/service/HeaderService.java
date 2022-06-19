package tr.com.burakgul.profileapi.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.profileapi.core.helper.DTOMapper;
import tr.com.burakgul.profileapi.core.util.ObjectUpdaterUtil;
import tr.com.burakgul.profileapi.model.dto.HeaderRequest;
import tr.com.burakgul.profileapi.model.dto.HeaderResponse;
import tr.com.burakgul.profileapi.model.entity.Header;
import tr.com.burakgul.profileapi.model.entity.Image;
import tr.com.burakgul.profileapi.model.entity.SocialMedia;
import tr.com.burakgul.profileapi.repository.HeaderRepository;
import tr.com.burakgul.profileapi.repository.ImageRepository;
import tr.com.burakgul.profileapi.repository.SocialMediaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
   /*
   @RequiredArgsConstructor'ın eşiti aşağıdaki constructor metottur.
   public HeaderService(ImageRepository imageRepository,
                         SocialMediaRepository socialMediaRepository,
                         HeaderRepository headerRepository){
        this.imageRepository = imageRepository;
        this.socialMediaRepository = socialMediaRepository;
        this.headerRepository = headerRepository;
    }
    */
public class HeaderService {

    private final Logger LOGGER = LoggerFactory.getLogger(HeaderService.class);

    private final ImageRepository imageRepository;
    private final SocialMediaRepository socialMediaRepository;
    private final HeaderRepository headerRepository;

    private final SocialMediaService socialMediaService;
    private final ImageService imageService;

    // private final ObjectMapper objectMapper;
    // private final ModelMapper modelMapper;
    private final DTOMapper dtoMapper;

    /*
    public HeaderResponse save(HeaderRequest headerRequest) {
        // Header header = this.objectMapper.convertValue(headerRequest, Header.class);
        // Header header = this.dtoMapper.mapModel(headerRequest,Header.class);
        Header header = this.modelMapper.map(headerRequest,Header.class);
        // HeaderResponse headerResponse = this.objectMapper.convertValue(header,HeaderResponse.class);
        HeaderResponse headerResponse = this.dtoMapper.mapModel(header,HeaderResponse.class);
        return headerResponse;
    }
    */

    public HeaderResponse save(HeaderRequest headerRequest) {
        Header header = this.dtoMapper.mapModel(headerRequest, Header.class);
        Image image = this.imageRepository.save(header.getImage());
        List<SocialMedia> savedSocialMedia = this.socialMediaRepository.saveAll(header.getSocialMedia());
        header.setImage(image);
        header.setSocialMedia(savedSocialMedia);
        Header savedHeader = this.headerRepository.save(header);
        HeaderResponse headerResponse = this.dtoMapper.mapModel(savedHeader, HeaderResponse.class);
        return headerResponse;
    }

    /**
     * Burda socialMedia ve image güncellenmiyor.
     * Bunların güncellenmesini sağlayacak şekilde bu metodu güncelleyelim.
     * @param headerRequest
     * @return
     */
    public HeaderResponse update(HeaderRequest headerRequest) {
        Header upToDateHeader = this.dtoMapper.mapModel(headerRequest, Header.class);
        Header currentHeader = this.headerRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Header bulunamadı."));
        this.updateHeaderObjectWithRelations(currentHeader,upToDateHeader);
        Header savedHeader = this.headerRepository.save(currentHeader);
        return this.dtoMapper.mapModel(savedHeader, HeaderResponse.class);
    }

    public Object findHeader() {
        Optional<Header> headerOptional = this.headerRepository.findTopByOrderByIdDesc();
        if (headerOptional.isPresent()) {
            return this.dtoMapper.mapModel(headerOptional.get(), HeaderResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Header bulunamadı.");
        }
    }

    private void updateHeaderObjectWithRelations(Header currentHeader, Header upToDateHeader){
        ObjectUpdaterUtil.updateObject(currentHeader, upToDateHeader, Arrays.asList("id", "socialMedia", "image"));
        List<SocialMedia> socialMedia = this.socialMediaService.saveAll(upToDateHeader.getSocialMedia());
        currentHeader.setSocialMedia(socialMedia);
        Image image = this.imageService.save(upToDateHeader.getImage());
        currentHeader.setImage(image);
    }
}
