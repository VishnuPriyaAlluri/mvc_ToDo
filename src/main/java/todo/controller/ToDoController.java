package todo.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import todo.dto.ToDoUser;
import todo.service.ToDoService;

@Controller
public class ToDoController {
	@Autowired
	ToDoService service;
  @GetMapping({"/","/login"})
  public String loadLogin() {
	  return "Login";
  }
  
  @GetMapping("/Signup")
  public String loadSignup() {
	  return "Signup";
  }
  
  @PostMapping("/signup")
 
  public String signup(ToDoUser user,@RequestParam String date,ModelMap map) {
	  user.setDob(LocalDate.parse(date));
	  return service.Signup(user, date,map);
  }
  
//  @PostMapping("/Login")
//  public String Login(ToDoUser user,@RequestParam String Email,String Password,ModelMap map) {
//	  
//	 if(service.login(Email,Password)) {
//		 user.getfindByEmail(Email);
//	 }
//  }
  
 
}
