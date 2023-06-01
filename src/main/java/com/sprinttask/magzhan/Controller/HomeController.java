package com.sprinttask.magzhan.Controller;


import com.sprinttask.magzhan.db.ApplicationRequest;
import com.sprinttask.magzhan.dbconnection.UserManager;
import com.sprinttask.magzhan.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserManager manager;

    @Autowired
    private RequestRepository requestRepository;

    @GetMapping(value="/")
    public String indexPage(Model model) {
        List<ApplicationRequest> applicationRequests=requestRepository.findAll();
        model.addAttribute("zayavkalar",applicationRequests);
        return "index";
    }

    @GetMapping (value="/add-request")
        public String addRequestPage (Model model){
        return "addrequest";
    }

    @PostMapping (value="add-request")
    public String addRequest (ApplicationRequest applicationRequest){
     requestRepository.save(applicationRequest);
     return "redirect:/";
    }

    @GetMapping (value="/request-details")
    public String getRequest(@RequestParam(name = "id") Long id, Model model){
        ApplicationRequest applicationRequest=requestRepository.findById(id).orElse(null);
        model.addAttribute("zayavka",applicationRequest);
        return "details";
    }

    @PostMapping (value="/delete-request")
    public String deleteRequest (@RequestParam(name="id") Long id){
        requestRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping(value="/new-requests")
    public String newRequestPage(Model model) {
        List<ApplicationRequest> applicationRequests=requestRepository.findAllByHandled(false);
        model.addAttribute("zayavkalar",applicationRequests);
        return "newrequests";
    }

    @GetMapping(value="/handled-requests")
    public String HandledRequestPage(Model model) {
        List<ApplicationRequest> applicationRequests=requestRepository.findAllByHandled(true);
        model.addAttribute("zayavkalar",applicationRequests);
        return "handledrequests";
    }

    @PostMapping(value = "/handle-request")
    public String HandleRequest(ApplicationRequest applicationRequest){
        applicationRequest.setHandled(true);
        requestRepository.save(applicationRequest);
        return "redirect:/";
    }





}
