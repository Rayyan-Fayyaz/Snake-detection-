
package com.finaltime.catalogue_management.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Builder;

@Builder // <== this is required for Snake.builder() to work
@Entity
public class Snake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;

    // Common name, e.g. "King Cobra"
    private String commonName;

    // Scientific (binomial) name, e.g. "Ophiophagus hannah"
    private String scientificName;

    // Taxonomic family, e.g. "Elapidae", "Viperidae", "Colubridae"
    private String family;

    // Where it's typically found, e.g. "South & Southeast Asia"
    private String region;

    private Boolean venomous;

    @Enumerated(EnumType.STRING)
    private VenomType venomType;

    @Enumerated(EnumType.STRING)
    private DangerLevel dangerLevel;

    private Boolean antivenomAvailable;

    @Column(length = 2000)
    private String description;

    public Snake() {
    }

    public Snake(Long id, String commonName, String scientificName, String family, String region,
                 Boolean venomous, VenomType venomType, DangerLevel dangerLevel,
                 Boolean antivenomAvailable, String description) {
        this.id = id;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.family = family;
        this.region = region;
        this.venomous = venomous;
        this.venomType = venomType;
        this.dangerLevel = dangerLevel;
        this.antivenomAvailable = antivenomAvailable;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Boolean getVenomous() {
        return venomous;
    }

    public void setVenomous(Boolean venomous) {
        this.venomous = venomous;
    }

    public VenomType getVenomType() {
        return venomType;
    }

    public void setVenomType(VenomType venomType) {
        this.venomType = venomType;
    }

    public DangerLevel getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(DangerLevel dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    public Boolean getAntivenomAvailable() {
        return antivenomAvailable;
    }

    public void setAntivenomAvailable(Boolean antivenomAvailable) {
        this.antivenomAvailable = antivenomAvailable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
