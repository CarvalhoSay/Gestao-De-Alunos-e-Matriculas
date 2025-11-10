package com.saymoncarvalho.gestaoDeAlunos.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MatriculaDTO(String codigoMatricula, String nomeCurso, LocalDate dataInicio) {
}
