import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

public class WestminsterSkinConsultationManagerTest {
    @Test
    public void addDoctor() {
        WestminsterSkinConsultationManager console = new WestminsterSkinConsultationManager();
        Doctor d = new Doctor();
        while (console.doctorsList.size() < 10) {
            console.doctorsList.add(d);
        }
        assertTrue(console.doctorsList.size() == 10);
    }

    @Test
    public void deleteDoctor() {
        WestminsterSkinConsultationManager console = new WestminsterSkinConsultationManager();
        Doctor d = new Doctor();
        while (console.doctorsList.size() < 10) {
            console.doctorsList.add(d);
        }
        if (console.doctorsList.size() == 10)
            console.doctorsList.remove(d);
        assertTrue(console.doctorsList.size() == 9);
    }

    @Test
    public void printDoctor() {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        WestminsterSkinConsultationManager console = new WestminsterSkinConsultationManager();
        Doctor d = new Doctor("John", "Doe", LocalDate.parse("2022-02-13"), "1234567892", "1", "2");
        String[] labels = { "Name: ", "Surname: ", "DOB: ", "Phone No: ", "Medical License No: ",
                "Specialisation: " };
        System.setOut(new PrintStream(outStream));
        console.doctorsList.add(d);
        console.printList();
        System.err.println(outStream.toString().trim());
        Assert.assertEquals(labels[0] + d.getName() + "\n" + labels[1] + d.getSurname() + "\n"
                + labels[2] + d.getDOB() + "\n" + labels[3] + d.getMobileNo() + "\n"
                + labels[4] + d.getMedicalLicenseNo() + "\n" + labels[5]
                + d.getSpecialisation(), outStream.toString().trim());
    }

    @Test
    public void saveDoctor() {
        WestminsterSkinConsultationManager console = new WestminsterSkinConsultationManager();
        Doctor d = new Doctor("John", "Doe", LocalDate.parse("2022-02-13"), "1234567892", "1", "2");
        console.doctorsList.add(d);
        console.write();
        File file = new File("doctors.txt");
        System.out.println(file.getAbsolutePath());
        assertTrue(file.exists());
    }

    @Test
    public void readDoctor() throws FileNotFoundException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        WestminsterSkinConsultationManager console = new WestminsterSkinConsultationManager();
        File file = new File("doctors.txt");
        String[] labels = { "Name: ", "Surname: ", "DOB: ", "Phone No: ", "Medical License No: ", "Specialisation: " };
        Doctor d = new Doctor("John", "Doe", LocalDate.parse("2022-02-13"), "1234567892", "1", "2");

        System.setOut(new PrintStream(outStream));
        console.read(file);
        console.printList();
        System.err.println(outStream.toString().trim());
        Assert.assertEquals(labels[0] + d.getName() + "\n" + labels[1] + d.getSurname() + "\n"
                + labels[2] + d.getDOB() + "\n" + labels[3] + d.getMobileNo() + "\n"
                + labels[4] + d.getMedicalLicenseNo() + "\n" + labels[5]
                + d.getSpecialisation(), outStream.toString().trim());
    }
}

/*
 * #2022-11-24: Completed all 4 tests for functions related to the console version of the application.
 * #2022-11-25: Completed 1 more test.
 */