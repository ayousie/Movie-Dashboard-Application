package fawry.task.admin.dashboard.service;

import fawry.task.admin.dashboard.dtos.MovieDTO;
import fawry.task.admin.dashboard.exception.ApiException;
import fawry.task.admin.dashboard.exception.NotFoundException;
import fawry.task.admin.dashboard.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import fawry.task.admin.dashboard.repository.MovieRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final OmdbService omdbService;


    public MovieService(MovieRepository movieRepository, OmdbService omdbService) {
        this.movieRepository = movieRepository;
        this.omdbService = omdbService;
    }


    public Page<MovieDTO> list(int page, int size) {
        Page<Movie> p = movieRepository.findAll(PageRequest.of(page, size));
        return p.map(MovieDTO::from);
    }


    public MovieDTO get(Long id) {
        Movie m = movieRepository.findById(id).orElseThrow(() -> new NotFoundException("Movie not found"));
        return MovieDTO.from(m);
    }


    public List<MovieDTO> addByImdbIds(List<String> imdbIds) {
        List<Movie> saved = imdbIds.stream().map(this::addByImdbId).collect(Collectors.toList());
        return saved.stream().map(MovieDTO::from).collect(Collectors.toList());
    }


    private Movie addByImdbId(String imdbId) {
        if (movieRepository.existsByImdbId(imdbId)) {
            throw new ApiException("Movie already exists: " + imdbId);
        }
        Map data = omdbService.findByImdbId(imdbId);
        if (data == null || !"True".equalsIgnoreCase(String.valueOf(data.get("Response")))) {
            throw new ApiException("OMDb not found imdbId=" + imdbId);
        }
        Movie m = Movie.builder()
                .imdbId(String.valueOf(data.get("imdbID")))
                .title(String.valueOf(data.get("Title")))
                .releaseYear(String.valueOf(data.get("release_year")))
                .poster(String.valueOf(data.get("Poster")))
                .build();
        return movieRepository.save(m);
    }


    public void deleteById(Long id) {
        if (!movieRepository.existsById(id)) throw new NotFoundException("Movie not found");
        movieRepository.deleteById(id);
    }


    public void deleteByIds(List<Long> ids) {
        movieRepository.deleteAllById(ids);
    }
}
