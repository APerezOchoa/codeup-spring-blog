package com.codeup.codeupspringblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {
    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello, World!";
    }

    //GetMapping is the more short-handed version and RequestMapping is the longer
    @GetMapping("/hello/{name}")
    @ResponseBody
    public String sayHello(@PathVariable String name) {
        return "Hello " + name + "!";
    }

    @RequestMapping(path = "/increment/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String addOne(@PathVariable int number) {
        return String.format("%d plus 1 is %d!", number, number +1);
    }

    @GetMapping("/exponent/{num}/{pow}")
    @ResponseBody
    public String exponent(@PathVariable int num, @PathVariable int pow){
        return String.format("%d to the power of %d = %d", num, pow, (int)(Math.pow(num,pow)));
    }

}
