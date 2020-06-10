package ru.ifmo.firstspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ifmo.firstspring.repository.EventRepository;
import ru.ifmo.firstspring.specification.Specifications;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static ru.ifmo.firstspring.specification.Specifications.*;

// Сервер должен выдать HTML (вид странички в браузере), HTML отражаются в папке resources/templates
// В данном случае браузер - клиент.
// Spring должен добавить определенные данные в HTML. Controller - связующее звено между HTML & данными.
// Controller берет данные, передает их в том же виде в HTML и возвращает клиенту.
// В такой ситуации Controller может обращаться напрямую к репозиторию, берет методы и внедряет в HTML

// Второй вариант - данные обрабатываются перед передачей в HTML, либо данные приходят не из базы, а из другой
// программы/генерируются на сервере. Controller не занимается обработкой данных! Обработкой данных занимается Сервис.
// Тогда Сервис получает данные из базы, обрабатывает и передает Контроллеру. В таком случае Контроллер обращается
// к Сервису.
// Тем меньше строчек кода в методах Контроллера, тем лучше.

// В нашем примере клиент (браузер) и сервер общаются, передавая друг другу сообщения.
// Сообщение в общем виде:
// Строка запроса / статус "/" -- эта строка будет всегда
// HTTP заголовки
// тело сообщения -- здесь набор данных

/* Например, получена строчка от клиента(браузера) "/"
Сервер проходит по всем RequestMapping, если соответствие находит, то выполняется метод под этой аннотацией
 */

@Controller
public class HomeController {
    private EventRepository eventRepository;


    @Autowired
    public HomeController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }



    @RequestMapping("/")
    public String index(){
        System.out.println("---inTitle---");
        // SELECT * FROM event WHERE eventTitle LIKE %текст%
        eventRepository.findAll(inTitle("java")).forEach(System.out::println); //
        // SELECT * FROM event WHERE eventStart BETWEEN from AND to;
        eventRepository.findAll(eventsByDates(LocalDateTime.now().minus(2, ChronoUnit.WEEKS),
                LocalDateTime.now().plus(2, ChronoUnit.WEEKS))).forEach(System.out::println);


        return "index"; // Возвращает HTML файл "index"
        // Чтобы вызвать нестатический метод, нам всегда нужен объект класса. Spring смотрит на аннотацию Controller
        // и заранее создает объект класса через рефлексию. Когда приходит запрос, то Spring вызывает метод у
        // готового уже объекта. По умолчанию Spring создает один объект у такого класса. Если нужно несколько,
        // то надо задать параметр. В Spring такие объекты называют bean. Их складывают в контейнер (Map'у).
    }


}
