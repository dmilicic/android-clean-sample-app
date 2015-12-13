package com.kodelabs.mycosts.domain.repository;

import java.util.Date;

/**
 * Created by dmilicic on 12/13/15.
 */
public interface CostRepository {

    void getCostsByDate(Date date);

    void getCostsInRange(Date startDate, Date endDate);
}
