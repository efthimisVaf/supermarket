package com.utrechtfour.supermarket.repository;

import com.utrechtfour.supermarket.model.PurchaseOrder;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder,Long> {
}
