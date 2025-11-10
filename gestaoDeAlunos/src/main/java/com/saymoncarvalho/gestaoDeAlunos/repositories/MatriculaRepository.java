package com.saymoncarvalho.gestaoDeAlunos.repositories;

import com.saymoncarvalho.gestaoDeAlunos.models.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
}
