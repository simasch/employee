package hr;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class QueryTest {

    private final static Logger LOGGER = Logger.getLogger(QueryTest.class);

    private static EntityManagerFactory emf;
    private static EntityManager em;

    private static Integer departmentId;

    @Test
    public void findDepartment() {
        LOGGER.info(">>>>> findDepartment <<<<<");

        Department department = em.find(Department.class, departmentId);

        department.getEmployees().forEach(employee -> LOGGER.info(employee.getAddress().getCity().getCountry().getName()));
    }

    @Test
    public void findAllEmployees() {
        LOGGER.info(">>>>> findAllEmployees <<<<<");

        List<Employee> employees = em.createQuery("select e from Employee e", Employee.class).getResultList();

        employees.forEach(employee -> LOGGER.info(employee.getAddress().getCity().getCountry().getName()));
    }

    @BeforeClass
    public static void beforeClass() {
        emf = Persistence.createEntityManagerFactory("hr");
        em = emf.createEntityManager();

        departmentId = TestDataCreator.createTestData(em);
    }

    @AfterClass
    public static void afterClass() {
        em.close();
        emf.close();
    }

    @Before
    public void before() {
        em.clear();
        emf.getCache().evictAll();
    }
}
