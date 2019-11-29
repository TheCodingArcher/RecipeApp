package android.the.coding.archer.recipeapp.model;

public class RecipeStep {

    private int stepNumber;
    private String instruction;

    public RecipeStep(int stepNumber, String instruction) {
        this.stepNumber = stepNumber;
        this.instruction = instruction;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public String toString() {
        return "RecipeStep{" +
                ", stepNumber=" + stepNumber +
                ", instruction='" + instruction + '\'' +
                '}';
    }
}
