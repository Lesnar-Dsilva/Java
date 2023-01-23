import java.time.LocalDate;

public class Person {
    private String name;
    private String surname;
    private LocalDate dOB;
    private String mobileNo;

    Person(){}
    Person(String name, String surname, LocalDate dOB, String mobileNo){
        this.name = name;
        this.surname = surname;
        this.dOB = dOB;
        this.mobileNo = mobileNo;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public LocalDate getDOB() {
        return dOB;
    }
    public void setdOB(LocalDate dOB) {
        this.dOB = dOB;
    }
    public String getMobileNo() {
        return mobileNo;
    }
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    @Override
    public String toString() {
        return name+"\n"+surname+"\n"+dOB+"\n"+mobileNo;
    }
}
