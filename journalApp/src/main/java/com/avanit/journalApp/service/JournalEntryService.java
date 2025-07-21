package com.avanit.journalApp.service;

import com.avanit.journalApp.entity.JournalEntry;
import com.avanit.journalApp.entity.User;
import com.avanit.journalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;
//    public void saveEntry(JournalEntry journalEntry, String userName){
//
//        User user=userService.findByUserName(userName);
//        JournalEntry saved = journalEntryRepository.save(journalEntry);
//        user.getJournalEntries().add(saved);
//        userService.saveEntry(user);
//    }
@Transactional
public void saveEntry(JournalEntry journalEntry, String userName) {
    User user = userService.findByUserName(userName);
    if (user == null) {
        throw new RuntimeException("User not found: " + userName);
    }

    journalEntry.setUser(user);
    JournalEntry saved = journalEntryRepository.save(journalEntry);

    // if I maintain bidirectional mapping
    user.getJournalEntries().add(saved); //sets the parent → child link.
    userService.saveUser(user); // only needed if I am persisting user changes

    String subject = "Journal Entry Created";
    String message = "Hi " + user.getUsername() + ",\n\n" +
            "Your journal entry titled \"" + saved.getTitle() + "\" was created on " +
            saved.getDate().toLocalDate() + ".\n\n" +
            "Keep writing and reflecting!\n\n" +
            "Journal App Team";

    mailService.sendMail(user.getEmail(), subject, message);
}


    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }


    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(Long id){
        return journalEntryRepository.findById(id);
    }


    public boolean deleteById(Long id,String username){
    boolean removed=false;
    User user=userService.findByUserName(username);
         removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        if(removed){
            userService.saveUser(user);
            journalEntryRepository.deleteById(id);
        }
        return removed;

    }



}
