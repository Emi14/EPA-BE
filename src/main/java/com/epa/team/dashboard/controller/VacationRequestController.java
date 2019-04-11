package com.epa.team.dashboard.controller;

import com.epa.team.dashboard.resource.VacationRequestResource;
import com.epa.team.dashboard.resource.VacationRequestStatus;
import com.epa.team.dashboard.service.VacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Ionut Emanuel Mihailescu on 4/3/19.
 */

@Controller
@RequestMapping("/api/vacationRequest")
public class VacationRequestController {

    @Autowired
    private VacationRequestService vacationRequestService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<VacationRequestResource>> getAllVacationRequests(
            @RequestParam(name = "type", required = false) String vacationRequestType,
            @RequestParam(name = "status", required = false) VacationRequestStatus vacationRequestStatus) {
        return new ResponseEntity<>(vacationRequestService.getAllVacationRequests(vacationRequestType, vacationRequestStatus),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/getAll/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<VacationRequestResource>> getAllVacationRequestsByUserId(
            @PathVariable(name = "userId") Long userId,
            @RequestParam(name = "onlyApproved", required = false, defaultValue = "false") boolean onlyApproved){

        return new ResponseEntity<>(vacationRequestService.getAllVacationRequestsByUserId(userId, onlyApproved), HttpStatus.OK);
    }

    @RequestMapping(value = "/addVacationRequest", method = RequestMethod.POST)
    public ResponseEntity<VacationRequestResource> saveVacationRequest(@RequestBody VacationRequestResource vacationRequestResource){
        return new ResponseEntity<>(vacationRequestService.saveVacationRequestResource(vacationRequestResource), HttpStatus.OK);
    }

    @RequestMapping(value = "/updateVacationRequestStatus/{vacationRequestId}", method = RequestMethod.PUT)
    public ResponseEntity<VacationRequestResource> updateVacationRequestStatus(
            @RequestBody VacationRequestStatus vacationRequestStatus,
            @PathVariable(name = "vacationRequestId") Long vacationRequestId){
        return vacationRequestService.updateVacationRequestStatus(vacationRequestId, vacationRequestStatus);
    }
}
