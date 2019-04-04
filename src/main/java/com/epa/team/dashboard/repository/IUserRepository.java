package com.epa.team.dashboard.repository;

import com.epa.team.dashboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ionut Emanuel Mihailescu on 4/3/19.
 */

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

}
