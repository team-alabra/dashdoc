package com.dashdocnow.entities;

import com.dashdocnow.DTO.DocumentDTO;
import com.dashdocnow.interfaces.enums.AgeGroup;
import com.dashdocnow.interfaces.enums.DocumentType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="document")
@Getter
@Setter
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="doc_name")
    private String documentName;
    @Enumerated(EnumType.STRING)
    @Column(name="doc_type")
    private DocumentType documentType;
    @Enumerated(EnumType.STRING)
    @Column(name="age_group")
    private AgeGroup ageGroup;
    @OneToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(name="provider_id")
    private Provider provider;
    @OneToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinColumn(name="client_id")
    private Client client;
    @Column(name = "created_on")
    private Calendar createdOn = Calendar.getInstance();
    @Column(name = "last_updated")
    private Calendar lastUpdated = Calendar.getInstance();
    @JdbcTypeCode(SqlTypes.JSON)
    private String formFields;

    public Document(Long id, DocumentType documentType, String docName, AgeGroup ageGroup, Provider provider, Client client, Calendar createdOn, Calendar lastUpdated, String formFields) {
        this.id = id;
        this.documentType = documentType;
        this.documentName = docName;
        this.ageGroup = ageGroup;
        this.provider = provider;
        this.client = client;
        this.createdOn = createdOn;
        this.lastUpdated = lastUpdated;
        this.formFields = formFields;
    }

    public Document() {

    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", documentName='" + documentName + '\'' +
                ", docType=" + documentType +
                ", ageGroup=" + ageGroup +
                ", provider=" + provider +
                ", student=" + client +
                ", createdOn=" + createdOn +
                ", lastUpdated=" + lastUpdated +
                ", formFields='" + formFields + '\'' +
                '}';
    }

    public DocumentDTO asDTO() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Long providerID = getProvider() == null ? null : getProvider().getId();
        Long clientID = getClient() == null ? null : getClient().getId();

        return new DocumentDTO(
                getId(),
                getDocumentType(),
                getDocumentName(),
                getAgeGroup(),
                providerID,
                clientID,
                mapper.readTree(getFormFields())
        );
    }
}
