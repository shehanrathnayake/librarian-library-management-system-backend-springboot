package com.shehanrathnayake.api;

import com.shehanrathnayake.service.custom.IssueService;
import com.shehanrathnayake.to.IssueTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/issues")
@CrossOrigin
public class IssueHttpController {
    private final IssueService issueService;

    public IssueHttpController(IssueService issueService) {
        this.issueService = issueService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public IssueTO createNewIssue(@RequestBody @Validated IssueTO issueTO) {
        return issueService.saveIssue(issueTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{issue-id}", consumes = "application/json")
    public void updateIssue(@PathVariable("issue-id") String issueId,
                            @RequestBody @Validated IssueTO issueTO) {
        issueTO.setId(issueId);
        issueService.updateIssue(issueTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{issue-id}")
    public void deleteIssue(@PathVariable("issue-id") String issueId) {
        issueService.deleteIssue(issueId);
    }

//    @GetMapping(value = "/{issue-id}", produces = "application/json")
//    public IssueTO getIssueDetails(@PathVariable("issue-id") String issueId) {
//        return issueService.getIssueDetails(issueId);
//    }

    @GetMapping(produces = "application/json")
    public List<IssueTO> getAllIssues(@RequestParam("user-id") String userId) {
        return issueService.getAllIssues(userId);
    }
}
