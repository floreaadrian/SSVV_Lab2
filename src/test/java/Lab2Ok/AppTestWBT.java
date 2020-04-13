package Lab2Ok;

import Lab2Ok.domain.Tema;
import Lab2Ok.repository.TemaXMLRepo;
import Lab2Ok.service.Service;
import Lab2Ok.validation.TemaValidator;
import Lab2Ok.validation.ValidationException;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AppTestWBT extends TestCase {
    private TemaValidator temaValidator;
    private String filenameTema = "fisiere/test/Teme.xml";
    private TemaXMLRepo temaXMLRepo;
    Service service;


    private void cleanUp() {
        service = new Service(null, null, temaXMLRepo, temaValidator, null, null);
        ArrayList<Tema> list = new ArrayList<Tema>();
        service.getAllTeme().forEach(list::add);
        for (Tema tema :
                list) {
            service.deleteTema(tema.getID());
        }
    }

    @Test
    public void test_tc_1_duplicate_id() {
        temaValidator = new TemaValidator();
        temaXMLRepo = new TemaXMLRepo(filenameTema);
        service = new Service(null, null, temaXMLRepo, temaValidator, null, null);
        Tema tema = new Tema("12", "Lab2121", 12, 12);
        service.addTema(tema);
        Tema tema1 = new Tema("12", "Lab2", 12, 12);
        assertNotSame(service.addTema(tema), tema1);
        cleanUp();
    }


    @Test
    public void test_tc_1_unique_id() {
        temaValidator = new TemaValidator();
        temaXMLRepo = new TemaXMLRepo(filenameTema);
        service = new Service(null, null, temaXMLRepo, temaValidator, null, null);
        Tema tema = new Tema("1211", "Lab2", 12, 12);
        assertNull(service.addTema(tema));
        cleanUp();
    }

    @Test
    public void test_tc_2_invalid_deadline() {
        temaValidator = new TemaValidator();
        temaXMLRepo = new TemaXMLRepo(filenameTema);
        service = new Service(null, null, temaXMLRepo, temaValidator, null, null);
        Tema tema = new Tema("1", "Lab2", 543, 12);
        try {
            service.addTema(tema);
        } catch (ValidationException validationException) {
            assertThat(validationException.getMessage(), is("Deadlineul trebuie sa fie intre 1-14."));
        }
        cleanUp();
    }

}
