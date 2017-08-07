package ru.stga.pft.rest;

/**
 * Created by admin on 07.08.2017.
 */
public class Issue {

    private int id;
    private String subject;
    private String description;
    private String state_name;


    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Issue withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Issue issue = (Issue) o;

        if (id != issue.id) return false;
        if (subject != null ? !subject.equals(issue.subject) : issue.subject != null) return false;
        return description != null ? description.equals(issue.description) : issue.description == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public Issue whitId(int id) {
        this.id = id;
        return this;
    }

    public String getStatus() {
        return state_name;
    }

    public Issue whitStatus(String state_name) {
        this.state_name = state_name;
        return this;
    }



}
