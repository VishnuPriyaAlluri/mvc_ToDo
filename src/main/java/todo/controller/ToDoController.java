package todo.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import todo.dto.ToDoTask;
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
  
  @GetMapping("/home")
	public String loadHome(HttpSession session, ModelMap model) {
		return service.loadHome(session,model);
	}
  
  @PostMapping("/signup")
 
  public String signup(ToDoUser user,@RequestParam String date,ModelMap map) {
//	  user.setDob(LocalDate.parse(date));
	  return service.signup(user, date,map);
  }
  @PostMapping("/login")  
  public String login(@RequestParam String email,String password,ModelMap map,HttpSession session) {
	return service.login(email,password,map,session);
}

  @GetMapping("/logout")
  public String logout(HttpSession session,ModelMap map) {
	return service.logout(session,map);
}

  @GetMapping("/add-task")
  public String loadAddTask(HttpSession session,ModelMap map) {
	return service.addTask(session,map);
}

@PostMapping("/add-task")
public String addTask(ToDoTask task,HttpSession session,ModelMap map) {
	return service.addTask(task,session,map);
}

@GetMapping("/change-status")
public String changeStatus(@RequestParam int id,HttpSession session,ModelMap map) {
	return service.changeStatus(id,session,map);
}
@GetMapping("/delete")
public String deleteTask(@RequestParam int id,HttpSession session,ModelMap map) {
	return service.deleteTask(id,session,map);
}

@GetMapping("/edit")
public String editTask(@RequestParam int id,HttpSession session,ModelMap map) {
	return service.loadEdit(id,session,map);
}

@PostMapping("/update-task")
public String updateTask(HttpSession session,ModelMap map,ToDoTask task) {
	return service.updateTask(session,map,task);
}

  
 
}
