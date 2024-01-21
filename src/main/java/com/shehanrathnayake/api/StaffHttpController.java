package com.shehanrathnayake.api;

import com.shehanrathnayake.service.custom.StaffService;
import com.shehanrathnayake.to.StaffTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
@CrossOrigin
public class StaffHttpController {

    private final StaffService service;

    public StaffHttpController(StaffService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public StaffTO crateNewStaff(@RequestBody @Validated StaffTO staffTO) {
        return service.saveStaff(staffTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{staff-id}", consumes = "application/json")
    public void updateStaff(@PathVariable("staff-id") String staffId,
                            @RequestBody @Validated StaffTO staffTO) {
        staffTO.setId(staffId);
        service.updateStaff(staffTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{staff-id}")
    public void deleteStaff(@PathVariable("staff-id") String staffId) {
        service.deleteStaff(staffId);
    }

    @GetMapping(value = "/{staff-id}", produces = "application/json")
    public StaffTO getStaffDetails(@PathVariable("staff-id") String staffId) {
        return service.getStaffDetails(staffId);
    }

    @GetMapping(produces = "application/json")
    public List<StaffTO> getAllStaff() {
        return service.getAllStaff();
    }
}
