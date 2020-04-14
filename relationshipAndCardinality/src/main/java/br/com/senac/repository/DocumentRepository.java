package br.com.senac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senac.domain.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer> {

}
