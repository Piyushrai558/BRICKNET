package com.example.SuperAdmin.Repository;


import com.example.SuperAdmin.Entity.PersonalDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalDetailsRepository extends JpaRepository<PersonalDetails,Long> {

}
