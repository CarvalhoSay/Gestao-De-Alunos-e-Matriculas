package com.saymoncarvalho.gestaoDeAlunos.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record AlunoResponse(Long id, String name, String telefone, LocalDate dataNascimento, List<MatriculaDTO> matriculas) {
}
