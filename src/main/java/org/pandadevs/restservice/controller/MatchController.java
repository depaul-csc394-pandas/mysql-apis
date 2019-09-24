package org.pandadevs.restservice.controller;

import javassist.tools.web.BadHttpRequest;
import org.pandadevs.restservice.entity.Match;
import org.pandadevs.restservice.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/api/matches")
public class MatchController {

    @Autowired
    private MatchRepository repository;

    @GetMapping
    public Iterable<Match> findAll() {
        return repository.findAll();
    }

    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    @GetMapping(path = "/matchID",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity getMatch(@RequestParam("matchID") String matchID) {
        Match match = repository.findOne(matchID);
        return new ResponseEntity<>(match, HttpStatus.OK);
    }

    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    @GetMapping(path = "/",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity getMatches() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    @PostMapping(path = "/new",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity postMatch(
            @RequestParam("team1") String team1,
            @RequestParam("team2") String team2,
            @RequestParam("team1score") String team1score,
            @RequestParam("team2score") String team2score
    ) {
        Match match = new Match();
        match.setTeam1(team1);
        match.setTeam2(team2);
        match.setTeam1Score(team1score);
        match.setTeam2Score(team2score);
        return new ResponseEntity<>(repository.save(match), HttpStatus.OK);
    }

    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    @DeleteMapping(path = "/matchID",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity deleteMatch(@RequestParam("matchID") String matchID) {
        repository.delete(matchID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    @PutMapping(path = "/{matchID}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity putMatch(
            @PathVariable("matchID") String matchID,
            @RequestParam("team1") String team1,
            @RequestParam("team2") String team2,
            @RequestParam("team1score") String team1score,
            @RequestParam("team2score") String team2score
    ) {
        Match match = new Match();
        match.setTeam1(team1);
        match.setTeam2(team2);
        match.setTeam1Score(team1score);
        match.setTeam2Score(team2score);

        if (repository.exists(matchID)) {
            match.setMatchID(matchID);
            return new ResponseEntity<>(repository.save(match), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*

    @DeleteMapping(path = "/{matchID}")
    public void delete(@PathVariable("matchID") String matchID) {
        repository.delete(matchID);
    }

    @GetMapping(value = "/{matchID}", consumes = {"application/x-www-form-urlencoded"})
     public Match find(@PathVariable("matchID") String matchID) {
         return repository.findOne(matchID);
     }

    @PostMapping(consumes = "application/json")
    public Match create(@RequestBody Match match) {
        return repository.save(match);
    }

    @PutMapping(path = "/{matchID}")
    public Match update(@PathVariable("matchID") String matchID, @RequestBody Match match) throws BadHttpRequest {
        if (repository.exists(matchID)) {
            match.setMatchID(matchID);
            return repository.save(match);
        } else {
            throw new BadHttpRequest();
        }
    }
*/
}