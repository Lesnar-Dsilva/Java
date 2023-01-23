import java.time.LocalDate;

public class Patient  extends Person{
    private String patientID;

    Patient(){}
    Patient(String name, String surname, LocalDate dOB, String mobileNo,String patientID){
        super(name,surname,dOB,mobileNo);
        this.patientID = patientID;
    }
    public String getPatientID() {
        return patientID;
    }
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }
    @Override
    public String toString() {
        return super.toString()+"\n"+patientID;
    }
}
