package ru.ifmo.firstspring.controllers;

    import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ifmo.firstspring.entity.Event;
import ru.ifmo.firstspring.repository.EventRepository;

@Controller
@RequestMapping("/event")
public class EventController {
    private EventRepository repository;

    @Autowired
    public void setRepository(EventRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showForm(Model model){
        model.addAttribute("event", new Event());
        return "add_event";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addEvent(@ModelAttribute Event event){
        repository.save(event);
        return "redirect:/event/add";
    }
}