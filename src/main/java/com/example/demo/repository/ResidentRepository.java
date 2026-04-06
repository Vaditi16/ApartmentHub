//package com.example.demo.repository;
//
//public class ResidentRepository {
//
//}

package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Flat;
import com.example.demo.model.Resident;

public interface ResidentRepository extends JpaRepository<Resident, Long> {
	List<Resident> findByFlat(Flat flat);

}