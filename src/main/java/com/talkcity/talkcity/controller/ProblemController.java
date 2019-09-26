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
    

    @ApiOperation(value = "EDIT PROBLEM")
    @PutMapping("/problem/{id}")
    public  Problem UpdatedProblem(@PathVariable(value = "id") Long problem,Problem problemUp){
        Problem update = problemRepository.findById(problem).orElseThrow(()->new ResourceNotFoundException("Nao foi possivel alterar","id",problem));
               update.setDescription(problemUp.getDescription());
               update.setCity(problemUp.getCity());
               update.setPerson(problemUp.getPerson());
               update.setDate(problemUp.getDate());
               update.setLike_problem(problemUp.getLike_problem());
               update.setDontlike_problem(problemUp.getDontlike_problem());

        return problemRepository.save(update);
    }
    
    
    @ApiOperation(value="Like Problem")
    @PatchMapping("/problem/{id}/like")
    public Problem updatedLProblem(@Pathvariable(value="id"),@RequestBody String like){
        Problem up = problemRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Nao foi possivel apgar o registro","id",id));
        up.ifPresent(problem->{up.setlike_problem(like)});
        
        return ReponseEntity.ok().build();  
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
