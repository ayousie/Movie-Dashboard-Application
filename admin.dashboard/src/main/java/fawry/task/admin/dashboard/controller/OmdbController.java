package fawry.task.admin.dashboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fawry.task.admin.dashboard.service.OmdbService;

@RestController
@RequestMapping("/api/omdb")
@CrossOrigin
public class OmdbController {
    private final OmdbService omdbService;


    public OmdbController(OmdbService omdbService) {
        this.omdbService = omdbService;
    }


    // ADMIN: search external OMDb
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam("q") String query,
                                    @RequestParam(defaultValue = "1") int page) {
        return ResponseEntity.ok(omdbService.search(query, page));
    }
}
