package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Ad;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final UserRepository userDao;
    private final PostRepository postDao;
    private final EmailService emailService;

    public PostController(UserRepository userDao, PostRepository postDao, EmailService emailService) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String posts(Model model){
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postsId(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.findPostById(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createForm(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }
    @PostMapping("/posts/save")
    public String postsCreate(@ModelAttribute Post post){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post origPost = postDao.findPostById(post.getId());
        if(origPost == null || user.getId() == origPost.getUser().getId()){
            post.setUser(user);
            postDao.save(post);
            emailService.prepareAndSend(post);
        }
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPostForm(Model model, @PathVariable long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postDao.findPostById(id);
        if (user.getId() == post.getUser().getId()) {
            model.addAttribute("post", post);
            return "posts/create";
        } else {
            return "redirect:/posts";
        }
    }

}
