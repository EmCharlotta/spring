package ru.ifmo.firstspring.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.ifmo.firstspring.entity.Event;
import ru.ifmo.firstspring.entity.University;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;

public class Specifications {

    // Принято создавать отдельный класс, внутри описывать статические методы, которые возвращают объекты типа
    // Specification на каждую сущность

    //SELECT * FROM event WHERE eventStart BETWEEN from AND to;
    public static Specification<Event> eventsByDates(final LocalDateTime from, final LocalDateTime to) {
        return (Specification<Event>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(
                root.get("eventStart"), from, to);
    }

    public static Specification<Event> inTitle(final String inTitle) {
        return (Specification<Event>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("eventTitle"),
                "%" + inTitle + "%");
        //анонимный класс
        //Блок Where - это предикат
        // Spring подставит в WHERE Specification, который нам вернется из метода

        // SELECT * FROM event WHERE eventTitle LIKE %текст%
        // Ищем такие случаи, где в названии события есть какой-то текст
        //Root - Откуда выбираем
    }

    public static Specification<University> studentCount(String uName){
        return new Specification<University>() {
            @Override
            public Predicate toPredicate(Root<University> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        }
    }
}