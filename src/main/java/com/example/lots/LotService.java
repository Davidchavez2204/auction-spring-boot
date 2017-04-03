package com.example.lots;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yurii on 21.03.2017.
 */
@Service
public class LotService {
    @Autowired
    private LotRepository lotRepository;

    public List<Lot> getAllUserLots(String username){

        List<Lot> lots = new ArrayList<>();
        lotRepository.findBySellerUsername(username)
                .forEach(lots::add);
        return lots;

    }


    public List<Lot> getAllLots(){

        List<Lot> lots = new ArrayList<>();
        lotRepository.findAll()
                .forEach(lots::add);
        return lots;

    }


    public List<Lot> getAllSortedLots(){

        List<Lot> lots = new ArrayList<>();
        lotRepository.findAll()
                .forEach(lots::add);
        lots.sort((Lot l1, Lot l2)
                ->
                Double.compare(l1.getPrice(),l2.getPrice())
        );
        return lots;

    }

    public List<Lot> getAllLotsByTopic(String topicName){
        List<Lot> lots = new ArrayList<>();
        lotRepository.findByTopic(topicName)
                .forEach(lots::add);
        return lots;
    }





    public Lot getLot(String id){

        return lotRepository.findOne(id);
    }

    public void addLot(Lot lot) {

        lotRepository.save(lot);
    }

    public void updateLot(Lot lot) {

        lot.setBuyer(lotRepository.findById(lot.getId()).getBuyer());
        lotRepository.save(lot);
    }


    public void deleteLot(Long id) {
        lotRepository.deleteById(id);
    }




}
