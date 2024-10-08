package br.com.brain.domain.professor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, String> {
    Optional<Professor> findByMatricula(String matricula);
}
