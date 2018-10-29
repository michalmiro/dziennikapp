package pl.miro.dziennikapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.miro.dziennikapp.model.Announcment;
import pl.miro.dziennikapp.repository.AnnouncmentRepository;
import pl.miro.dziennikapp.service.AnnouncmentService;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    private AnnouncmentRepository announcmentRepository;
    private AnnouncmentService announcmentService;

    @Autowired
    public HomeController(AnnouncmentRepository announcmentRepository, AnnouncmentService announcmentService) {
        this.announcmentRepository = announcmentRepository;
        this.announcmentService = announcmentService;
    }

    @ResponseBody
    @GetMapping("/update/{id}/{contents}")
    public String update(@PathVariable Long id, @PathVariable String contents) {
        announcmentService.update(id, contents);
        return "no niezly update";
    }


    @GetMapping("/delete/{id}")
    @ResponseBody
    public String deleteAnnouncment(@PathVariable Long id) {
        announcmentRepository.deleteById(id);
        return "Usunalem ci matke";
    }

    @GetMapping("/announcment/{contents}")
    public String getAnnouncment(Model model, @PathVariable String contents) {
        Optional<Announcment> announcmentByContents = announcmentRepository.findByContentsIsContaining(contents);
        announcmentByContents.ifPresent(announcment -> model.addAttribute("announcment", announcment));
        return announcmentByContents.map(announcment -> "singleAnnouncment").orElse("noAnnouncment");
    }
//
//    @GetMapping("/announcment/{id}")
//    public String getAnnouncment(Model model, @PathVariable Long id){
//        Optional<Announcment> announcmentById = announcmentRepository.findById(id);
//        announcmentById.ifPresent(announcment -> model.addAttribute("announcment", announcment));
//        return announcmentById.map(announcment -> "singleAnnouncment.html").orElse("noAnnouncment.html");
//    }


    @GetMapping("/list")
    public String home(Model model) {
        List<Announcment> allAnnouncments = (List<Announcment>) announcmentRepository.findAll();
        model.addAttribute("announcments", allAnnouncments);
        return "list";
    }

    @GetMapping("/createAnnouncment")
    public String create(Model model) {
        model.addAttribute("newAnnouncment", new Announcment());
        return "addAnnouncment.html";
    }

    @PostMapping("/addAnnouncment")
    public String add(Announcment announcment) {
        announcmentRepository.save(announcment);
        return "redirect:/list";
    }

    @GetMapping("/adminlist")
    public String getAdminView(Model model){
        List<Announcment> allAnnouncments = (List<Announcment>) announcmentRepository.findAll();
        model.addAttribute("announcments", allAnnouncments);
        return "adminList";
    }


}
