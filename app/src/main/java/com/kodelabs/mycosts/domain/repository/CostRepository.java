package com.kodelabs.mycosts.domain.repository;

import com.kodelabs.mycosts.domain.model.Cost;

import java.util.List;

/**
 * Created by dmilicic on 12/13/15.
 */
public interface CostRepository {

    void insert(Cost cost);

    void update(Cost cost);

    Cost getCostById(long id);

    List<Cost> getAllCosts();

    void delete(Cost cost);
}
