package com.shehanrathnayake.api;

import com.shehanrathnayake.service.custom.IssueReturnService;
import com.shehanrathnayake.to.IssueReturnTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/issueReturns")
@CrossOrigin
public class IssueReturnHttpController {

    private final IssueReturnService issueReturnService;

    public IssueReturnHttpController(IssueReturnService issueReturnService) {
        this.issueReturnService = issueReturnService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public IssueReturnTO createNewIssueReturn(@RequestBody @Validated IssueReturnTO issueReturnTO) {
        return issueReturnService.saveIssueReturn(issueReturnTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{issue-return-id}", consumes = "application/json")
    public void updateIssueReturn(@PathVariable("issue-return-id") String issueReturnId,
                                  @RequestBody @Validated IssueReturnTO issueReturnTO) {
        issueReturnTO.setId(issueReturnId);
        issueReturnService.updateIssueReturn(issueReturnTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{issue-return-id}")
    public void deleteIssueReturn(@PathVariable("issue-return-id") String issueReturnId) {
        issueReturnService.deleteIssueReturn(issueReturnId);
    }

    @GetMapping(produces = "application/json")
    public IssueReturnTO getIssueReturnDetails(@RequestParam("issue-id") String issueId) {
        return issueReturnService.getIssueReturnDetails(issueId);
    }
}
