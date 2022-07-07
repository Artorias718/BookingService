package com.seabook.booking.services;

import com.seabook.booking.model.BookMessage;
import com.seabook.booking.model.Reservation;
import com.seabook.booking.repo.ReservationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    static final String topicExchangeName = "spring-boot-exchange";
    private final RabbitTemplate rabbitTemplate;

    private final ReservationsRepository repository;

    public List<Reservation> getAllReservations() {

        List<Reservation> prenotazioni = new ArrayList<>();
        repository.findAll().forEach(prenotazioni::add);

        return prenotazioni;
    }

    public Optional<Reservation> getReservation(long id) {

        return repository.findById(id);
    }

    public List<Reservation> getReservationListByEmail1(String userEmail) {

        List<Reservation> reservations = new ArrayList<>();
        repository.findByUserEmail(userEmail).forEach(reservations::add);
        return reservations;
    }

    public List<Reservation> getAllSpotsBySid(long sid) {

        List<Reservation> prenotazioni = new ArrayList<>();
        repository.findAllByStabilimentoID(sid).forEach(prenotazioni::add);

        return prenotazioni;
    }

    public Reservation saveReservation(Reservation res) {

        return repository.save(res);
    }

    public Reservation createReservation(Reservation reservation) {

        Reservation newrev = repository.save(new Reservation(
                reservation.getStabilimentoID(),
                reservation.getTotalPrice(),
                reservation.getListaPostiPrenotati(),
                reservation.getDate(),
                reservation.getUserEmail()
        ));

        BookMessage bookMessage = new BookMessage();
        bookMessage.setDataPrenotazione(reservation.getDate());
        bookMessage.setListaPosti(reservation.getListaPostiPrenotati());

        String strReservation = bookMessage.toString();

        rabbitTemplate.convertAndSend(topicExchangeName, "foo.bar.baz", strReservation);

        return newrev;
    }

    public String deleteReservation(long id) {

        Optional<Reservation> reservation = repository.findById(id);
        BookMessage bookMessage = new BookMessage();
        if (reservation.isPresent()) {
            Reservation res = reservation.get();
            bookMessage.setDataPrenotazione(res.getDate());
            bookMessage.setListaPosti(res.getListaPostiPrenotati());
            String strReservation = bookMessage.toString();
            rabbitTemplate.convertAndSend(topicExchangeName, "foo2.bar.baz", strReservation);
        }
        repository.deleteById(id);
        return "Reservation has been deleted!";
    }

    public String deleteReservationBySid(long sid) {

        List<Reservation> prenotazioni = new ArrayList<>();
        repository.findAllByStabilimentoID(sid).forEach(prenotazioni::add);
        for(Reservation res: prenotazioni){
            res.getListaPostiPrenotati();
            List<Integer> arry = new ArrayList<Integer>();
            arry.addAll(res.getListaPostiPrenotati());
            rabbitTemplate.convertAndSend(topicExchangeName, "foo2.bar.baz", arry);
        }

        repository.deleteAllByStabilimentoID(sid);

        return "Reservations has been deleted!";

    }



}
