package org.pandadevs.restservice.controller;

import javassist.tools.web.BadHttpRequest;
import org.pandadevs.restservice.entity.Match;
import org.pandadevs.restservice.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/matches")
public class MatchController {

    @Autowired
    private MatchRepository repository;

    @GetMapping
    public Iterable<Match> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = "/{matchID}")
    public Match find(@PathVariable("matchID") String matchID) {
        return repository.findOne(matchID);
    }

    @PostMapping(consumes = "application/json")
    public Match create(@RequestBody Match match) {
        return repository.save(match);
    }

    @DeleteMapping(path = "/{matchID}")
    public void delete(@PathVariable("matchID") String matchID) {
        repository.delete(matchID);
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

}