package ru.kpfu.printer.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.kpfu.printer.models.enums.UserRole;

import javax.persistence.*;

/**
 * 01.06.2018
 *
 * @author Robert Bagramov.
 */
@Entity
@Table(name = "source_printing_system_user")
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private TeamDetails teamDetails;
}
