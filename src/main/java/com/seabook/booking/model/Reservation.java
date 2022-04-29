package com.seabook.booking.model;
import javax.persistence.*;

@Entity
@Table(name = "prenotazione")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "stabilimetoId")
    private long stabilimentoID;

    @Column(name = "totalPrice")
    private double totalPrice;

    @Column(name = "listaPostiPrenotati")
    private int listaPostiPrenotati;





    public Reservation(){

    }

}
