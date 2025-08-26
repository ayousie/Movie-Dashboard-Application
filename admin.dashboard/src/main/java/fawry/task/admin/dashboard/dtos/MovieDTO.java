package fawry.task.admin.dashboard.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import fawry.task.admin.dashboard.model.Movie;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDTO {
    private Long id;
    private String imdbId;
    private String title;
    private String year;
    private String poster;


    public static MovieDTO from(Movie m) {
        return MovieDTO.builder()
                .id(m.getId())
                .imdbId(m.getImdbId())
                .title(m.getTitle())
                .year(m.getReleaseYear())
                .poster(m.getPoster())
                .build();
    }
}
