package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Ad;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.AdRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdController {

    private final UserRepository userDao;
    private final AdRepository adDao;
    private final EmailService emailService;

    public AdController(UserRepository userDao, AdRepository adDao, EmailService emailService) {
        this.userDao = userDao;
        this.adDao = adDao;
        this.emailService = emailService;
    }

    @GetMapping("/ads")
    public String showAllAds(Model model){
        model.addAttribute("ads", adDao.findAll());
        return "ads/index";
    }

    @GetMapping("/ads/search")
    public String showAllAds(@RequestParam String query, Model model){
        model.addAttribute("ads", adDao.searchByTitleLike(query));
        return "ads/index";
    }

    @GetMapping("/ads/{id}")
    public String getOneAd(@PathVariable long id, Model model){
        model.addAttribute("ad", adDao.findAdById(id));
        return "ads/show";
    }

    @GetMapping("/ads/create")
    public String showAdForm(Model model){
        model.addAttribute("ad", new Ad());
        return "ads/create";
    }
    @PostMapping("/ads/save")
    public String saveNewAd(@ModelAttribute Ad ad){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Ad origAd = adDao.findAdById(ad.getId());
        if(origAd == null || user.getId() == origAd.getUser().getId()){
            ad.setUser(user);
            adDao.save(ad);
            emailService.prepareAndSend(ad);
        }
        return "redirect:/ads";
    }

    @GetMapping("/ads/{id}/edit")
    public String editAdForm(Model model, @PathVariable long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Ad ad = adDao.findAdById(id);
        if (user.getId() == ad.getUser().getId()) {
            model.addAttribute("ad", ad);
            return "ads/create";
        } else {
            return "redirect:/ads";
        }
    }

}
