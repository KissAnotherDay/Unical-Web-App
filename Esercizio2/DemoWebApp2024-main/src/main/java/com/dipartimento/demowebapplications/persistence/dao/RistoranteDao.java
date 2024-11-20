package com.dipartimento.demowebapplications.persistence.dao;

import com.dipartimento.demowebapplications.model.Piatto;
import com.dipartimento.demowebapplications.model.Ristorante;

import java.util.List;

public interface RistoranteDao {

    List<Ristorante> findAll();

    Ristorante findByPrimaryKey(String nome);

    void save(Ristorante ristorante);

    void delete(Ristorante ristorante);

    // Nuovi metodi per la relazione molti-a-molti
    void addPiattoToRistorante(int ristoranteId, int piattoId); // Aggiunge un piatto a un ristorante
    void removePiattoFromRistorante(int ristoranteId, int piattoId); // Rimuove un piatto da un ristorante
    List<Piatto> findPiattiByRistorante(int ristoranteId); // Recupera tutti i piatti associati a un ristorante
}

