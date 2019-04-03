package com.epa.team.dashboard.resource;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ionut Emanuel Mihailescu on 4/3/19.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserResource {

    private Long id;
    private String username;
    private String password;
    private UserRole role;
    private long daysOff;
    private boolean workFromHome;
    private List<VacationRequestResource> vacationRequests = new ArrayList<>();

}
