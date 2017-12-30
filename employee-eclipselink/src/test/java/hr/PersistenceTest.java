package hr;

import org.apache.log4j.Logger;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PersistenceTest {

    private final static Logger LOGGER = Logger.getLogger(PersistenceTest.class);

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;
    private static Integer departmentId;

    @BeforeClass
    public static void beforeClass() {
        emf = Persistence.createEntityManagerFactory("hr");
        em = emf.createEntityManager();
        tx = em.getTransaction();

        tx.begin();

        Department department = new Department();
        department.setName("Sales");

        Employee john = new Employee();
        john.setName("John");
        Address bern = new Address();
        bern.setCity("Bern");
        john.setAddress(bern);
        department.addEmployee(john);

        Employee mary = new Employee();
        mary.setName("Mary");
        department.addEmployee(mary);
        Address zurich = new Address();
        zurich.setCity("Zurich");
        mary.setAddress(zurich);

        em.persist(department);

        departmentId = department.getId();

        em.flush();
        tx.commit();

        em.clear();
        emf.getCache().evictAll();
    }

    @Before
    public void before() {
        tx.begin();
    }

    @Test
    public void test() {
        Department department = em.find(Department.class, departmentId);

        department.getEmployees().forEach(employee -> LOGGER.info(employee.getName()));
    }

    @After
    public void after() {
        tx.commit();
    }

    @AfterClass
    public static void afterClass() {
        em.close();
        emf.close();
    }
}
