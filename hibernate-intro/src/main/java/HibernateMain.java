import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateMain {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Student example = new Student();
        example.setName("Bobo");
        session.save(example);

        List<Student>students= session.createQuery("FROM Student as s where s.name = 'Tosho'", Student.class).list();
        students.forEach(student -> System.out.println(student.getId()+ student.getName()));

        session.get(Student.class,1);
        session.getTransaction().commit();
        session.close();
    }
}
