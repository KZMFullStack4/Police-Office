package com.moshka.model;

import com.moshka.enums.DossierStatus;
import lombok.*;
import javax.persistence.*;
import javax.persistence.Table;
import java.util.Date;

//Parvandeh
@Entity
@Data
@Table(name="tbl_dossier",schema = "bicycle")
@NoArgsConstructor
public class Dossier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dossier_number",nullable = false,unique = true)
    private String dossierNumber;

    @Column(name = "description",nullable = true)
    private String description;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "creation_date",nullable = false)
    private Date creationDate;

    @Temporal(value = TemporalType.TIME)
    @Column(name = "creation_time",nullable = true)
    private Date creationTime;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "dossier_Status")
    private DossierStatus dossierStatus ;

    @OneToOne(mappedBy = "dossierId")
    private PoliceManModel policeManId;

    @JoinColumn(name = "plaintiff_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private PlaintiffModel plaintiff;
}
