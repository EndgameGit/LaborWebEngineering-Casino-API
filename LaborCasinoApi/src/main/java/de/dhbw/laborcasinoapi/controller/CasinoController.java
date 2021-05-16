package de.dhbw.laborcasinoapi.controller;


import de.dhbw.laborcasinoapi.model.Casino;
import de.dhbw.laborcasinoapi.repository.CasinoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class CasinoController {


    private CasinoRepository casinoRepository;

    public CasinoController(CasinoRepository casinoRepository) {

        this.casinoRepository = casinoRepository;
    }

    @GetMapping("/items")
    public ResponseEntity<List<Casino>> getAllItems(@RequestParam(required = false) String spielName) {

        try{

            List<Casino> items = new ArrayList<Casino>();

            if (spielName == null) {
                items.addAll(casinoRepository.findAll());
            }else {
                items.addAll(casinoRepository.findBySpielNameContaining(spielName));
            }

            if(items.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(items, HttpStatus.OK);

        } catch (Exception exception) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Casino> getItemById(@PathVariable("id") long id) {

        Optional<Casino> itemData= casinoRepository.findById(id);

        if(itemData.isPresent()) {
            return new ResponseEntity<>(itemData.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/items")
    public ResponseEntity<Casino> createItem(@RequestBody Casino item) {

        try {

            Casino _item = casinoRepository
                    .save(new Casino(item.getSpielName(), item.getSpielerAnzahl(), item.getMinAlter(), item.isInBetrieb(), item.getMaxEinsatz()));

            return new ResponseEntity<>(_item, HttpStatus.CREATED);

        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<Casino> updateItem(@PathVariable("id") long id, @RequestBody Casino item) {

        Optional<Casino> itemData= casinoRepository.findById(id);
        if(itemData.isPresent()) {
            Casino _item = itemData.get();
            _item.setSpielName(item.getSpielName());
            _item.setInBetrieb(item.isInBetrieb());
            _item.setMinAlter(item.getMinAlter());
            _item.setMaxEinsatz(item.getMaxEinsatz());
            _item.setSpielerAnzahl(item.getSpielerAnzahl());

            return new ResponseEntity<>(casinoRepository.save(_item), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") long id) {

        try {

            casinoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/items")
    public ResponseEntity<HttpStatus> deleteAllItems() {

        try {

            casinoRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/items/inBetrieb")
    public ResponseEntity<List<Casino>> findByInBetrieb() {
        try{

            List<Casino> items = casinoRepository.findByInBetrieb(true);
            if (items.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }else {
                return new ResponseEntity<>(items, HttpStatus.OK);
            }

        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
