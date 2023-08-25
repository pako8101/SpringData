import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class GetEmployeeWithProject {
    public static void main(String[] args) {

        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        final int empId = scanner.nextInt();
       String employee = entityManager.createQuery(
                "select  e from Employee  e where e.id = :id", Employee.class)
                .setParameter("id", empId)
                .getSingleResult()
                .toString();
        System.out.println(employee);



    }
}
