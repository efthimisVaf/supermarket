package com.utrechtfour.supermarket.service;

import com.utrechtfour.supermarket.model.PurchaseOrder;
import com.utrechtfour.supermarket.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseOrderService {

    @Autowired
    PurchaseOrderRepository repository;

    public Optional<PurchaseOrder> getPurchaseOrderById(Long id){
        return repository.findById(id);
    }

    public PurchaseOrder createOrUpdatePurchaseOrder(PurchaseOrder purchaseOrder){
        return repository.save(purchaseOrder);
    }


}
