package tr.com.burakgul.profileapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.burakgul.profileapi.model.entity.Image;
import tr.com.burakgul.profileapi.repository.ImageRepository;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public Image save(Image image){
        return this.imageRepository.save(image);
    }
}
