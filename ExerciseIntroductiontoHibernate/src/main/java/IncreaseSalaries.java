import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IncreaseSalaries {
    public static void main(String[] args) {

        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

//        final List<String> test = Arrays.asList(
//                "Engineering","Tool Design","Marketing ","Information Services ");

     // final String testString = String.join(", ", test);
       entityManager.createQuery(
                "update Employee as e set e.salary = e.salary * 1.12" +
                        " where e.department.id " +
                        "in(1,2,14,3)")
               .executeUpdate();


        entityManager.getTransaction().commit();

       entityManager.createQuery("select e from Employee as e where e.department.name " +
               "in('Engineering','Tool Design','Marketing'," +
               "'Information Services')", Employee.class)
               .getResultList()
               .forEach(e-> System.out.printf("%s %s ($%.2f)",
                       e.getFirstName(),e.getLastName(),e.getSalary()));



entityManager.close();
        System.out.println();



    }
}
