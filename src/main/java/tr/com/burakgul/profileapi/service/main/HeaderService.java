package tr.com.burakgul.profileapi.service.main;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.profileapi.core.helper.DTOMapper;
import tr.com.burakgul.profileapi.core.util.ObjectUpdaterUtil;
import tr.com.burakgul.profileapi.model.dto.ImageDTO;
import tr.com.burakgul.profileapi.model.dto.main.*;
import tr.com.burakgul.profileapi.model.entity.Image;
import tr.com.burakgul.profileapi.model.entity.main.*;
import tr.com.burakgul.profileapi.repository.main.HeaderRepository;
import tr.com.burakgul.profileapi.repository.main.ImageRepository;
import tr.com.burakgul.profileapi.repository.main.SocialMediaRepository;

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

    @Transactional(readOnly = true)
    public HeaderResponse findHeader() {
        Optional<Header> headerOptional = this.headerRepository.findTopByOrderByIdDesc();
        if (headerOptional.isPresent()) {
            return this.dtoMapper.mapModel(headerOptional.get(), HeaderResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Header bulunamadı.");
        }
    }

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
    @Transactional
    public HeaderResponse save(HeaderRequest headerRequest) {
        Header headerToBeSavedOrUpdated = this.dtoMapper.mapModel(headerRequest, Header.class);
        this.setHeaderWithRelations(headerToBeSavedOrUpdated, headerRequest);
        Header savedHeader = this.headerRepository.save(headerToBeSavedOrUpdated);
        return this.dtoMapper.mapModel(savedHeader, HeaderResponse.class);
    }

    @Transactional
    public HeaderResponse update(HeaderRequest headerRequest) {
        Header headerToBeSavedOrUpdated = this.headerRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Header bulunamadi"));
        this.updateHeaderWithRelations(headerToBeSavedOrUpdated, headerRequest);
        Header savedHeader = this.headerRepository.save(headerToBeSavedOrUpdated);
        return this.dtoMapper.mapModel(savedHeader, HeaderResponse.class);
    }

    private void updateHeaderWithRelations(Header headerToBeSavedOrUpdated, HeaderRequest headerRequest) {
        Resume upToDateHeader = this.dtoMapper.mapModel(headerRequest, Resume.class);
        ObjectUpdaterUtil.updateObject(headerToBeSavedOrUpdated, upToDateHeader, Arrays.asList("id", "socialMedia", "image"));
        this.setHeaderWithRelations(headerToBeSavedOrUpdated, headerRequest);
    }

    private void setHeaderWithRelations(Header headerToBeSavedOrUpdated, HeaderRequest headerRequest) {
        List<SocialMediaDTO> savedSocialMedias = this.socialMediaService.saveAll(headerRequest.getSocialMedia());
        List<SocialMedia> mappedSocialMediasToSetHeader = this.dtoMapper.mapListModel(savedSocialMedias, SocialMedia.class);
        headerToBeSavedOrUpdated.setSocialMedia(mappedSocialMediasToSetHeader);

        ImageDTO savedImage = this.imageService.save(headerRequest.getImage());
        Image mappedImageToSetHeader = this.dtoMapper.mapModel(savedImage, Image.class);
        headerToBeSavedOrUpdated.setImage(mappedImageToSetHeader);
    }
}
