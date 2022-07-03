package tr.com.burakgul.profileapi.service.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.profileapi.core.helper.DTOMapper;
import tr.com.burakgul.profileapi.core.util.ObjectHistoryUtil;
import tr.com.burakgul.profileapi.core.util.ObjectUpdaterUtil;
import tr.com.burakgul.profileapi.model.dto.blog.CategoryResponse;
import tr.com.burakgul.profileapi.model.dto.blog.CategoryRequest;
import tr.com.burakgul.profileapi.model.entity.blog.Category;
import tr.com.burakgul.profileapi.repository.blog.CategoryRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final DTOMapper dtoMapper;

    @Transactional
    public CategoryResponse save(CategoryRequest categoryRequest) {
        Category category = this.dtoMapper.mapModel(categoryRequest, Category.class);
        ObjectHistoryUtil.initHistoricalEntity(category);
        Category savedCategory = this.categoryRepository.save(category);
        return this.dtoMapper.mapModel(savedCategory, CategoryResponse.class);
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> findAll() {
        List<Category> categories = this.categoryRepository.findAll();
        if (!categories.isEmpty()) {
            return this.dtoMapper.mapListModel(categories, CategoryResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kategori bulunamadı.");
        }
    }

    @Transactional
    public CategoryResponse update(CategoryResponse categoryResponse){
        Optional<Category> oldCategoryOptional = categoryRepository.findById(categoryResponse.getId());
        if(oldCategoryOptional.isPresent()){
            Category upToDateCategory = this.dtoMapper.mapModel(categoryResponse,Category.class);
            Category oldCategory = oldCategoryOptional.get();
            ObjectUpdaterUtil.updateObject(oldCategory,
                    upToDateCategory,
                    Arrays.asList("id", "createdDate", "lastModifiedDate"));
            ObjectHistoryUtil.setLastModifiedDate(oldCategory);
            Category updatedCategory = this.categoryRepository.save(oldCategory);
            return this.dtoMapper.mapModel(updatedCategory, CategoryResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Bu id ile kategori bulunamadı.");
        }
    }

    @Transactional
    public void deleteById(Long id) {
        Optional<Category> categoryOptional = this.categoryRepository.findById(id);
        if(categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            category.setDeletedDate(new Date());
            this.categoryRepository.save(category);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Category bulunamadı.");
        }
    }
}
