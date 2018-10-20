package com.tokio.devtokio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tokio.devtokio.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

}
