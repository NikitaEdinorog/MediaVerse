package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.DTOs;

import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.util.SimpleResponse;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.util.ToStringBuilder;

public class ServiceResponse<T> extends SimpleResponse {

    private T data;

    private ServiceError serviceError;


    public ServiceResponse(boolean success, ServiceError serviceError, T data) {
        super(success);
        this.data = data;
        this.serviceError = serviceError;
    }


    public ServiceError getServiceError() {
        return serviceError;
    }


    public T getData() {
        return data;
    }


    /**
     * Builder to easy response creation
     *
     * @param <T> - response data
     */
    public static class Builder<T> {
        public ServiceResponse<T> buildSuccess(T data) {
            return new ServiceResponse<T>(true, null, data);
        }


        public ServiceResponse<T> buildError(ServiceError serviceError) {
            return new ServiceResponse<T>(false, serviceError, null);
        }

        public ServiceResponse<T> buildErrorWithMessage(ServiceError serviceError, T data) {
            return new ServiceResponse<T>(false, serviceError, data);
        }
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("data", data)
                .append("serviceError", serviceError)
                .toString();
    }


}
