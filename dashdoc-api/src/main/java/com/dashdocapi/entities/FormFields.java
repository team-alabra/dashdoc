package com.dashdocapi.entities;


import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name="form_fields")
public class FormFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="doc_name")
    private String docName;
    @JdbcTypeCode(SqlTypes.JSON)
    private String data;

    public FormFields() {

    }
    public FormFields(Long id, String docName, String data) {
        this.id = id;
        this.docName = docName;
        this.data = data;
    }
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FormFields{" +
                "id=" + id +
                ", docName='" + docName + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
