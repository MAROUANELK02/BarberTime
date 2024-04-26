package com.barbertime.barbertime_backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    private Long idUser;

    @NotEmpty
    @Size(min = 4, max = 40)
    private String firstName;

    @NotEmpty
    @Size(min = 4, max = 40)
    private String lastName;

    @NotEmpty
    @Size(min = 10)
    private String telNumber;

    @Email
    @NotEmpty
    @Column(unique = true)
    private String email;

    @NotEmpty
    @Column(unique = true)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToOne
    private Role role;
}