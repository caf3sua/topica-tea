package com.topica.tea.service.mapper;

import com.topica.tea.domain.*;
import com.topica.tea.service.dto.ArticleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Article and its DTO ArticleDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ArticleMapper extends EntityMapper <ArticleDTO, Article> {
    
    
    default Article fromId(Long id) {
        if (id == null) {
            return null;
        }
        Article article = new Article();
        article.setId(id);
        return article;
    }
}