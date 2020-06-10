package ru.ifmo.firstspring.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
// Часть инфо в pom.xml
@Entity
public class University {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment") //стратегия, описанная в hibernate
    private int id;

    // Когда работаем со Spring, то необязательно регистрировать класс в persistence.xml
    // Достаточно отметить класс как @Entity

    @Column(unique = true, nullable = true) //уникальное непустое имя университета
    private String universityName;

    @OneToMany (mappedBy = "university", cascade = CascadeType.ALL, // Если происходит Удаление/добавения этих данных,
            // то происходит Удаление/добавление... всех связанных данных
    orphanRemoval = true) // orphanRemoval - удаление из списка из кода ведет к удалению из базы (таблицы)
    // Связь на уровне кода, между сущностями. university - имя поля в классе Student.
    // Один университет - много студентов.
    // ManyToOne - много студентов - один университет (отметка связи с другой стороны)
    private List<Student> students = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
