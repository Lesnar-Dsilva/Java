import java.io.IOException;
import java.time.LocalDateTime;

public class Consultation {
    private LocalDateTime dAndTSlot;
    private double cost;
    private byte[] encoded;
    private byte[] decoded;
    WestminsterSkinConsultationManagerGUI x = new WestminsterSkinConsultationManagerGUI();
    Consultation(){}
    Consultation(LocalDateTime dAndTSlot, double cost, String notes){
        this.dAndTSlot = dAndTSlot;
        this.cost = cost;
        try {
            encoded = x.encrypt(notes);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
        decoded = x.decrypt(encoded);
        return new String(decoded);
    }
    public void setNotes(String notes) {
        try {
            encoded = x.encrypt(notes);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public String toString(){
        decoded = x.decrypt(encoded);
        return dAndTSlot+"\n"+cost+"\n"+new String(decoded);
    }
}
