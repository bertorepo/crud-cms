package com.hubert.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hubert.crud.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
