package pl.miro.dziennikapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Announcment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String subject;
    private String contents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public String getSubject() {
        return subject;
    }

    public String getContents() {
        return contents;
    }


    public void setType(String type) {
        this.type = type;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Announcment() {
    }

    public Announcment(String type, String subject, String contents) {
        this.type = type;
        this.subject = subject;
        this.contents = contents;
    }
}
