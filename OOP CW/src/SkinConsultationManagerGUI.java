import java.time.LocalDate;
import java.util.ArrayList;

interface SkinConsultationManagerGUI {
    void createDoctor(String name, String surname, LocalDate dOB, String mobileNo, String mLN,
            String specialisation, ArrayList<Doctor> doctorsList);
    void deleteDoctor(ArrayList<Doctor> doctorsList, String mLN);
    void printDoctors(ArrayList<Doctor> doctorsList);
    void create();
    void save(ArrayList<Doctor> doctorsList);
    void delete();
    void mainMenu();
    void menu();
    
}
