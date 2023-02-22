package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String posts(Model model){
        ArrayList<Post> posts = new ArrayList<>();
        Post post1 = new Post("Post Number 1", "Free stuff all over the place");
        Post post2 = new Post("Post Number 2", "Free stuff in some places.");
        posts.add(post1);
        posts.add(post2);
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postsId(@PathVariable int id, Model model) {
        Post post = new Post("Free Stuff1", "2Alot of free stuff.");
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String postsCreate(){
        return "Viewing the form for creating a post.";
    }

}
