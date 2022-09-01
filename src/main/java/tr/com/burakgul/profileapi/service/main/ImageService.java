package tr.com.burakgul.profileapi.service.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.burakgul.profileapi.core.helper.DTOMapper;
import tr.com.burakgul.profileapi.model.dto.ImageDTO;
import tr.com.burakgul.profileapi.model.entity.Image;
import tr.com.burakgul.profileapi.repository.main.ImageRepository;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final DTOMapper dtoMapper;

    @Transactional
    public ImageDTO save(ImageDTO imageRequest){
        Image upToDateImage = this.dtoMapper.mapModel(imageRequest, Image.class);
        Image savedImage = this.imageRepository.save(upToDateImage);
        return this.dtoMapper.mapModel(savedImage, ImageDTO.class);
    }

    //TODO find ve update methodlari eklenebilir hatta delete methodu

}
