package com.example.suggestion.Model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "suggestions")
public class Suggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticket_id;
    @NotNull
    private String subjectTitle;

    @NotNull
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Department department;


}
