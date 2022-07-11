package com.seabook.booking;

import com.seabook.booking.model.Reservation;
import com.seabook.booking.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000",
        "https://fe-artorias718.cloud.okteto.net",
        "https://apigateway-artorias718.cloud.okteto.net"})
@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;

    @GetMapping("/lista_prenotazioni")
    public ResponseEntity<List<Reservation>> getAllReservations() {

        service.getAllReservations();

        return new ResponseEntity<>(service.getAllReservations(), HttpStatus.OK);
    }

    @GetMapping("/lista_prenotazioni/{id}")
    public ResponseEntity<Optional<Reservation>> getReservation(@PathVariable long id){

        return new ResponseEntity<>(service.getReservation(id), HttpStatus.OK);

    }

    @GetMapping("/lista_prenotazioni/email/{userEmail}")
    public ResponseEntity<List<Reservation>> getReservationListByEmail1(@PathVariable("userEmail") String userEmail) {

        return new ResponseEntity<>(service.getReservationListByEmail1(userEmail), HttpStatus.OK);
    }

    @PostMapping("/prenotazioni/create")
    public ResponseEntity<Reservation> postReservation(@RequestBody Reservation reservation) {

        return new ResponseEntity<>(service.createReservation(reservation), HttpStatus.OK);
    }

    @GetMapping("/stabilimento/{sid}/prenotazioni")
    public ResponseEntity<List<Reservation>> getAllSpotsBySid(@PathVariable long sid) {

        return new ResponseEntity<>(service.getAllSpotsBySid(sid), HttpStatus.OK);
    }

    @DeleteMapping("/lista_prenotazioni/{id}/delete")
    public ResponseEntity<String> deleteReservation(@PathVariable("id") long id) {

        return new ResponseEntity<>(service.deleteReservation(id), HttpStatus.OK);
    }

    @DeleteMapping("/stabilimento/{sid}/delete_reservations")
    public ResponseEntity<String> deleteReservationBySid(@PathVariable long sid) {

        return new ResponseEntity<>(service.deleteReservationBySid(sid), HttpStatus.OK);

    }

    @PutMapping("/lista_prenotazioni/{id}/put")
    public ResponseEntity<Reservation> putReservation(@PathVariable("id") long id, @RequestBody Reservation reservation) {

        Optional<Reservation> resData = service.getReservation(id);

        if (resData.isPresent()) {
            Reservation res = resData.get();
            res.setStabilimentoID(reservation.getStabilimentoID());
            res.setTotalPrice(reservation.getTotalPrice());
            res.setListaPostiPrenotati(reservation.getListaPostiPrenotati());

            return new ResponseEntity<>(service.saveReservation(res), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
