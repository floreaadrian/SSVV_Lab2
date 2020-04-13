package Lab2Ok;

import Lab2Ok.domain.Student;
import Lab2Ok.repository.StudentXMLRepo;
import Lab2Ok.service.Service;
import Lab2Ok.validation.StudentValidator;
import Lab2Ok.validation.ValidationException;
import junit.framework.TestCase;
import org.junit.Assert;
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
        Student student = new Student("11111", "Gigel", 333, "gigel@gigi.com");
        Assert.assertEquals(service.addStudent(student), student);
    }

    @Test
    public void test_tc_2_invalid_id() {
        studentValidator = new StudentValidator();
        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        service = new Service(studentXMLRepository, studentValidator, null, null, null, null);
        Student student = new Student("", "Gigel", 111, "gigel@gigi.com");
        try {
            service.addStudent(student);
        } catch (ValidationException validationException) {
            assertThat(validationException.getMessage(), is("Id incorect!"));
        }
        Student student1 = new Student(null, "Gigel", 111, "gigel@gigi.com");
        try {
            service.addStudent(student1);
        } catch (ValidationException validationException) {
            assertThat(validationException.getMessage(), is("Id incorect!"));
        }
    }

    @Test
    public void test_tc_3_invalid_name() {
        studentValidator = new StudentValidator();
        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        service = new Service(studentXMLRepository, studentValidator, null, null, null, null);
        Student student = new Student("12", "", 111, "gigel@gigi.com");
        try {
            service.addStudent(student);
        } catch (ValidationException validationException) {
            assertThat(validationException.getMessage(), is("Nume incorect!"));
        }
        Student student1 = new Student("12", null, 111, "gigel@gigi.com");
        try {
            service.addStudent(student1);
        } catch (ValidationException validationException) {
            assertThat(validationException.getMessage(), is("Nume incorect!"));
        }
    }

    @Test
    public void test_tc_4_invalid_email() {
        studentValidator = new StudentValidator();
        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        service = new Service(studentXMLRepository, studentValidator, null, null, null, null);
        Student student = new Student("12", "Gigel", 111, "");
        try {
            service.addStudent(student);
        } catch (ValidationException validationException) {
            assertThat(validationException.getMessage(), is("Email incorect!"));
        }
        Student student1 = new Student("12", "Gigel", 111, null);
        try {
            service.addStudent(student1);
        } catch (ValidationException validationException) {
            assertThat(validationException.getMessage(), is("Email incorect!"));
        }
    }

    @Test
    public void test_tc_5_all_invalid() {
        studentValidator = new StudentValidator();
        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        service = new Service(studentXMLRepository, studentValidator, null, null, null, null);
        Student student = new Student(null, null, -11, null);
        try {
            service.addStudent(student);
        } catch (Exception exception) {
            assertThat(exception.getClass().toString(), is(ValidationException.class.toString()));
        }
    }

}
