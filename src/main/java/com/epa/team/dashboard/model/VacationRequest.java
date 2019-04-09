package com.epa.team.dashboard.model;

import com.epa.team.dashboard.resource.VacationRequestStatus;
import com.epa.team.dashboard.resource.VacationRequestType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Ionut Emanuel Mihailescu on 4/3/19.
 */

@Entity
@Table(name = "requests")
@Getter
@Setter
@EqualsAndHashCode
public class VacationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private VacationRequestStatus vacationRequestStatus;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private VacationRequestType vacationRequestType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
