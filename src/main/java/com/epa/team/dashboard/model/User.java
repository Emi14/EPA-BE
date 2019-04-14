package com.epa.team.dashboard.model;

import com.epa.team.dashboard.resource.UserRole;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Ionut Emanuel Mihailescu on 4/3/19.
 */

@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    protected UserRole role;

    @Column(name = "days_off")
    private long daysOff;

    @Column(name = "work_from_home")
    private boolean workFromHome;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<VacationRequest> vacationRequests;


}
