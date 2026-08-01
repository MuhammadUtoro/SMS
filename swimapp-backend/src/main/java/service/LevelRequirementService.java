package service;

import java.util.List;

import dto.levelrequirement.CreateLevelRequirementDTO;
import dto.levelrequirement.CreateLevelRequirementResponseDTO;
import dto.levelrequirement.LevelRequirementSummaryDTO;
import dto.levelrequirement.UpdateLevelRequirementInfoDTO;
import dto.levelrequirement.UpdateLevelRequirementLevelDTO;
import entity.Level;
import entity.LevelRequirement;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import mapper.LevelRequirementMapper;
import repository.LevelRepository;
import repository.LevelRequirementRepository;

@ApplicationScoped
public class LevelRequirementService {

    @Inject
    LevelRequirementMapper levelRequirementMapper;

    @Inject
    LevelRepository levelRepository;

    @Inject
    LevelRequirementRepository levelRequirementRepository;

    // CreateLevelRequirement - POST
    @Transactional
    public CreateLevelRequirementResponseDTO createLevelRequirement(
            CreateLevelRequirementDTO dto) {
        LevelRequirement levelRequirement = levelRequirementMapper.toCreateEntity(dto);
        // Add level_id
        Level level = levelRepository.findLevelById(dto.levelId());
        levelRequirement.setLevel(level);
        // Persist
        levelRequirementRepository.persist(levelRequirement);
        return levelRequirementMapper.toCreateResponseDTO(levelRequirement);
    }

    // GetLevelRequirementsList - GET
    public List<LevelRequirementSummaryDTO> getLevelRequirementsList(int page, int size) {
        List<LevelRequirement> levelRequirements = levelRequirementRepository.getLevelRequirementsList(page, size);
        return levelRequirements.stream().map(
                levelRequirementMapper::toSummaryDTO).toList();
    }

    // GetLevelRequirementById - GET
    public LevelRequirementSummaryDTO getLevelRequirementById(Long levelRequirementId) {
        LevelRequirement levelRequirement = levelRequirementRepository.findLevelRequirementById(levelRequirementId);
        if (levelRequirement == null) {
            throw new NotFoundException("Level Requirement not Found!");
        }
        return levelRequirementMapper.toSummaryDTO(levelRequirement);
    }

    // UpdateLevelInfoEntity - PUT
    @Transactional
    public LevelRequirementSummaryDTO updateLevelInfoEntity(Long levelRequirementId,
            UpdateLevelRequirementInfoDTO dto) {
        LevelRequirement levelRequirement = levelRequirementRepository.findLevelRequirementById(levelRequirementId);
        if (levelRequirement == null) {
            throw new NotFoundException("Level Requirement not found!");
        }
        levelRequirementMapper.updateLevelRequirementInfoEntity(levelRequirement, dto);
        return levelRequirementMapper.toSummaryDTO(levelRequirement);
    }

    // UpdateLevelRequirementLevelDTO - PATCH
    @Transactional
    public LevelRequirementSummaryDTO UpdateLevelRequirementLevel(Long levelRequirementId, UpdateLevelRequirementLevelDTO dto) {
        LevelRequirement levelRequirement = levelRequirementRepository.findLevelRequirementById(levelRequirementId);
        // Fetch level
        Level level = levelRepository.findLevelById(dto.levelId());
        if (level == null) {
            throw new NotFoundException("Level not found!");
        }
        levelRequirement.setLevel(level);
        return levelRequirementMapper.toSummaryDTO(levelRequirement);
    }

    // DeleteLevelRequirement - DELETE
    @Transactional
    public void deleteLevelRequirement(Long levelRequirementId) {
        boolean deleted = levelRequirementRepository.deleteLevelRequirement(levelRequirementId);
        if (!deleted) {
            throw new NotFoundException("Level Requirement not found!");
        }
    }
}
