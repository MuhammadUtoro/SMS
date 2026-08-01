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
    public LevelSummaryDTO getLevelById(Long levelId) {
        Level level = levelRepository.findLevelById(levelId);
        if (level == null) {
            throw new NotFoundException("Level with ID: " + levelId + " is not found!");
        }
        return levelMapper.toSummaryDTO(level);
    }

    // UpdateLevelInfoDTO - PUT
    @Transactional
    public LevelSummaryDTO updateLevelInfoEntity(Long levelId, UpdateLevelInfoDTO dto) {
        Level level = levelRepository.findLevelById(levelId);
        if (level == null) {
            throw new NotFoundException("Level with ID: " + levelId + " not found!");
        }
        levelMapper.updateLevelInfoEntity(level, dto);
        return levelMapper.toSummaryDTO(level);
    }

    // deleteLevel - DELETE
    @Transactional
    public void deleteLevel(Long levelId) {
        boolean deleted = levelRepository.deleteLevel(levelId);
        if (!deleted) {
            throw new NotFoundException("Level not found!");
        }
    }
}
