package com.hubert.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hubert.crud.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
