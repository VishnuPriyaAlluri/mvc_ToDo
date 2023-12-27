package todo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.ui.ModelMap;

import todo.dao.ToDoDao;
import todo.dto.ToDoTask;
import todo.dto.ToDoUser;
import todo.helper.AES;
@Component
public class ToDoService {
	@Autowired
     ToDoDao dao;
     
     public String Signup(ToDoUser user,String date, ModelMap map) {
    	 user.setDob(LocalDate.parse(date));
 		user.setAge(Period.between(user.getDob(), LocalDate.now()).getYears());
 		user.setPassword(AES.encrypt(user.getPassword(), "123"));
 		if(user.getAge()<18) {
 			map.put("dob", "* You have to be 18+ to create Account");
 			return "Signup";
 		}
 		else{
 			
			List<ToDoUser> list = dao.findByEmail(user.getEmail());
			// checking email is repeated
			if (list.isEmpty()) {
				
				dao.save(user);
				map.put("pass","Account Created Success");
				return "Login";
			}
			else {
				map.put("email","Email Should is unique");
				return "Signup";
			}
 		}     
     }

	public String login(String email, String password, ModelMap map,HttpSession session) {
		
	List<ToDoUser> list=dao.findByEmail(email);
	if(list.isEmpty()) {
		map.put("email","Invalid Email");
		return "Login";
	}else {
		if(password.equals(AES.decrypt(list.get(0).getPassword(),"123"))) {
			session.setAttribute("todouser",list.get(0));
			map.put("pass","Login Success");
			return "ToDoHome";
		}else {
			map.put("password","Incorrect Password");
			return "Login";
		}
	}
	

	}
	
	public String loadHome(HttpSession session,ModelMap map) {
		ToDoUser user = (ToDoUser) session.getAttribute("todouser");
		if (user == null) {
			map.put("fail", "Invalid Session");
			return "Login";
		} else {
			map.put("list", dao.fetchAllTask(user.getId()));
			return "TodoHome";
		}

	}

	public String logout(HttpSession session, ModelMap map) {
		session.invalidate();
		map.put("pass","Logout Success");
		return "Login";
	}

	public String addTask(HttpSession session, ModelMap map) {
		ToDoUser user = (ToDoUser) session.getAttribute("todouser");	
		if(user==null) {
			map.put("fail","Invalid Session");
			return "Login";
		}else {
			return "AddTask";
		}
	}

	public String addTask(ToDoTask task, HttpSession session, ModelMap map) {
		ToDoUser user = (ToDoUser) session.getAttribute("todouser");
		if(user==null) {
			map.put("fail","Invalid Session");
			return "Login";
		}else {
			task.setCreatedTime(LocalDateTime.now());
			task.setUser(user);
			task.setStatus(false);
			
			dao.save(task);
			
			map.put("list",dao.fetchAllTask(user.getId()));
			map.put("pass","Data Saved Successfully");
			return "ToDoHome";
		}
	}

}
