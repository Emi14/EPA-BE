package com.epa.team.dashboard.resource;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by Ionut Emanuel Mihailescu on 4/3/19.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class VacationRequestResource {

    private Long id;
    private Date startDate;
    private Date endDate;
    private VacationRequestStatus vacationRequestStatus;
    private VacationRequestType vacationRequestType;

}
