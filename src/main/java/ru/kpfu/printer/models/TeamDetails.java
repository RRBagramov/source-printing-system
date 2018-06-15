package ru.kpfu.printer.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * 01.06.2018
 *
 * @author Robert Bagramov.
 */
@Getter
@Setter
@Entity
@Table(name = "team_details")
@NoArgsConstructor
public class TeamDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "team_name")
    private String teamName;

    @Column
    private String auditory;

    @Column(name = "more_info")
    private String moreInfo;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
