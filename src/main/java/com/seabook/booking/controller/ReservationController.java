package com.seabook.booking.controller;

import com.seabook.booking.model.Reservation;
import com.seabook.booking.repo.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    /*TODO
    post prenotazione
    get lista prenotazioni by sid
    put prenotazione
    delete prentazione
    delete all prenotazioni
    delete all by sid
     */


}
