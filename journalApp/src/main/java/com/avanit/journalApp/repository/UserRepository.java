package com.avanit.journalApp.repository;

import com.avanit.journalApp.entity.JournalEntry;
import com.avanit.journalApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

    void deleteByUsername(String username);
}
