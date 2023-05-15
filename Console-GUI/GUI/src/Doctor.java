import java.time.LocalDate;

public class Doctor extends Person{
    private String medicalLicenseNo;
    private String specialisation;

    Doctor(){}
    Doctor(String name, String surname, LocalDate dOB, String mobileNo,String mLN, String specialisation){
        super(name, surname, dOB, mobileNo);
        this.medicalLicenseNo = mLN;
        this.specialisation = specialisation;
    }
    public String getMedicalLicenseNo() {
        return medicalLicenseNo;
    }
    public void setMedicalLicenseNo(String medicalLicenseNo) {
        this.medicalLicenseNo = medicalLicenseNo;
    }
    public String getSpecialisation() {
        return specialisation;
    }
    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }
    @Override
    public String toString() {
        return super.toString()+"\n"+medicalLicenseNo+"\n"+specialisation;
    }
}
