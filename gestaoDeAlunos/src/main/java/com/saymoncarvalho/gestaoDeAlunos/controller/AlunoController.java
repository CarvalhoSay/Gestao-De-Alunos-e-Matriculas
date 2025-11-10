package com.saymoncarvalho.gestaoDeAlunos.controller;

import com.saymoncarvalho.gestaoDeAlunos.dto.AlunoRequest;
import com.saymoncarvalho.gestaoDeAlunos.dto.AlunoResponse;
import com.saymoncarvalho.gestaoDeAlunos.dto.MatriculaDTO;
import com.saymoncarvalho.gestaoDeAlunos.services.AlunoService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;


    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<AlunoResponse> criar (@Valid @RequestBody AlunoRequest alunoRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.salvar(alunoRequest));
    }

    @GetMapping
    public List<AlunoResponse> listarTodos(){
        return alunoService.listarTodos();
    }

    @GetMapping("/{id}/matriculas")
    public List<MatriculaDTO> listarMatriculas(@PathVariable Long id){
        return alunoService.listarMatriculasPorID(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponse> atualizar (@PathVariable Long id, @RequestBody AlunoRequest alunoRequest){
        return ResponseEntity.ok(alunoService.atualizarAluno(id, alunoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover (@PathVariable Long id){
        alunoService.removerAluno(id);

        return ResponseEntity.noContent().build();
    }

}
