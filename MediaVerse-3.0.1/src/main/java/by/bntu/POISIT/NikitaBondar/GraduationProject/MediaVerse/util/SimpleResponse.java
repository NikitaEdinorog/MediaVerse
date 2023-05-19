package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.util;

public class SimpleResponse {
    private boolean success;


    public SimpleResponse() {
    }


    public SimpleResponse(boolean success) {
        this.success = success;
    }


    public boolean isSuccess() {
        return success;
    }


    public boolean isError() {
        return !success;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("success", success)
                .toString();
    }
}