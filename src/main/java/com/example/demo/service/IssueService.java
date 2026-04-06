package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Issue;
import com.example.demo.model.Resident;
import com.example.demo.repository.IssueRepository;
import com.example.demo.repository.ResidentRepository;

//
//@Service
//public class IssueService {
//
//    @Autowired
//    private IssueRepository issueRepository;
//
//    @Autowired
//    private ResidentRepository residentRepository;
//
//    public Issue saveIssue(Issue issue) {
//
//        // Get resident from DB
//        Long residentId = issue.getResident().getId();
//        Resident resident = residentRepository.findById(residentId).orElse(null);
//
//        issue.setResident(resident);
//
//        // NLP category detection
//        String desc = issue.getDescription().toLowerCase();
//
//        if(desc.contains("door") || desc.contains("hinge")) {
//            issue.setCategory("Carpenter");
//        }
//        else if(desc.contains("fan") || desc.contains("light")) {
//            issue.setCategory("Electrician");
//        }
//        else if(desc.contains("water") || desc.contains("leak")) {
//            issue.setCategory("Plumber");
//        }
//        else if(desc.contains("security") || desc.contains("stranger")) {
//            issue.setCategory("Security Guard");
//        }
//        else {
//            issue.setCategory("General");
//        }
//
//        // Default status
//        issue.setStatus("Pending");
//
//        return issueRepository.save(issue);
//    }
//
//
//    public Issue updateStatus(Long id, String status) {
//
//        Issue issue = issueRepository.findById(id).orElse(null);
//
//        if(issue != null) {
//            issue.setStatus(status);
//            return issueRepository.save(issue);
//        }
//
//        return null;
//    }
//    public List<Issue> getAllIssues() {
//        return issueRepository.findAll();
//    }
//}

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ResidentRepository residentRepository;

    // ✅ FIXED METHOD
    public Issue saveIssue(Issue issue, Long residentId) {

        // Get resident safely from DB
        Resident resident = residentRepository.findById(residentId).orElse(null);

        if (resident == null) {
            throw new RuntimeException("Resident not found with id: " + residentId);
        }

        // Set resident
        issue.setResident(resident);

        // NLP category detection
        String desc = issue.getDescription().toLowerCase();

        if (desc.contains("door") || desc.contains("hinge")) {
            issue.setCategory("Carpenter");
        }
        else if (desc.contains("fan") || desc.contains("light")) {
            issue.setCategory("Electrician");
        }
        else if (desc.contains("water") || desc.contains("leak")) {
            issue.setCategory("Plumber");
        }
        else if (desc.contains("security") || desc.contains("stranger")) {
            issue.setCategory("Security Guard");
        }
        else {
            issue.setCategory("General");
        }

        // Default status
        issue.setStatus("Pending");

        return issueRepository.save(issue);
    }

    public Issue updateStatus(Long id, String status) {

        Issue issue = issueRepository.findById(id).orElse(null);

        if(issue != null) {
            issue.setStatus(status);
            return issueRepository.save(issue);
        }

        return null;
    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }
}