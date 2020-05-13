package com.codegym.service;

import com.codegym.model.Smartphone;
import com.codegym.repository.SmartphoneRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SmartphoneServiceImpl implements SmartphoneService{
    @Autowired
    private SmartphoneRepository repository;
    @Override
    public Iterable<Smartphone> findAll() {
        return repository.findAll();
    }

    @Override
    public Smartphone findById(Integer id) {
        Smartphone smartphone=repository.findOne(id);
        if (smartphone==null){
            return null;
        }
        return smartphone;
    }

    @Override
    public Smartphone save(Smartphone phone) {
        return repository.save(phone);
    }

    @Override
    public Smartphone remove(Integer id) {
        Smartphone smartphone=findById(id);
        repository.delete(id);
        return smartphone;

    }
}
