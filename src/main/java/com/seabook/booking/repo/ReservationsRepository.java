package com.seabook.booking.repo;

import com.seabook.booking.model.Reservation;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;


public interface ReservationsRepository extends CrudRepository<Reservation,Long>{

    List<Reservation> findAllByStabilimentoID(long id);
    @Transactional
    void deleteAllByStabilimentoID(long sid);

}
