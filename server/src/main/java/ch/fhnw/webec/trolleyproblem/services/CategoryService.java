package ch.fhnw.webec.trolleyproblem.services;

import ch.fhnw.webec.trolleyproblem.dtos.CategoryDto;
import ch.fhnw.webec.trolleyproblem.mappers.CategoryMapper;
import ch.fhnw.webec.trolleyproblem.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<CategoryDto> findAll() {
        return CategoryMapper.INSTANCE.categoryEntitiesToCategoryDtos(repository.findAll());
    }
}
