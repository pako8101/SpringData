import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeesFromDepartment {
    private static final String FORMAT = "%s %s from %s - $%.2f%n";

    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();


        final String department = "Research and Development ";
        entityManager.createQuery("select  e from  Employee  e " +
                        "where e.department.name=:dp " +
                        "order by e.salary asc, e.id", Employee.class)
                .setParameter("dp", department)
                .getResultList()
                .forEach(e -> System.out.printf(FORMAT, e.getFirstName(),
                        e.getLastName(), e.getDepartment().getName(), e.getSalary()));

        entityManager.close();
    }
}
