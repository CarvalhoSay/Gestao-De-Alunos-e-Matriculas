package com.saymoncarvalho.gestaoDeAlunos.mapper;

import com.saymoncarvalho.gestaoDeAlunos.dto.AlunoRequest;
import com.saymoncarvalho.gestaoDeAlunos.dto.AlunoResponse;
import com.saymoncarvalho.gestaoDeAlunos.dto.MatriculaDTO;
import com.saymoncarvalho.gestaoDeAlunos.models.Aluno;
import com.saymoncarvalho.gestaoDeAlunos.models.Matricula;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class AlunoMapper {

    public Aluno toEntity(AlunoRequest request){
        Aluno aluno = new Aluno();

       aluno.setName(request.nome());
       aluno.setTelefone(request.telefone());
       aluno.setDataNascimento(request.dataNascimento());

        List<Matricula> matriculas = new ArrayList<>();

        for (MatriculaDTO m : request.matriculas()){

            Matricula matricula = new Matricula();

           matricula.setNomeCurso(m.nomeCurso());
           matricula.setCodigoMatricula(m.codigoMatricula());
           matricula.setDataInicio(m.dataInicio());

           matricula.setAluno(aluno);
           matriculas.add(matricula);

        }

        aluno.setMatriculas(matriculas);

        return aluno;
    }

    public AlunoResponse toResponse(Aluno aluno) {

        List<MatriculaDTO> matriculaDTO = new ArrayList<>();

        for(Matricula m : aluno.getMatriculas()){
            MatriculaDTO dto = new MatriculaDTO(m.getCodigoMatricula(),m.getNomeCurso(),m.getDataInicio());

            matriculaDTO.add(dto);
        }

        return new AlunoResponse(aluno.getId(),
                aluno.getName(),
                aluno.getTelefone(),
                aluno.getDataNascimento(),
                matriculaDTO);
    }
}
