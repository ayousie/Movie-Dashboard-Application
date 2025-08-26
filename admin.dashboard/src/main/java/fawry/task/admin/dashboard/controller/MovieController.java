package fawry.task.admin.dashboard.controller;

import fawry.task.admin.dashboard.dtos.MovieDTO;
import fawry.task.admin.dashboard.dtos.PageResponseDTO;
import fawry.task.admin.dashboard.dtos.SaveMoviesRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import fawry.task.admin.dashboard.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin
public class MovieController {
    private final MovieService movieService;


    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    // USER + ADMIN: list paginated
    @GetMapping
    public ResponseEntity<PageResponseDTO<MovieDTO>> list(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        Page<MovieDTO> p = movieService.list(page, size);
        return ResponseEntity.ok(new PageResponseDTO<>(p.getContent(), p.getTotalElements(), p.getTotalPages(), page, size));
    }


    // USER + ADMIN: details
    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.get(id));
    }


    // ADMIN: add movies by imdb IDs (batch)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<MovieDTO>> add(@Validated @RequestBody SaveMoviesRequestDTO req) {
        return ResponseEntity.ok(movieService.addByImdbIds(req.getImdbIds()));
    }


    // ADMIN: delete single
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    // ADMIN: delete batch, e.g. /api/movies?ids=1,2,3
    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBatch(@RequestParam List<Long> ids) {
        movieService.deleteByIds(ids);
        return ResponseEntity.noContent().build();
    }
}
