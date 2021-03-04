package pl.edu.wszib.project.forum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.project.forum.model.Post;
import pl.edu.wszib.project.forum.model.User;
import pl.edu.wszib.project.forum.services.IPostService;
import pl.edu.wszib.project.forum.services.IThreadService;
import pl.edu.wszib.project.forum.services.IUserService;
import pl.edu.wszib.project.forum.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class AdminController {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IPostService postService;
    @Autowired
    IUserService userService;
    @Autowired
    IThreadService threadService;


    @RequestMapping(value = "/thread{threadId}/delete/{postId}",method = RequestMethod.GET)
    public String deletePost(@PathVariable int threadId,@PathVariable int postId){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN)
            return "redirect:/thread{threadId}";


        Post post = postService.getPostById(postId);
        this.postService.deletePost(post);
        return "redirect:/thread{threadId}/page1";
    }

    @RequestMapping(value = "/t{tid}/ban{id}",method = RequestMethod.GET)
    public String banUser(@PathVariable int id,@PathVariable int tid){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN)
            return "redirect:/thread{tid}/page1";

        User user = this.userService.getUserById(id);
        this.userService.banUser(user);
        return "redirect:/thread{tid}/page1";
    }

    @RequestMapping(value = "/t{tid}/unban{id}",method = RequestMethod.GET)
    public String unbanUser(@PathVariable int id,@PathVariable int tid){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN)
            return "redirect:/thread{tid}/page1";

        User user = this.userService.getUserById(id);
        this.userService.unbanUser(user);
        return "redirect:/thread{tid}/page1";
    }

    @RequestMapping(value = "/delete/thread{threadId}",method = RequestMethod.GET)
    public String deleteThread(@PathVariable int threadId){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN)
            return "redirect:/main";
        this.threadService.deleteThread(threadId);


        return "redirect:/main";
    }


}
