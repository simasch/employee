package hr;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TestDataCreator {

    public static Integer createTestData(EntityManager em) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Country switzerland = new Country();
        switzerland.setName("Switzerland");
        em.persist(switzerland);

        City bern = new City();
        bern.setZip("3000");
        bern.setName("Bern");
        bern.setCountry(switzerland);
        em.persist(bern);

        City zurich = new City();
        zurich.setZip("8000");
        zurich.setName("Zurich");
        zurich.setCountry(switzerland);
        em.persist(zurich);

        Department department = new Department();
        department.setName("Sales");
        em.persist(department);

        Employee john = new Employee();
        john.setName("John");
        em.persist(john);

        Address marktgasse = new Address();
        marktgasse.setStreet("Martkgasse");
        marktgasse.setNumber("123");

        marktgasse.setCity(bern);

        john.setAddress(marktgasse);

        department.addEmployee(john);

        Employee mary = new Employee();
        mary.setName("Mary");
        em.persist(mary);

        department.addEmployee(mary);

        Address bahnhofstrasse = new Address();
        bahnhofstrasse.setStreet("Bahhofstrasse");
        bahnhofstrasse.setNumber("4");

        bahnhofstrasse.setCity(zurich);

        mary.setAddress(bahnhofstrasse);

        em.persist(department);

        em.flush();
        transaction.commit();

        return department.getId();
    }
}
