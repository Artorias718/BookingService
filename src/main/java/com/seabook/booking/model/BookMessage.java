package com.seabook.booking.model;

import java.util.Date;
import java.util.List;

public class BookMessage {
    private Date dataPrenotazione;
    private List<Integer> listaPosti;

    public Date getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(Date dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    public List<Integer> getListaPosti() {
        return listaPosti;
    }

    public void setListaPosti(List<Integer> listaPosti) {
        this.listaPosti = listaPosti;
    }
}
