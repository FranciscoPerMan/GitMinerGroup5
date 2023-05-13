package aiss.gitminer.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Project could not be found", code = HttpStatus.NOT_FOUND)
public class ProjectNotFoundException extends Exception{
}
