package com.codeup.codeupspringblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String posts(){
        return "This is the posts index page.";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String postsId(@PathVariable int id){
        return String.format("This is where you would view an individual post. With the given id of %d.", id);
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String postsCreate(){
        return "This is where you would view the form for creating a post.";
    }

}
