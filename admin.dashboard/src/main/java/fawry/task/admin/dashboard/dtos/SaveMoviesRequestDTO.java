package fawry.task.admin.dashboard.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class SaveMoviesRequestDTO {

    @NotEmpty
    private List<String> imdbIds; // e.g. ["tt0133093", "tt0234215"]
}
