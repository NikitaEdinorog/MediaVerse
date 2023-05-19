package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.DTOs;

import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.util.ToStringBuilder;
import org.springframework.http.HttpStatus;

public enum ServiceError
{
    NOT_FOUND(HttpStatus.NOT_FOUND),
    UNKNOWN(HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(HttpStatus.BAD_REQUEST);


    private HttpStatus status;


    ServiceError(HttpStatus httpStatus)
    {
        this.status = httpStatus;
    }

    public HttpStatus getStatus()
    {
        return status;
    }


    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                .append("status", status)
                .toString();
    }
}
