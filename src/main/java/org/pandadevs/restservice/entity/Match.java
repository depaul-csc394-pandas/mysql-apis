package org.pandadevs.restservice.entity;

import javax.persistence.*;

@Entity
@Table(name="matches")
public class Match {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String matchID;

    private String team1;
    private String team2;
    private String team1Score;
    private String team2Score;

    public String getMatchID() { return matchID; }

    public void setMatchID(String matchID) { this.matchID = matchID; }

    public String getTeam1() { return team1; }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(String team1Score) {
        this.team1Score = team1Score;
    }

    public String getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(String team2Score) {
        this.team2Score = team2Score;
    }

    @Override
    public String toString() {
        return "Match{" + "Team 1='" + team1 + '\'' + ", Team 2='" + team2 + '\'' + ", Team 1 Score='" + team1Score + '\'' + ", Team 2 Score='" + team2Score + '\'' + '}';
    }
}