package com.epa.team.dashboard.service;

import com.epa.team.dashboard.model.VacationRequest;
import com.epa.team.dashboard.repository.IVacationRequestRepository;
import com.epa.team.dashboard.resource.VacationRequestResource;
import com.epa.team.dashboard.resource.VacationRequestStatus;
import com.epa.team.dashboard.resource.VacationRequestType;
import com.epa.team.dashboard.utils.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Ionut Emanuel Mihailescu on 4/3/19.
 */

@Service
public class VacationRequestService {

    @Autowired
    private IVacationRequestRepository vacationRequestRepository;

    @Autowired
    private Mapper mapper;

    public List<VacationRequestResource> getAllVacationRequests(VacationRequestType vacationRequestType,
                                                                VacationRequestStatus vacationRequestStatus) {

        if (Objects.isNull(vacationRequestStatus) && Objects.isNull(vacationRequestType)) {
            return mapper.mapAsList(vacationRequestRepository.findAll(), VacationRequestResource.class);
        } else {
            return getFilteredVacationRequestResources(vacationRequestType, vacationRequestStatus);
        }
    }

    private List<VacationRequestResource> getFilteredVacationRequestResources(VacationRequestType vacationRequestType, VacationRequestStatus vacationRequestStatus) {
        if (Objects.nonNull(vacationRequestStatus) && Objects.nonNull(vacationRequestType)) {
            return mapper.mapAsList(vacationRequestRepository
                            .findAllByVacationRequestTypeAndVacationRequestStatus(vacationRequestType, vacationRequestStatus),
                    VacationRequestResource.class);
        }
        if (Objects.nonNull(vacationRequestStatus)) {
            return mapper.mapAsList(vacationRequestRepository.findAllByVacationRequestStatus(vacationRequestStatus),
                    VacationRequestResource.class);
        }
        return mapper.mapAsList(vacationRequestRepository.findAllByVacationRequestType(vacationRequestType),
                VacationRequestResource.class);
    }

    public List<VacationRequestResource> getAllVacationRequestsByUserId(Long userId, boolean onlyApproved) {
        List<VacationRequestResource> vacationRequestResourcesByUserId =
                mapper.mapAsList(vacationRequestRepository.findAllByUser_Id(userId), VacationRequestResource.class);

        if(onlyApproved){
            vacationRequestResourcesByUserId = vacationRequestResourcesByUserId.stream()
                    .filter(vacationRequestResource -> vacationRequestResource.getVacationRequestStatus()
                            .equals(VacationRequestStatus.APPROVED))
                    .collect(Collectors.toList());
        }

        return vacationRequestResourcesByUserId;
    }

    public VacationRequestResource saveVacationRequestResource(VacationRequestResource vacationRequestResource) {
        VacationRequest vacationRequestToBeSaved = mapper.map(vacationRequestResource, VacationRequest.class);

        return mapper.map(vacationRequestRepository.save(vacationRequestToBeSaved), VacationRequestResource.class);
    }

    public ResponseEntity<VacationRequestResource> updateVacationRequestStatus(Long vacationRequestId, VacationRequestStatus vacationRequestStatus) {
        Optional<VacationRequest> vacationRequestOptional = vacationRequestRepository.findById(vacationRequestId);

        if(vacationRequestOptional.isPresent()){
            VacationRequest vacationRequest = vacationRequestOptional.get();

            if(vacationRequest.getVacationRequestStatus() == VacationRequestStatus.PENDING &&
                    vacationRequestStatus != VacationRequestStatus.PENDING){
                vacationRequest.setVacationRequestStatus(vacationRequestStatus);
                return new ResponseEntity<>(mapper.map(vacationRequestRepository.save(vacationRequest), VacationRequestResource.class),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
