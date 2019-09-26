package com.talkcity.talkcity.controller;

import com.talkcity.talkcity.Exception.ResourceNotFoundException;
import com.talkcity.talkcity.entity.Problem;
import com.talkcity.talkcity.repository.ProblemRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "/api",description = "Problem")
@RequestMapping("/api")
public class ProblemController {

    @Autowired
    ProblemRepository problemRepository;

    @ApiOperation(value="GET ALL PROBLEMS")
    @GetMapping("/problem")
    public List<Problem> getAllProblems() {
        return problemRepository.findAll();
    }

    @ApiOperation(value = "CREATE PROBLEM")
    @PostMapping("/problem")
    public Problem create(@Validated @RequestBody Problem problem){
        return  problemRepository.save(problem);
    }
    @ApiOperation(value = "GET SINGLE PROBLEM")
    @GetMapping("/problem/{id}")
    public Problem getProblemId(@PathVariable(value = "id") Long problem){
        return problemRepository.findById(problem)
                .orElseThrow(() -> new ResourceNotFoundException("Nao encontrado", "id", problem));
    }
    
    @ApiOperation(value = "Updated")
    @PutMapping(value="/problem/{id}")
     public ResponseEntity<?> UpdatedProblem(@PathVariable("id") long id,@RequestBody Problem problemUp){
             return problemRepository.findById(id).
                     map(update->{
                          update.setDescription(problemUp.getDescription());
                          update.setCity(problemUp.getCity());
                          update.setPerson(problemUp.getPerson());
                          update.setDate(problemUp.getDate());
                          update.setLike_problem(problemUp.getLike_problem());
                          update.setDontlike_problem(problemUp.getDontlike_problem());
                          Problem updated = problemRepository.save(update);
                          return ResponseEntity.ok().body(updated);
                     }).orElse(ResponseEntity.notFound().build());  
    }
    
    @ApiOperation(value = "DELETE PROBLEM")
    @DeleteMapping("/problem/{id}")
    public ResponseEntity<?> DeleteProblem(@PathVariable(value = "id") Long id){
        Problem delete = problemRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Nao foi possivel apgar o registro","id",id));
        problemRepository.delete(delete);

        return ResponseEntity.ok().build();
    }

}
