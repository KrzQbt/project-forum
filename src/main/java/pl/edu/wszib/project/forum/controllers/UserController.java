package pl.edu.wszib.project.forum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.project.forum.model.ForumThread;
import pl.edu.wszib.project.forum.model.Post;
import pl.edu.wszib.project.forum.model.User;
import pl.edu.wszib.project.forum.model.view.ThreadModel;
import pl.edu.wszib.project.forum.services.IPostService;
import pl.edu.wszib.project.forum.services.IThreadService;
import pl.edu.wszib.project.forum.services.IUserService;
import pl.edu.wszib.project.forum.session.SessionObject;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    IUserService userService;
    @Autowired
    IThreadService threadService;
    @Autowired
    IPostService postService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {
        if(this.sessionObject.isLogged()) {
            return "redirect:/main";
        }
        model.addAttribute("userModel", new User());
        return "login";
    }

    @RequestMapping(value = "/banned",method = RequestMethod.GET)
    public String banned(){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.BANNED)
            return "redirect:/main";
        return "banned";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute User user) {
        this.userService.authenticate(user);
        if(this.sessionObject.isLogged()) {
            return "redirect:/main";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.userService.logout();
        return "redirect:/main";
    }

    @RequestMapping(value = "/newthread", method = RequestMethod.GET)
    public String threadForm(Model model){

        if(!this.sessionObject.isLogged())
            return "redirect:/login";
        if (this.sessionObject.getLoggedUser().getRole() == User.Role.BANNED)
            return "redirect:/banned";

        ForumThread forumThread = new ForumThread();
        model.addAttribute("thread",forumThread);
        return "newthread";
    }

    @RequestMapping(value = "/newthread", method = RequestMethod.POST)
    public String newThread(@ModelAttribute ForumThread forumThread){
        if(!this.sessionObject.isLogged())
            return "redirect:/login";
        if (this.sessionObject.getLoggedUser().getRole() == User.Role.BANNED)
            return "redirect:/banned";

        this.threadService.createThread(forumThread);
        return "redirect:/main";
    }

    @RequestMapping(value = "/thread{id}",method = RequestMethod.GET)
    public String showThread(@PathVariable int id, Model model){
        if(this.sessionObject.isLogged()) {
            if (this.sessionObject.getLoggedUser().getRole() == User.Role.BANNED)
                return "redirect:/banned";
        }
        ThreadModel tm = this.threadService.displayThread(id);

        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);

        model.addAttribute("thread",this.threadService.displayThread(id));
        model.addAttribute("posts",tm.getPostsInThread());
        if(this.sessionObject.isLogged()){
            if (this.sessionObject.getLoggedUser().getRole() == User.Role.ADMIN)
                return "adminthread";}
        return "thread";
    }

    @RequestMapping(value = "/thread{id}/page{pid}",method = RequestMethod.GET)
    public String showPaginatedThread(@PathVariable int id, @PathVariable int pid ,Model model){
        if(!this.sessionObject.isLogged())
            return "redirect:/login";

        if(this.sessionObject.isLogged()) {
            if (this.sessionObject.getLoggedUser().getRole() == User.Role.BANNED)
                return "redirect:/banned";
        }
        ThreadModel tm = this.threadService.displayPaginatedThread(id,pid);


        model.addAttribute("currentPage",pid);
        model.addAttribute("allPages",threadService.calculatePages(this.postService.countPost(id))); // how many pages in this thread
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);

        model.addAttribute("thread",this.threadService.displayThread(id));
        model.addAttribute("posts",tm.getPostsInThread());
        if(this.sessionObject.isLogged()){
            if (this.sessionObject.getLoggedUser().getRole() == User.Role.ADMIN)
                return "adminthread";}
        return "thread";
    }



    @RequestMapping(value = "/thread{id}/post",method = RequestMethod.GET)
    public String newPostInThread(@PathVariable int id,Model model){
        if(!this.sessionObject.isLogged())
            return "redirect:/login";
        if (this.sessionObject.getLoggedUser().getRole() == User.Role.BANNED)
            return "redirect:/banned";


        Post post = new Post();
        model.addAttribute("post",post);
        return "newpost";
    }
    @RequestMapping(value = "/thread{id}/post",method = RequestMethod.POST)
    public String newPostInThread(@PathVariable int id,@ModelAttribute Post post){
        if(!this.sessionObject.isLogged())
            return "redirect:/login";
        if (this.sessionObject.getLoggedUser().getRole() == User.Role.BANNED)
            return "redirect:/banned";

        int userId = sessionObject.getUserId();
        System.out.println(post.getContent() + "controller post");

        this.postService.createPostInThread(post,id,userId);
        return "redirect:/thread{id}/page1";
    }


}
