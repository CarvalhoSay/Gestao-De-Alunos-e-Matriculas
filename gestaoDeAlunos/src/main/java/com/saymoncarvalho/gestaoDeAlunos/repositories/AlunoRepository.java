package com.saymoncarvalho.gestaoDeAlunos.repositories;

import com.saymoncarvalho.gestaoDeAlunos.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
