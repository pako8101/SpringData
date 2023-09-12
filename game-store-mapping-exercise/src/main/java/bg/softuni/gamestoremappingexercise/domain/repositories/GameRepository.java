package bg.softuni.gamestoremappingexercise.domain.repositories;

import bg.softuni.gamestoremappingexercise.domain.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}

