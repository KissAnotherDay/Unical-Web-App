package com.dipartimento.demowebapplications.persistence.dao.impljdbc;

import com.dipartimento.demowebapplications.model.Piatto;
import com.dipartimento.demowebapplications.model.Ristorante;
import com.dipartimento.demowebapplications.persistence.dao.PiattoDao;
import com.dipartimento.demowebapplications.persistence.dao.PiattoDaoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PiattoDaoProxy implements PiattoDao {

    private PiattoDaoImpl piattoDao;
    private Map<String, Piatto> cache;

    public PiattoDaoProxy() {
        this.piattoDao = new PiattoDaoImpl();
        this.cache = new HashMap<>();
    }

    @Override
    public List<Piatto> findAll() {
        return piattoDao.findAll();
    }

    @Override
    public Piatto findByPrimaryKey(String nome) {
        // Controlla la cache prima di fare la query
        if (cache.containsKey(nome)) {
            return cache.get(nome);
        } else {
            Piatto piatto = piattoDao.findByPrimaryKey(nome);
            if (piatto != null) {
                cache.put(nome, piatto);
            }
            return piatto;
        }
    }

    @Override
    public void save(Piatto piatto) {
        piattoDao.save(piatto);
        // Aggiunge alla cache dopo il salvataggio
        cache.put(piatto.getNome(), piatto);
    }

    @Override
    public void delete(Piatto piatto) {
        piattoDao.delete(piatto);
        // Rimuove dalla cache dopo la cancellazione
        cache.remove(piatto.getNome());
    }

    @Override
    public List<Piatto> findAllByRistoranteName(String name) {
        return piattoDao.findAllByRistoranteName(name);
    }

    @Override
    public Piatto findByID(int id) {
        return null;
    }

    @Override
    public void create(Piatto piatto) {

    }

    @Override
    public void update(Piatto piatto) {

    }

    @Override
    public void deleteByID(int id) {

    }

    @Override
    public void addRistoranteToPiatto(int piattoId, int ristoranteId) {

    }

    @Override
    public void removeRistoranteFromPiatto(int piattoId, int ristoranteId) {

    }

    @Override
    public List<Ristorante> findRistorantiByPiatto(int piattoId) {
        return List.of();
    }
}