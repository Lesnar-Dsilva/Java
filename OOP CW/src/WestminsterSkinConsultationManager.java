import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {
    // DO NOT CHANGE TO ANOTHER ACCESS MODIFIER, IT'S REQUIRED FOR TESTING
    protected ArrayList<Doctor> doctorsList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private String input = "";
    private String[] labels = { "Name: ", "Surname: ", "DOB: ", "Phone No: ", "Medical License No: ",
            "Specialisation: " };
    private WestminsterSkinConsultationManagerGUI gui = new WestminsterSkinConsultationManagerGUI();
    File file = new File("doctors.txt");

    public boolean duplicate(String mLN){
        for(Doctor d: doctorsList){
            if(d.getMedicalLicenseNo().equals(mLN)){
                return true;
            }
        }
        return false;
    }
    public void addDoctor() {
        try {
            System.out.println("Enter Name: ");
            String n = scanner.nextLine();
            System.out.println("Enter Surname: ");
            String sN = scanner.nextLine();
            System.out.println("Enter Date of Birth: ");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            System.out.println("Enter Phone No: ");
            String pN = scanner.nextLine();
            System.out.println("Enter Medical License No: ");
            String mLN = scanner.nextLine();
            System.out.println("Enter Specialisation: ");
            String s = scanner.nextLine();
            if(doctorsList.size() < 10 && !duplicate(mLN)){
                doctorsList.add(new Doctor(n, sN, date, pN, mLN, s));
            } else {
                System.out.println("No more space available, cannot add more to list, or duplicate doctor.");
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("DATE ERROR, write date as YYYY-MM-DD");
        }
    }

    public void deleteDoctor() {
        try {
        System.out.println("Enter Medical License No for deletion: ");
        String mLND = scanner.nextLine();
        for (Doctor doc : doctorsList) {
            if (doc.getMedicalLicenseNo().equals(mLND)) {
                System.out.println("******************DELETED******************\n\n");
                System.out.println(labels[0] + doc.getName() + "\n" + labels[1] + doc.getSurname() + "\n"
                    + labels[2] + doc.getDOB() + "\n" + labels[3] + doc.getMobileNo() + "\n"
                    + labels[4] + doc.getMedicalLicenseNo() + "\n" + labels[5]
                    + doc.getSpecialisation() + "\n\n");
                System.out.println("******************DELETED******************");
                doctorsList.remove(doc);
                System.out.println(doctorsList.size()+" Doctors left.");
            }
        }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void printList() {
        Collections.sort(doctorsList, (d1, d2) -> d1.getSurname().compareTo(d2.getSurname()));
        for (Doctor doc : doctorsList) {
            System.out.println(labels[0] + doc.getName() + "\n" + labels[1] + doc.getSurname() + "\n"
                    + labels[2] + doc.getDOB() + "\n" + labels[3] + doc.getMobileNo() + "\n"
                    + labels[4] + doc.getMedicalLicenseNo() + "\n" + labels[5]
                    + doc.getSpecialisation() + "\n\n");
        }
    }

    public void write() {
        try {
            File file = new File("doctors.txt");
            if (file.createNewFile())
                System.out.println("File created: " + file.getName());
            else
                System.out.println("File already exists.");
            FileWriter writer = new FileWriter(file);
            for (Doctor doc : doctorsList) {
                writer.append(labels[0] + doc.getName() + "\n" + labels[1] + doc.getSurname() + "\n"
                        + labels[2] + doc.getDOB() + "\n" + labels[3] + doc.getMobileNo() + "\n" + labels[4]
                        + doc.getMedicalLicenseNo() + "\n" + labels[5] + doc.getSpecialisation() + "\n\n");

            }
            System.out.println("Successfully wrote to the file.");
            writer.close();
        } catch (Exception e) {
            System.out.println("WRITE ERROR");
            e.printStackTrace();
        }
    }

    public void read(File file) {
        try {
            Scanner scanner = new Scanner(file);
            int i = 0;
            String name = "";
            String surname = "";
            String dOB = "";
            String phoneNo = "";
            String medicalLicenseNo = "";
            String specialisation = "";
            if (file.exists())
                while (scanner.hasNext()) {
                    String t = scanner.nextLine();
                    t = t.replaceAll(".+: ", "");
                    if (t.isEmpty() || t.isBlank())
                        continue;
                    if (i == 0)
                        name = t;
                    else if (i == 1)
                        surname = t;
                    else if (i == 2)
                        dOB = t;
                    else if (i == 3)
                        phoneNo = t;
                    else if (i == 4)
                        medicalLicenseNo = t;
                    else if (i == 5)
                        specialisation = t;
                    i++;
                    if (i == 6) {
                        i = 0;
                        if(doctorsList.size() < 10 && !duplicate(medicalLicenseNo)){
                        doctorsList.add(new Doctor(name, surname, LocalDate.parse(dOB), phoneNo,
                                medicalLicenseNo, specialisation));}else{System.out.println("No more space available, cannot add more to list, or duplicate doctor");}
                    }
                }
            scanner.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("DATE ERROR, write date as YYYY-MM-DD");
            System.out.println("RETRIEVE ERROR");
        }
    }

    public void menu() {
        while (!input.equals("q")) {
            System.out.println("\n**********MENU**********\n");
            System.out.println("1. Add a new doctor");
            System.out.println("2. Delete a doctor");
            System.out.println("3. Print the list of the doctors");
            System.out.println("4. Save in a file");
            System.out.println("5. Retrieve from a file");
            System.out.println("6. Open GUI version");
            System.out.println("Q: Quit");
            System.out.println("\n**********MENU**********\n");
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    addDoctor();
                    break;
                case "2":
                    deleteDoctor();
                    break;
                case "3":
                    printList();
                    break;
                case "4":
                    write();
                    break;
                case "5":
                    read(file);
                    break;
                case "6":
                    gui.menu();
                    break;
                case "q":
                    input = "q"; 
                    break;
                default:
                    System.out.println("Enter only commands.");
            }
        }
    }
}
