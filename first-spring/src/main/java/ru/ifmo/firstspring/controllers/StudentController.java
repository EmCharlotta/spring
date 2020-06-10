package ru.ifmo.firstspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ifmo.firstspring.entity.Event;
import ru.ifmo.firstspring.entity.Student;
import ru.ifmo.firstspring.entity.University;
import ru.ifmo.firstspring.repository.EventRepository;
import ru.ifmo.firstspring.repository.StudentRepository;
import ru.ifmo.firstspring.repository.UniversityRepository;

@Controller
@RequestMapping("/student")
public class StudentController {
    UniversityRepository universityRepository;
    EventRepository eventRepository;
    StudentRepository studentRepository;

    @Autowired
    public StudentController(UniversityRepository universityRepository, EventRepository eventRepository, StudentRepository studentRepository) {
        this.universityRepository = universityRepository;
        this.eventRepository = eventRepository;
        this.studentRepository = studentRepository;
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showForm(Model model){
        model.addAttribute("student", new Student());
        model.addAttribute("events", eventRepository.findAll());
        model.addAttribute("universities", universityRepository.findAll());
        return "add_student";

    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute Student student, @RequestParam(name = "universityId") int universityId,
                             @RequestParam(name = "eventId") int eventId) //name - что пользователь ввел, int - куда
    // его ввод сохранится
    {
        University university = universityRepository.findById(universityId).get();
        Event event = eventRepository.findById(eventId).get();

        student.getEvents().add(event);
        student.setUniversity(university);
        //важно! Добавить связанную информацию с другой стороны. Тогда при сохранении студента они обновятся.
        // Пробрасывание двусторонних ссылок
        university.getStudents().add(student);
        event.getStudents().add(student);
        studentRepository.save(student);
        return "redirect:/student/add";
    }
}
