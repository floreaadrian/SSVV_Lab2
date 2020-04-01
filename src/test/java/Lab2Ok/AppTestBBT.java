package Lab2Ok;

import Lab2Ok.domain.Student;
import Lab2Ok.repository.StudentXMLRepo;
import Lab2Ok.service.Service;
import Lab2Ok.validation.StudentValidator;
import Lab2Ok.validation.ValidationException;
import junit.framework.TestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AppTestBBT extends TestCase {
    StudentValidator studentValidator;
    String filenameStudent = "fisiere/test/Studenti.xml";
    private StudentXMLRepo studentXMLRepository;
    Service service;

    @Test
    public void test_tc_1_valid_group() {
        studentValidator = new StudentValidator();
        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        service = new Service(studentXMLRepository, studentValidator, null, null, null, null);
        Student student = new Student("11111","Gigel",333,"gigel@gigi.com");
        assertEquals(service.addStudent(student),student);
//        assertEquals(1,service.getAllStudenti());
    }

    @Test
    public void test_tc_2_invalid_group() {
        studentValidator = new StudentValidator();
        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        service = new Service(studentXMLRepository, studentValidator, null, null, null, null);
        Student student = new Student("12","Gigel",-111,"gigel@gigi.com");
        try {
            service.addStudent(student);
        } catch (ValidationException validationException) {
            assertThat(validationException.getMessage(), is("Grupa incorecta!"));
        }
    }
}
