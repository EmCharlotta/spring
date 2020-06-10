package ru.ifmo.firstspring.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import ru.ifmo.firstspring.entity.Event;

// CrudRepository - из Spring.
// Когда приложение будет запускаться, Spring создаст класс на основе репозитория, напишет реализацию методов + можно
// добавить ещё свои методы
// Стандартные методы: добавление / обновление / удаление / извлечение всех записей / извлечение по первичн ключу

public interface EventRepository extends CrudRepository<Event, Integer>,
    //Создается для сущности Event, первичный ключ сущности - Integer

// если мы хотим создать дополнительный запрос (не через аннотации):
    JpaSpecificationExecutor<Event>{

    }

