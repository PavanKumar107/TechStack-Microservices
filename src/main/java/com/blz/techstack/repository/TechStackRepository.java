package com.blz.techstack.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blz.techstack.model.TechStackModel;



public interface TechStackRepository extends JpaRepository<TechStackModel, Long> {

}
