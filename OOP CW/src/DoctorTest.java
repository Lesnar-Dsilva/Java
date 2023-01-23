import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;

import org.junit.Test;

public class DoctorTest {
    @Test
    public void testGetMLN(){
        Doctor d = new Doctor();
        assertNull(d.getMedicalLicenseNo());
    }
    @Test
    public void testGetS(){
        Doctor d = new Doctor();
        assertNull(d.getSpecialisation());
    }
    @Test
    public void testSetMLN(){
        Doctor d = new Doctor();
        d.setMedicalLicenseNo("1");
        assertEquals("1",d.getMedicalLicenseNo());
    }
    @Test
    public void testSetS(){
        Doctor d = new Doctor();
        d.setSpecialisation("2");
        assertEquals("2",d.getSpecialisation());
    }
    @Test
    public void testToString(){
        Doctor d = new Doctor("John","Smith",LocalDate.parse("2022-02-13"),"1234567890","1","2");
        assertEquals("John"+"\n"+"Smith"+"\n"+"2022-02-13"+"\n"+"1234567890"+"\n"+"1"+"\n"+"2",d.toString());
    }
}
