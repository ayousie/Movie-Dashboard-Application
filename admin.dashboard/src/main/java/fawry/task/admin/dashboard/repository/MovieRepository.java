package fawry.task.admin.dashboard.repository;

import fawry.task.admin.dashboard.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByImdbId(String imdbId);
    boolean existsByImdbId(String imdbId);
}
