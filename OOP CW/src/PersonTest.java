import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;

import org.junit.Test;

public class PersonTest {
    @Test
    public void testGetName(){
        Person person = new Person();
        assertNull(person.getName());
    }
    @Test
    public void testGetSurname(){
        Person person = new Person();
        assertNull(person.getSurname());
    }
    @Test
    public void testGetDOB(){
        Person person = new Person();
        assertNull(person.getDOB());
    }
    @Test
    public void testGetMobileNo(){
        Person person = new Person();
        assertNull(person.getMobileNo());
    }
    @Test
    public void testSetName(){
        Person person = new Person();
        person.setName("Foo");
        assertEquals("Foo",person.getName());
    }
    @Test
    public void testSetSurname(){
        Person person = new Person();
        person.setSurname("Bar");
        assertEquals("Bar",person.getSurname());
    }
    @Test
    public void testSetDOB(){
        Person person = new Person();
        person.setdOB(LocalDate.parse("2022-02-13"));
        assertEquals(LocalDate.parse("2022-02-13"),person.getDOB());
    }
    @Test
    public void testSetMobileNo(){
        Person person = new Person();
        person.setMobileNo("1234567892");
        assertEquals("1234567892",person.getMobileNo());
    }
    @Test
    public void testToString(){
        Person person = new Person("Foo","Bar",LocalDate.parse("2022-02-13"),"1234567892");
        assertEquals("Foo"+"\n"+"Bar"+"\n"+"2022-02-13"+"\n"+"1234567892",person.toString());
    }
}
