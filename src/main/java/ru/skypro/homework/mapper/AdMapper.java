package ru.skypro.homework.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

@Service
public class AdMapper implements MapperService<AdEntity, Ad> {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public AdMapper(UserRepository userRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Ad mappingToDto(AdEntity entity) {
        Ad result = new Ad();
        result.setAuthor(entity.getUser().getId());
        result.setImage(entity.getImage());
        result.setPk(entity.getId());
        result.setPrice(entity.getPrice());
        result.setTitle(entity.getTitle());
        return result;
    }

    @Override
    public AdEntity mappingToEntity(Ad dto) {
        AdEntity adEntity = new AdEntity();
        adEntity.setId(dto.getPk());
        adEntity.setImage(dto.getImage());
        adEntity.setPrice(dto.getPrice());
        adEntity.setTitle(dto.getTitle());
        adEntity.setUser(userRepository.findById(dto.getAuthor()).orElse(null));
        adEntity.setCommentEntities(commentRepository.findAllByAdEntity_Id(dto.getPk()));
        return adEntity;
    }
}
