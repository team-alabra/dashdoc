package com.dashdocapi.DTO;

import com.fasterxml.jackson.databind.JsonNode;

public class FormFieldsDTO {

    private Long id;
    private String docName;
    private JsonNode data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public FormFieldsDTO(Long id, String docName, JsonNode data) {
        this.id = id;
        this.docName = docName;
        this.data = data;
    }
}
