import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

public class ChangeCasing {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String UPDATE_TOWNS =
            "UPDATE  Town as t set t.name = upper (t.name) where length(t.name) > 5 ";

    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(DATABASE_NAME);

        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

      final List<Town> resultList = entityManager.createQuery("select t from Town t", Town.class).getResultList();

        for (Town town : resultList) {
            final String townName = town.getName();
            if (townName.length()<=5){
                town.setName(townName.toUpperCase());
                entityManager.persist(town);
            }
        }

//
//    final   List<Town>townStream = resultList.stream()
//               .filter(town -> town.getName().toUpperCase().length()<=5)
//              .collect(Collectors.toList());
//      entityManager.persist(townStream);
//
//       entityManager.persist(resultList);
        entityManager.getTransaction().commit();
        entityManager.close();


    }
}
