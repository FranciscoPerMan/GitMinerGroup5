package aiss.gitminer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(reason = "Project already exists", code = HttpStatus.CONFLICT)
public class ProjectAlreadyExistsException extends Exception{
}

