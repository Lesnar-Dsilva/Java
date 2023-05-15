import java.time.LocalDateTime;

public class Consultation {
    private LocalDateTime dAndTSlot;
    private double cost;
    private String notes;

    Consultation(){}
    Consultation(LocalDateTime dAndTSlot, double cost, String notes){
        this.dAndTSlot = dAndTSlot;
        this.cost = cost;
        this.notes = notes;
    }
    public LocalDateTime getDandTSlot() {
        return dAndTSlot;
    }
    public void setDandTSlot(LocalDateTime dAndTSlot) {
        this.dAndTSlot = dAndTSlot;
    }
    public double getCost() {
        return  cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String toString(){
        return dAndTSlot+"\n"+cost+"\n"+notes;
    }
}
