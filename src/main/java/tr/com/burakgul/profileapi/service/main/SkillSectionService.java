package tr.com.burakgul.profileapi.service.main;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.profileapi.core.helper.DTOMapper;
import tr.com.burakgul.profileapi.core.util.ObjectUpdaterUtil;
import tr.com.burakgul.profileapi.model.dto.main.SkillDTO;
import tr.com.burakgul.profileapi.model.dto.main.SkillSectionDTO;
import tr.com.burakgul.profileapi.model.entity.main.Skill;
import tr.com.burakgul.profileapi.model.entity.main.SkillSection;
import tr.com.burakgul.profileapi.repository.main.SkillSectionRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillSectionService {
    private final SkillSectionRepository skillSectionRepository;
    private final SkillService skillService;
    private final DTOMapper dtoMapper;

    @Transactional(readOnly = true)
    public SkillSectionDTO findSkillSection() {
        Optional<SkillSection> currentSkillSection = this.skillSectionRepository.findTopByOrderByIdDesc();
        if (currentSkillSection.isPresent()) {
            return this.dtoMapper.mapModel(currentSkillSection, SkillSectionDTO.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill Section bulunamadi.");
        }
    }

    @Transactional
    public SkillSectionDTO save(SkillSectionDTO skillSectionRequest) {
        SkillSection skillSectionToBeSavedOrUpdated = this.dtoMapper.mapModel(skillSectionRequest, SkillSection.class);
        this.setSkillSectionWithRelations(skillSectionToBeSavedOrUpdated, skillSectionRequest);
        SkillSection savedSkillSection = this.skillSectionRepository.save(skillSectionToBeSavedOrUpdated);
        return this.dtoMapper.mapModel(savedSkillSection, SkillSectionDTO.class);
    }

    @Transactional
    public SkillSectionDTO update(SkillSectionDTO skillSectionRequest) {
        Optional<SkillSection> skillSectionOptional = this.skillSectionRepository.findTopByOrderByIdDesc();
        if (skillSectionOptional.isPresent()) {
            SkillSection skillSectionToBeSavedOrUpdated = skillSectionOptional.get();
            this.updateSkillSectionWithRelations(skillSectionToBeSavedOrUpdated, skillSectionRequest);
            SkillSection savedSkillSection = this.skillSectionRepository.save(skillSectionToBeSavedOrUpdated);
            return this.dtoMapper.mapModel(savedSkillSection, SkillSectionDTO.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SkillSection bulunamadi!");
        }
    }

    private void updateSkillSectionWithRelations(SkillSection skillSectionToBeSavedOrUpdated, SkillSectionDTO skillSectionRequest) {
        SkillSection upToDateSkillSection = this.dtoMapper.mapModel(skillSectionRequest, SkillSection.class);
        ObjectUpdaterUtil.updateObject(skillSectionToBeSavedOrUpdated, upToDateSkillSection, Arrays.asList("id", "skills"));
        this.setSkillSectionWithRelations(skillSectionToBeSavedOrUpdated, skillSectionRequest);
    }

    private void setSkillSectionWithRelations(SkillSection skillSectionToBeSavedOrUpdated, SkillSectionDTO skillSectionRequest){
        List<SkillDTO> savedSkills = this.skillService.saveAll(skillSectionRequest.getSkills());
        List<Skill> mappedSkillsToSetSkillSection = this.dtoMapper.mapListModel(savedSkills, Skill.class);
        skillSectionToBeSavedOrUpdated.setSkills(mappedSkillsToSetSkillSection);
    }
}
