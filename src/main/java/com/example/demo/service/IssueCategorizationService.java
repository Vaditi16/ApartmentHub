//package com.example.demo.service;
//
//public class IssueCategorizationService {
//
//}


package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class IssueCategorizationService {

    public String categorizeIssue(String description) {

        description = description.toLowerCase();

        // Plumbing
        if(description.contains("water") || description.contains("leak") || description.contains("pipe") || description.contains("tap")) {
            return "Plumbing";
        }

        // Electrical
        if(description.contains("fan") || description.contains("light") || description.contains("switch") || description.contains("ac") || description.contains("power")) {
            return "Electrical";
        }

        // Network
        if(description.contains("wifi") || description.contains("internet") || description.contains("network")) {
            return "Network";
        }

        // Maintenance
        if(description.contains("garbage") || description.contains("trash") || description.contains("clean")) {
            return "Maintenance";
        }

        // Carpenter
        if(description.contains("door") || description.contains("window") || description.contains("cupboard") || description.contains("wood")) {
            return "Carpenter";
        }

        // Security
        if(description.contains("security") || description.contains("theft") || description.contains("suspicious") || description.contains("intruder")) {
            return "Security";
        }

        return "General";
    }
}