package tr.com.burakgul.profileapi.service.main;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.profileapi.core.helper.DTOMapper;
import tr.com.burakgul.profileapi.core.util.ObjectUpdaterUtil;
import tr.com.burakgul.profileapi.model.dto.main.ContactResponse;
import tr.com.burakgul.profileapi.model.dto.main.SkillDTO;
import tr.com.burakgul.profileapi.model.entity.main.Contact;
import tr.com.burakgul.profileapi.model.entity.main.Skill;
import tr.com.burakgul.profileapi.repository.main.SkillRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillService {
    private final SkillRepository skillRepository;
    private final DTOMapper dtoMapper;
    @Transactional(readOnly = true)
    public List<SkillDTO> findAll() {
        List<Skill> skillList = this.skillRepository.findAll();
        if(skillList.size() != 0){
            return this.dtoMapper.mapListModel(skillList, SkillDTO.class);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Herhangi bir Skill bulunamadi.");
        }
    }

    @Transactional
    public SkillDTO save(SkillDTO skillRequest) {
        Skill upToDateSkill = this.dtoMapper.mapModel(skillRequest, Skill.class);
        Skill savedSkill = this.skillRepository.save(upToDateSkill);
        return this.dtoMapper.mapModel(savedSkill, SkillDTO.class);
    }

    @Transactional
    public List<SkillDTO> saveAll(List<SkillDTO> skillRequest) {
        List<Skill> upToDateSkills = this.dtoMapper.mapListModel(skillRequest, Skill.class);
        List<Skill> savedSkills = this.skillRepository.saveAll(upToDateSkills);
        return this.dtoMapper.mapListModel(savedSkills, SkillDTO.class);
    }

    @Transactional
    public SkillDTO update(SkillDTO skillRequest, Long skillId) {
        Optional<Skill> skillOptional = skillRepository.findById(skillId);
        if(skillOptional.isPresent()){
            Skill currentSkill = skillOptional.get();
            Skill upToDateSkill = this.dtoMapper.mapModel(skillRequest, Skill.class);
            ObjectUpdaterUtil.updateObject(currentSkill, upToDateSkill, Arrays.asList("id"));
            Skill savedSkill = this.skillRepository.save(currentSkill);
            return this.dtoMapper.mapModel(savedSkill, SkillDTO.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bu id ile herhangi bir Skill bulunamadi.");
        }
    }
}
