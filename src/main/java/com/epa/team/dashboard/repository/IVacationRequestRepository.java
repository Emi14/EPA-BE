package com.epa.team.dashboard.repository;

import com.epa.team.dashboard.model.VacationRequest;
import com.epa.team.dashboard.resource.VacationRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Ionut Emanuel Mihailescu on 4/3/19.
 */
public interface IVacationRequestRepository extends JpaRepository<VacationRequest, Long> {
    List<VacationRequest> findAllByVacationRequestStatus(VacationRequestStatus vacationRequestStatus);
    List<VacationRequest> findAllByVacationRequestType(String vacationRequestType);
    List<VacationRequest> findAllByVacationRequestTypeAndVacationRequestStatus(String vacationRequestType ,
                                                                               VacationRequestStatus vacationRequestStatus);

    List<VacationRequest> findAllByUser_Id(Long userId);
}
