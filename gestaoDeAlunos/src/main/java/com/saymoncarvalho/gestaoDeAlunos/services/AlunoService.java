package com.saymoncarvalho.gestaoDeAlunos.services;

import com.saymoncarvalho.gestaoDeAlunos.dto.AlunoRequest;
import com.saymoncarvalho.gestaoDeAlunos.dto.AlunoResponse;
import com.saymoncarvalho.gestaoDeAlunos.dto.MatriculaDTO;
import com.saymoncarvalho.gestaoDeAlunos.mapper.AlunoMapper;
import com.saymoncarvalho.gestaoDeAlunos.models.Aluno;
import com.saymoncarvalho.gestaoDeAlunos.models.Matricula;
import com.saymoncarvalho.gestaoDeAlunos.repositories.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    AlunoMapper alunoMapper;

    @Autowired
    AlunoRepository alunoRepository;

    public AlunoResponse salvar(AlunoRequest request){
        Aluno aluno = alunoMapper.toEntity(request);

        if (aluno.getMatriculas() != null) {
            aluno.getMatriculas().forEach(m -> m.setAluno(aluno));
        }

        Aluno salvo = alunoRepository.save(aluno);
        return alunoMapper.toResponse(aluno);
    }

    public List<AlunoResponse> listarTodos(){
       List<Aluno> list = alunoRepository.findAll();

       List<AlunoResponse> responses = new ArrayList<>();

       for(Aluno a : list){
          AlunoResponse alunoResponse =  alunoMapper.toResponse(a);
          responses.add(alunoResponse);
       }

       return responses;
    }

    public List<MatriculaDTO> listarMatriculasPorID(Long id){
        Aluno aluno =  alunoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Aluno nao encontrado"));
        List<Matricula> matriculaList = aluno.getMatriculas();


        List<MatriculaDTO> matriculaDTOList = new ArrayList<>();

        for(Matricula m : matriculaList){
          matriculaDTOList.add(new MatriculaDTO(m.getCodigoMatricula(),m.getNomeCurso(),m.getDataInicio()));
        }


        return matriculaDTOList;

    }

    public void removerAluno(Long id){
        if(!alunoRepository.existsById(id)){
            throw new EntityNotFoundException("Id do aluno nao encontrado");
        }

        alunoRepository.deleteById(id);
    }

    public AlunoResponse atualizarAluno(Long id, AlunoRequest request){
        Aluno a = alunoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Aluno nao encontrado"));

        a.setName(request.nome());
        a.setTelefone(request.telefone());
        a.setDataNascimento(request.dataNascimento());

        for(MatriculaDTO m : request.matriculas()){
            Matricula matricula = new Matricula();

            matricula.setCodigoMatricula(m.codigoMatricula());
            matricula.setNomeCurso(m.nomeCurso());
            matricula.setDataInicio(m.dataInicio());

            a.getMatriculas().add(matricula);

        }

        return alunoMapper.toResponse(alunoRepository.save(a));
    }

}
