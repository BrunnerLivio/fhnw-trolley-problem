package ch.fhnw.webec.trolleyproblem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.webec.trolleyproblem.entities.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

}