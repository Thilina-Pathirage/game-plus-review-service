package com.example.games.controllers;

import com.example.games.models.games;
import com.example.games.repositories.gameRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class gameController {

    @Autowired
    private gameRepository repo;

    @PostMapping(value = "/addGame", consumes = {"multipart/form-data"})
    public ResponseEntity<String> addGame(@RequestParam("game") String gameStr, @RequestParam("image") MultipartFile image) {

        // Convert JSON string to games object
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            games game = objectMapper.readValue(gameStr, games.class);

            // Convert image to base64
            String base64Image = Base64.getEncoder().encodeToString(image.getBytes());
            game.setImageUrl(base64Image);

            // Save game to DB
            repo.save(game);

            return new ResponseEntity<String>("Game is inserted", HttpStatus.OK);

        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Error processing game data: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            return new ResponseEntity<String>("Error processing image: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("getAllGames")
    public List<games> getAllGames () {
        return repo.findAll();
    }

    @GetMapping("/getGameById/{id}")
    public ResponseEntity<games> getGameById(@PathVariable("id") String gameId) {
        Optional<games> gameOptional = repo.findById(gameId);

        if (gameOptional.isPresent()) {
            games game = gameOptional.get();
            return new ResponseEntity<>(game, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("updateGame/{id}")
    public ResponseEntity<String> updateNews(@PathVariable String id, @RequestBody games game) {
        Optional<games> existingNewsOpt = repo.findById(id);

        if(existingNewsOpt.isPresent()){
            games existingGames = existingNewsOpt.get();
            existingGames.setGameName(game.getGameName());
            existingGames.setDescription(game.getDescription());
            repo.save(existingGames);
            return new ResponseEntity<String>("Game updated!", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Game not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("deleteGame/{id}")
    public ResponseEntity<String> deleteNews(@PathVariable String id) {
        Optional<games> existingNews = repo.findById(id);

        if(existingNews.isPresent()){
            repo.deleteById(id);
            return new ResponseEntity<String>("Game is deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Game not found", HttpStatus.NOT_FOUND);
        }
    }
}
