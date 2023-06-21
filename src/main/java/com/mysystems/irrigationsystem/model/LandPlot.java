package com.mysystems.irrigationsystem.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@Table(name = "LANDPLOT")
public class LandPlot {

    @Id
    @SequenceGenerator(name = "landplotseq", sequenceName = "landplotseq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "landplotseq")
    @Column(name = "LAND_PLOT_ID")
    Long id;

    @Column(name = "TIME_SLOT")
    int timeSlot;

    @Column(name = "WATER_AMOUNT", nullable = false)
    float waterAmount;



}
