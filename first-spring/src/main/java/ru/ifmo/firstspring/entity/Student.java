package ru.ifmo.firstspring.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false)
    private short studentAge;

    @ManyToMany (mappedBy = "students") //"students" - имя поля класса Event
    private List<Event> events = new ArrayList<>();

    @ManyToOne //много студентов на один университет - аннотация на уровне кода
    // на уровне базы данных создастся дополнительный столбец через первичные ключи:
    @JoinColumn
    private University university;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public short getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(short studentAge) {
        this.studentAge = studentAge;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
