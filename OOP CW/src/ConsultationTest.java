import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDateTime;

import org.junit.Test;

public class ConsultationTest {
    @Test
    public void testGetCost(){
        Consultation testC = new Consultation();
        assertEquals(0.0, testC.getCost(),1e-15);
    }
    @Test
    public void testGetDandTSlot(){
        Consultation testC = new Consultation();
        assertNull(testC.getDandTSlot());
    }
    @Test
    public void testGetNotes(){
        Consultation testC = new Consultation();
        try {
        assertEquals("", testC.getNotes());
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Nothing available to decode.");
        }
    }
    @Test
    public void testSetCost(){
        Consultation testC = new Consultation();
        testC.setCost(45.0);
        assertEquals(45.0, testC.getCost(),1e-15);
    }
    @Test
    public void testSetDandTSlot(){
        Consultation testC = new Consultation();
        testC.setDandTSlot(LocalDateTime.parse("2022-02-13T19:40"));
        assertEquals(LocalDateTime.parse("2022-02-13T19:40"),testC.getDandTSlot());
    }
    @Test
    public void testSetNotes(){
        Consultation testC = new Consultation();
        testC.setNotes("notes");
        assertEquals("notes",testC.getNotes());
    }
    @Test
    public void testToString(){
        Consultation testC = new Consultation(LocalDateTime.parse("2022-02-13T19:40"),45.0,"notes");
        assertEquals(LocalDateTime.parse("2022-02-13T19:40")+"\n"+45.0+"\n"+"notes",testC.toString());
    }
}
