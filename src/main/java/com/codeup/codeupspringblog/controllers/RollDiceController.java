package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String guessForm(){
        return "roll-dice/index";
    }

    @GetMapping("/roll-dice/{n}")
    public String roll(@PathVariable int n, Model model) {
        model.addAttribute("n", n);
        model.addAttribute("result", (int)(Math.floor(Math.random()*6)+1));
        return "roll-dice/show";
    }
}
