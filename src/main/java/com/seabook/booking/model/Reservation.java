package com.seabook.booking.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "prenotazione")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "stabilimetoId")
    private long stabilimentoID;

    @Column(name = "totalPrice")
    private double totalPrice;

    @ElementCollection
    @Column(name = "listaPostiPrenotati")
    private List<Integer> listaPostiPrenotati;

    @Column(name = "date")
    private Date date;

    @Column(name = "userEmail")
    private String userEmail;

    public Reservation(long stabilimentoID, double totalPrice, List<Integer> listaPostiPrenotati, Date date, String userEmail) {
        this.stabilimentoID = stabilimentoID;
        this.totalPrice = totalPrice;
        this.listaPostiPrenotati = listaPostiPrenotati;
        this.date = date;
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public long getStabilimentoID() {
        return stabilimentoID;
    }

    public void setStabilimentoID(long stabilimentoID) {
        this.stabilimentoID = stabilimentoID;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Integer> getListaPostiPrenotati() {
        return listaPostiPrenotati;
    }

    public void setListaPostiPrenotati(List<Integer> listaPostiPrenotati) {
        this.listaPostiPrenotati = listaPostiPrenotati;
    }

    public Reservation(){

    }

    public Reservation(long stabilimentoID, List<Integer> listaPostiPrenotati, double totalPrice) {
        this.stabilimentoID = stabilimentoID;
        this.listaPostiPrenotati = listaPostiPrenotati;
        this.totalPrice = totalPrice;

    }

    public Reservation(long stabilimentoID, double totalPrice, List<Integer> listaPostiPrenotati, Date date) {
        this.stabilimentoID = stabilimentoID;
        this.totalPrice = totalPrice;
        this.listaPostiPrenotati = listaPostiPrenotati;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", stabilimentoID=" + stabilimentoID +
                ", totalPrice=" + totalPrice +
                ", listaPostiPrenotati=" + listaPostiPrenotati +
                ", date=" + date +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
