package com.talkcity.talkcity.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private  String name;
    private  String problem;
    private Object fieldValue;

    public ResourceNotFoundException(String name, String problem, Object fieldValue){
        super(String.format("%s not found s%: '%s'",name, problem,fieldValue));
        this.name=name;
        this.problem = problem;

    }

    public String getName() {
        return name;
    }

    public String getProblem() {
        return problem;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
