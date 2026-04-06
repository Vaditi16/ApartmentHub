//package com.example.demo.repository;
//
//public class IssueRepository {
//
//}

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Issue;

public interface IssueRepository extends JpaRepository<Issue, Long> {

}