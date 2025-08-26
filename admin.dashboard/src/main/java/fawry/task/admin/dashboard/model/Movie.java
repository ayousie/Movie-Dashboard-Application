package fawry.task.admin.dashboard.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "movies")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "imdb_id", nullable = false)
    private String imdbId;

    private String poster;

    private String title;

    @Column(name = "release_year")  // 👈 avoids using reserved "year"
    private String releaseYear;
}