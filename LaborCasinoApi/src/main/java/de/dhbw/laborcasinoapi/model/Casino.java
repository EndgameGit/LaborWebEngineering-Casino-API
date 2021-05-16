package de.dhbw.laborcasinoapi.model;

import javax.persistence.*;

@Entity
@Table(name = "casino")
public class Casino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "spielName")
    private String spielName;

    @Column(name = "spielerAnzahl")
    private int spielerAnzahl;

    @Column(name = "minAlter")
    private int minAlter = 18;

    public long getId() {
        return id;
    }

    //public void setId(long id) {
   //     this.id = id;
    //}

    public String getSpielName() {
        return spielName;
    }

    public void setSpielName(String spielName) {
        this.spielName = spielName;
    }

    public int getSpielerAnzahl() {
        return spielerAnzahl;
    }

    public void setSpielerAnzahl(int spielerAnzahl) {
        this.spielerAnzahl = spielerAnzahl;
    }

    public int getMinAlter() {
        return minAlter;
    }

    public void setMinAlter(int minAlter) {
        this.minAlter = minAlter;
    }

    public boolean isInBetrieb() {
        return inBetrieb;
    }

    public void setInBetrieb(boolean inBetrieb) {
        this.inBetrieb = inBetrieb;
    }

    public int getMaxEinsatz() {
        return maxEinsatz;
    }

    public void setMaxEinsatz(int maxEinsatz) {
        this.maxEinsatz = maxEinsatz;
    }

    @Column(name = "inBetrieb")
    private boolean inBetrieb;

    @Column(name = "maxEinsatz")
    private int maxEinsatz;

    public Casino(){}

    public Casino(String spielName, int spielerAnzahl, int minAlter, boolean inBetrieb, int maxEinsatz){
        this.spielName = spielName;
        this.spielerAnzahl = spielerAnzahl;
        this.minAlter = minAlter;
        this.inBetrieb = inBetrieb;
        this.maxEinsatz = maxEinsatz;
    }
}
