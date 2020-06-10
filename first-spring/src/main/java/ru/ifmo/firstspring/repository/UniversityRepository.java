package ru.ifmo.firstspring.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.ifmo.firstspring.entity.Event;
import ru.ifmo.firstspring.entity.University;

import java.util.Optional;

public interface UniversityRepository extends CrudRepository<University, Integer>,
    JpaSpecificationExecutor<University>{
        // три варианта методов в рамках данного репозитория будут происходить с сущностью University
        // (то есть благодаря дженерику прописывается автоматически SELECT * FROM table University)
        // Задача - описать блок WHERE:
        // 1) получение одной записи (спецификация)
        // 2) получение нескольких записей (спецификация)
        // 3) получение количества (спецификация)
        // Все три варианта принимают на вход какую-то спецификацию. В данном случае спецификация - это объект

    // описание дополнительных, нестандартных методов
    // Вариант 1:

    @Query ("SELECT u FROM University u WHERE u.universityName = :universityName")
    // На JPQL. если поставить nativeQuery = true, тогда будет чистый SQL

    Optional<University> findByName (@Param("universityName") String universityName);
    //аргумент (строка 17 "universityName незеленое") и имя параметра
    // (строка 16 "universityName") никак не связаны, поэтому добавляем @Param - имя параметра "universityName"

    // Вариант 2:
    // Через спецификации, Criteria IP, следующее занятие
}
