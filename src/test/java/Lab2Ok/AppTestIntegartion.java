package Lab2Ok;

import Lab2Ok.domain.Nota;
import Lab2Ok.domain.Student;
import Lab2Ok.domain.Tema;
import Lab2Ok.repository.NotaXMLRepo;
import Lab2Ok.repository.StudentXMLRepo;
import Lab2Ok.repository.TemaXMLRepo;
import Lab2Ok.service.Service;
import Lab2Ok.validation.NotaValidator;
import Lab2Ok.validation.StudentValidator;
import Lab2Ok.validation.TemaValidator;
import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class AppTestIntegartion extends TestCase {
    private String filenameStudent = "fisiere/test/Studenti.xml";
    private String filenameTema = "fisiere/test/Teme.xml";
    private String filenameNote = "fisiere/test/Note.xml";
    private TemaValidator temaValidator = new TemaValidator();
    private TemaXMLRepo temaXMLRepo = new TemaXMLRepo(filenameTema);
    private StudentValidator studentValidator = new StudentValidator();
    ;
    private StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
    ;
    private NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepo);
    private NotaXMLRepo notaXMLRepo = new NotaXMLRepo(filenameNote);
    Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);
    ;

    private void cleanUpTema() {
        ArrayList<Tema> list = new ArrayList<Tema>();
        service.getAllTeme().forEach(list::add);
        for (Tema tema :
                list) {
            service.deleteTema(tema.getID());
        }
    }

    private void cleanUpStudent() {
        ArrayList<Student> list = new ArrayList<Student>();
        service.getAllStudenti().forEach(list::add);
        for (Student student :
                list) {
            service.deleteStudent(student.getID());
        }
    }


    @Test
    public void test_add_student() {
        cleanUpStudent();
        Student student = new Student("12", "nu", 11, "nu@n.com");
        assertNull(service.addStudent(student));
    }

    @Test
    public void test_add_tema() {
        cleanUpTema();
        Tema tema = new Tema("1211", "Lab2", 5, 2);
        assertNull(service.addTema(tema));
    }

    @Test
    public void test_add_grade() {
        service.deleteNota("1111");
        Nota nota = new Nota("1111", "12", "1211", 9, LocalDate.of(2018,11,1));
        assertNull(service.addNota(nota, "super gucci"));
    }

    @Test
    public void test_integration(){
        test_add_tema();
        test_add_student();
        test_add_grade();
    }
}
