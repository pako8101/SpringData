package bg.softuni.gamestoremappingexercise.domain.repositories;

import bg.softuni.gamestoremappingexercise.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
