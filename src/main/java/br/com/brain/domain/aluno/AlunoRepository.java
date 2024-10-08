package br.com.brain.domain.aluno;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, String> {
    Optional<Aluno> findByMatricula(String matricula);
}
