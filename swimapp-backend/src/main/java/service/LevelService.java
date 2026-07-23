package service;

import java.util.List;

import dto.level.CreateLevelDTO;
import dto.level.CreateLevelResponseDTO;
import dto.level.LevelSummaryDTO;
import dto.level.UpdateLevelInfoDTO;
import entity.Level;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import mapper.LevelMapper;
import repository.LevelRepository;


@ApplicationScoped
public class LevelService {

    @Inject
    LevelMapper levelMapper;

    @Inject
    LevelRepository levelRepository;
    
    // CreateLevelDTO - POST
    @Transactional
    public CreateLevelResponseDTO createLevel(CreateLevelDTO dto) {
        Level level = levelMapper.toCreateEntity(dto);
        level.getLevelId();
        level.getLevelName();
        // Persist
        levelRepository.persist(level);
        return levelMapper.toCreateResponseDTO(level);
    }

    // Retrieve all levels - GET
    public List<LevelSummaryDTO> getLevelsList(int page, int size) {
       List<Level> levels = levelRepository.getLevelsList(page, size); 
       return levels.stream().map(
               levelMapper::toSummaryDTO
               ).toList();
    }

    // Retrieve Level By ID - GET
    public LevelSummaryDTO getLevelById(Long level_id) {
        Level level = levelRepository.findLevelById(level_id);
        if (level == null) {
            throw new NotFoundException("Level with ID: " + level_id + " is not found!");
        }
        return levelMapper.toSummaryDTO(level);
    }

    // UpdateLevelInfoDTO - PUT
    @Transactional
    public LevelSummaryDTO updateLevelInfoEntity(Long level_id, UpdateLevelInfoDTO dto) {
        Level level = levelRepository.findLevelById(level_id);
        if (level == null) {
            throw new NotFoundException("Level with ID: " + level_id + " not found!");
        }
        levelMapper.updateLevelInfoEntity(level, dto);
        return levelMapper.toSummaryDTO(level);
    }

    // deleteLevel - DELETE
    @Transactional
    public void deleteLevel(Long level_id) {
        boolean deleted = levelRepository.deleteLevel(level_id);
        if (!deleted) {
            throw new NotFoundException("Level not found!");
        }
    }
}
