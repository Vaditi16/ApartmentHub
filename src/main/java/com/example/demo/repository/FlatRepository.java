//package com.example.demo.repository;
//
//public class FlatRepository {
//
//}


package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Flat;

public interface FlatRepository extends JpaRepository<Flat, Long> {
	 Optional<Flat> findByFlatNumber(String flatNumber);

}