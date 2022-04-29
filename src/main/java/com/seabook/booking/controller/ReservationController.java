package com.seabook.booking.controller;

import com.seabook.booking.model.Reservation;
import com.seabook.booking.repo.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class ReservationController {

    @Autowired
    ReservationsRepository repository;

    @GetMapping("/lista_prenotazioni")
    public List<Reservation> getAllSpots() {

        List<Reservation> prenotazioni = new ArrayList<>();
        repository.findAll().forEach(prenotazioni::add);

        return prenotazioni;
    }

    @GetMapping("/lista_prenotazioni/{id}")
    public Optional<Reservation> getReservation(@PathVariable long id){

        return repository.findById(id);
    }

    @PostMapping("/prenotazioni/create")
    public Reservation postReservation(@RequestBody Reservation reservation) {

       Reservation newrev =repository.save(new Reservation(
               reservation.getStabilimentoID(),
               reservation.getListaPostiPrenotati(),
               reservation.getTotalPrice()));

        return newrev;
    }

    @GetMapping("/stabilimento/{sid}/prenotazioni")
    public List<Reservation> getAllSpotsBySid(@PathVariable long sid) {

        List<Reservation> prenotazioni = new ArrayList<>();
        repository.findAllByStabilimentoID(sid).forEach(prenotazioni::add);

        return prenotazioni;
    }

    @DeleteMapping("/lista_prenotazioni/{id}/delete")
    public ResponseEntity<String> deleteReservation(@PathVariable("id") long id) {

        repository.deleteById(id);

        return new ResponseEntity<>("Reservation has been deleted!", HttpStatus.OK);
    }

    @DeleteMapping("/stabilimento/{sid}/delete_reservations")
    public ResponseEntity<String> deleteReservationBySid(@PathVariable long sid) {

        repository.deleteAllByStabilimentoID(sid);

        return new ResponseEntity<>("Reservations has been deleted!", HttpStatus.OK);
    }

    @PutMapping("/lista_prenotazioni/{id}/put")
    public ResponseEntity<Reservation> putReservation(@PathVariable("id") long id, @RequestBody Reservation reservation) {

        Optional<Reservation> stabData = repository.findById(id);

        if (stabData.isPresent()) {
            Reservation res = stabData.get();
            res.setStabilimentoID(reservation.getStabilimentoID());
            res.setTotalPrice(reservation.getTotalPrice());
            res.setListaPostiPrenotati(reservation.getListaPostiPrenotati());

            return new ResponseEntity<>(repository.save(res), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
