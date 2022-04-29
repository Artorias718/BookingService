package com.seabook.booking.repo;

import com.seabook.booking.model.Reservation;
import org.springframework.data.repository.CrudRepository;


public interface ReservationsRepository extends CrudRepository<Reservation,Long>{


}
