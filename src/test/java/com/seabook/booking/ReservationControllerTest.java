/*package com.seabook.booking;

import com.seabook.booking.model.Reservation;
import com.seabook.booking.repo.ReservationsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import static org.hamcrest.Matchers.*;



import javax.swing.text.html.HTMLDocument;
import java.util.*;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReservationController.class)
class ReservationControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ReservationsRepository repository;

    Reservation res1 = new Reservation(1, Arrays.asList(1,2,3), 55);
    Reservation res2 = new Reservation(1, Arrays.asList(4), 25);
    Reservation res3 = new Reservation(1, Arrays.asList(5,6), 31);


    @Test
    void getAllSpots() throws Exception {
        List<Reservation> prenotazioni = new ArrayList<>(Arrays.asList(res1, res2, res3));

        Mockito.when(repository.findAll()).thenReturn(prenotazioni);

        mvc.perform(MockMvcRequestBuilders
                .get("/patient")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect()

        );


    }

    @Test
    void getReservation() {
    }

    @Test
    void postReservation() {
    }

    @Test
    void getAllSpotsBySid() {
    }

    @Test
    void deleteReservation() {
    }

    @Test
    void deleteReservationBySid() {
    }

    @Test
    void putReservation() {
    }
}*/