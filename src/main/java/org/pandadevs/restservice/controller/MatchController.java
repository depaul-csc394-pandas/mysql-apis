package org.pandadevs.restservice.controller;

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

    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    @GetMapping(path = "/get",
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
    @PostMapping(path = "/post",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity postMatch(
            @RequestParam("team1") String team1,
            @RequestParam("team2") String team2,
            @RequestParam("team1score") String team1score,
            @RequestParam("team2score") String team2score
    ) {
        Match match = setMatchData(team1, team2, team1score, team2score);
        return new ResponseEntity<>(repository.save(match), HttpStatus.OK);
    }

    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    @DeleteMapping(path = "/delete",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity deleteMatch(@RequestParam("matchID") String matchID) {
        repository.delete(matchID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    @PutMapping(path = "/update",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity putMatch(
            @RequestParam("matchID") String matchID,
            @RequestParam("team1") String team1,
            @RequestParam("team2") String team2,
            @RequestParam("team1score") String team1score,
            @RequestParam("team2score") String team2score
    ) {
        Match match = setMatchData(team1, team2, team1score, team2score);
        if (repository.exists(matchID)) {
            match.setMatchID(matchID);
            return new ResponseEntity<>(repository.save(match), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private Match setMatchData(String team1, String team2, String team1score, String team2score) {
        Match match = new Match();
        match.setTeam1(team1);
        match.setTeam2(team2);
        match.setTeam1Score(team1score);
        match.setTeam2Score(team2score);
        return match;
    }
}