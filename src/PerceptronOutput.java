public class PerceptronOutput {

    private String equation;
    private int error;

    public PerceptronOutput setEquation(String equation) {
        this.equation = equation;
        return this;
    }

    public PerceptronOutput setError(int error) {
        this.error = error;
        return this;
    }

    public String getEquation() {
        return equation;
    }

    public int getError() {
        return error;
    }
}
