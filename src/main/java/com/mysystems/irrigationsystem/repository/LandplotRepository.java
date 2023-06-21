package com.mysystems.irrigationsystem.repository;

import com.mysystems.irrigationsystem.model.LandPlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface LandplotRepository extends JpaRepository<LandPlot,Long> {

    List<LandPlot> findAll ();

    @Modifying
    @Transactional
    @Query("update LandPlot l set l.waterAmount =:water, l.timeSlot =:time where l.id =:id")
    int editLandPlot(float water, int time ,Long id);


    @Modifying
    @Transactional
    @Query("update LandPlot l set  l.timeSlot =:time where l.id =:id")
    int configureTime(int time ,Long id);

}
