package Lab2Ok;

import Lab2Ok.domain.Student;
import Lab2Ok.domain.Tema;
import Lab2Ok.repository.StudentXMLRepo;
import Lab2Ok.repository.TemaXMLRepo;
import Lab2Ok.service.Service;
import Lab2Ok.validation.StudentValidator;
import Lab2Ok.validation.TemaValidator;
import Lab2Ok.validation.ValidationException;
import junit.framework.TestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AppTestWBT extends TestCase {
    private TemaValidator temaValidator;
    private String filenameTema = "fisiere/test/Teme.xml";
    private TemaXMLRepo temaXMLRepo;
    Service service;

    @Test
    public void test_tc_1_duplicate_id() {
        temaValidator = new TemaValidator();
        temaXMLRepo = new TemaXMLRepo(filenameTema);
        service = new Service(null, null, temaXMLRepo, temaValidator, null, null);
        Tema tema = new Tema("1","Lab2",12,12);
        assertNull(service.addTema(tema));
    }


    @Test
    public void test_tc_1_unique_id() {
        temaValidator = new TemaValidator();
        temaXMLRepo = new TemaXMLRepo(filenameTema);
        service = new Service(null, null, temaXMLRepo, temaValidator, null, null);
        Tema tema = new Tema("121","Lab2",12,12);
        assertEquals(tema,service.addTema(tema));
    }

    @Test
    public void test_tc_2_invalid_deadline() {
        temaValidator = new TemaValidator();
        temaXMLRepo = new TemaXMLRepo(filenameTema);
        service = new Service(null, null, temaXMLRepo, temaValidator, null, null);
        Tema tema = new Tema("1","Lab2",543,12);
        try {
            service.addTema(tema);
        } catch (ValidationException validationException) {
            assertThat(validationException.getMessage(), is("Deadlineul trebuie sa fie intre 1-14."));
        }
    }
}
