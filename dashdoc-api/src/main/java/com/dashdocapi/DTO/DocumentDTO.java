package com.dashdocapi.DTO;
import com.dashdocapi.interfaces.enums.AgeGroup;
import com.dashdocapi.interfaces.enums.DocumentType;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentDTO {
    private Long id;
    private DocumentType documentType;
    private String documentName;
    private AgeGroup ageGroup;
    private Long providerID;
    private Long clientID;
    private JsonNode formFields;

    public DocumentDTO() {
    }

    public DocumentDTO(Long id, DocumentType documentType, String docName, AgeGroup ageGroup, Long providerID, Long clientID, JsonNode formFields) {
        this.id = id;
        this.documentType = documentType;
        this.documentName = docName;
        this.ageGroup = ageGroup;
        this.providerID = providerID;
        this.clientID = clientID;
        this.formFields = formFields;
    }

    @Override
    public String toString() {
        return "DocumentDTO{" +
                "id=" + id +
                ", docType=" + documentType +
                ", docName='" + documentName + '\'' +
                ", ageGroup=" + ageGroup +
                ", providerID=" + providerID +
                ", studentID=" + clientID +
                ", formFields=" + formFields +
                '}';
    }
}
