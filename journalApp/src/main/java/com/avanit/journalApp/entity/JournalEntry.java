package com.avanit.journalApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "journal_entries")

@Data
@NoArgsConstructor
public class JournalEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NonNull
    private String title;

    private String content;

    private LocalDateTime date;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Foreign key
    @JsonBackReference
    private User user;


}
