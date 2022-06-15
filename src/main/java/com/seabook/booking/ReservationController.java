package com.seabook.booking;

import com.seabook.booking.model.BookMessage;
import com.seabook.booking.model.Reservation;
import com.seabook.booking.repo.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.amqp.rabbit.core.RabbitTemplate;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2")
public class ReservationController {

    private final RabbitTemplate rabbitTemplate;


    public ReservationController(RabbitTemplate rabbitTemplate){this.rabbitTemplate = rabbitTemplate;}

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

        System.out.println("\n\n\n\n\n\n\n" + reservation.getDate());
        Reservation newrev = repository.save(new Reservation(
               reservation.getStabilimentoID(),
               reservation.getTotalPrice(),
               reservation.getListaPostiPrenotati(),
               reservation.getDate()));

        BookMessage bookMessage = new BookMessage();
        bookMessage.setDataPrenotazione(reservation.getDate());
        bookMessage.setListaPosti(reservation.getListaPostiPrenotati());

//        JSONObject obj = new JSONObject(bookMessage);
//        String a = obj.getJSONObject("result").getString("name");
//        System.out.println("\n\n\n\n\n\n\n\n\n\n\t\t" + obj);

        String strReservation = bookMessage.toString();
        System.out.println(strReservation + "\n\n\n\n\n\n\n\n\n\n");

        // TODO(2) cambiare il tipo di oggetto da inviare nella queue
//        rabbitTemplate.convertAndSend(bookingService.topicExchangeName, "foo.bar.baz", reservation.getListaPostiPrenotati());
//        rabbitTemplate.convertAndSend(bookingService.topicExchangeName, "foo.bar.baz", bookMessage.toString());
        rabbitTemplate.convertAndSend(bookingService.topicExchangeName, "foo.bar.baz", strReservation);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\t\t Inviato book msg");

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

        Optional<Reservation> reservation = repository.findById(id);

        if (reservation.isPresent()) {
            Reservation res = reservation.get();
            List<Integer> arry = new ArrayList<Integer>();
            arry.addAll(res.getListaPostiPrenotati());
            rabbitTemplate.convertAndSend(bookingService.topicExchangeName, "foo2.bar.baz", arry);
        }

        repository.deleteById(id);

        return new ResponseEntity<>("Reservation has been deleted!", HttpStatus.OK);

    }

    @DeleteMapping("/stabilimento/{sid}/delete_reservations")
    public ResponseEntity<String> deleteReservationBySid(@PathVariable long sid) {

        List<Reservation> prenotazioni = new ArrayList<>();
        repository.findAllByStabilimentoID(sid).forEach(prenotazioni::add);
        for(Reservation res: prenotazioni){
            res.getListaPostiPrenotati();
            List<Integer> arry = new ArrayList<Integer>();
            arry.addAll(res.getListaPostiPrenotati());
            rabbitTemplate.convertAndSend(bookingService.topicExchangeName, "foo2.bar.baz", arry);
        }

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
