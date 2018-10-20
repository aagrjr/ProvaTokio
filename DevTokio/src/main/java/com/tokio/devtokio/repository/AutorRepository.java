package com.tokio.devtokio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tokio.devtokio.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{

}
