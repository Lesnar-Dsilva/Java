import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;

import org.junit.Test;

public class PatientTest {
    @Test
    public void testGetPatientID(){
        Patient p = new Patient();
        assertNull(p.getPatientID());
    }
    @Test
    public void testSetPatientID(){
        Patient p = new Patient();
        p.setPatientID("5");
        assertEquals("5",p.getPatientID());
    }
    @Test
    public void testToString(){
        Patient p = new Patient("John","Doe",LocalDate.parse("2022-02-13"),"1234567891","5");
        assertEquals("John"+"\n"+"Doe"+"\n"+"2022-02-13"+"\n"+"1234567891"+"\n"+"5",p.toString());
    }
}
