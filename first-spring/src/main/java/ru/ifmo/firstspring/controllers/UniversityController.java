package ru.ifmo.firstspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ifmo.firstspring.entity.University;
import ru.ifmo.firstspring.repository.UniversityRepository;

@Controller
@RequestMapping("/university") //Можно присвоить на весь класс RequestMapping, все строчки запросов будут начинаться
// на university:
// university/add
// university/remove
// university/etc...
public class UniversityController {

    @Autowired //Autowired можно использовать для Setter (самый правильный вариант), для конструктора, для свойства
    // объекта(напрямую к private свойству, неправильно с точки зрения инкапсуляции).
    // благодаря аннотации в переменную repository будет положен конкретный объект при инициализации,
    // Spring сам создаст:
    private UniversityRepository repository; //Объект типа Репозиторий важен - он отправляет данные в базу данных

    public UniversityController(UniversityRepository repository) {
        this.repository = repository;
    }

    // Если пользователь вводит какие-то данные и передает их на сервер после нажатия кнопки "Добавить", то на сервер
    // отправляется запрос методом GET (переход по ссылке, в адресной строке передаются данные)
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showForm(Model model){
        model.addAttribute("university", new University());
        // передаем в HTML Объект (new University), в HTML к этому объекту мы сможем обращаться по имени "university"
        return "add_university";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    // Данных не будут передаваться в адресной строке, безопаснее. Также RequestMethod.POST может передать больше строк

    // Когда Spring будет вызывать метод addUniversity, то он передаст в метод в качестве аргумента тот объект
    // типа University, который соберет
    public String addUniversity(@ModelAttribute University university){
        repository.save(university); //добавление объекта в базу
        return "redirect:/university/add"; //обратно отображаем форму
            }
    @RequestMapping(value = "/show")
    public String showData(Model model){
        model.addAttribute("universities", repository.findAll());
        return "universities";
    }
}
