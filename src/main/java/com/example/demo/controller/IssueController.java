//package com.example.demo.controller;
//
//public class IssueController {
//
//}

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Issue;
import com.example.demo.service.IssueService;

//@RestController
//@RequestMapping("/issues")
//@CrossOrigin("*")
//public class IssueController {
//
//    @Autowired
//    private IssueService issueService;
//
//    // Add Issue
//    @PostMapping
//    public Issue addIssue(@RequestBody Issue issue) {
//        return issueService.saveIssue(issue);
//    }
//
//    // Get All Issues
//    @GetMapping
//    public List<Issue> getIssues() {
//        return issueService.getAllIssues();
//    }
//
//    // Update Issue Status
//    @PutMapping("/{id}/status")
//    public Issue updateStatus(@PathVariable Long id, @RequestParam String status) {
//        return issueService.updateStatus(id, status);
//    }
//}

@RestController
@RequestMapping("/issues")
@CrossOrigin("*")
public class IssueController {

    @Autowired
    private IssueService issueService;

    // Add Issue
//    @PostMapping
//    public Issue addIssue(@RequestBody Issue issue) {
//        return issueService.saveIssue(issue);
//    }

    @PostMapping("/{residentId}")
    public Issue addIssue(@PathVariable Long residentId,
                          @RequestBody Issue issue) {

        return issueService.saveIssue(issue, residentId);
    }
    
    // Get All Issues
    @GetMapping
    public List<Issue> getIssues() {
        return issueService.getAllIssues();
    }

    // Update Issue Status
    @PutMapping("/{id}/status")
    public Issue updateStatus(@PathVariable Long id, @RequestParam String status) {
        return issueService.updateStatus(id, status);
    }
}