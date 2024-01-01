package todo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
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

	public String signup(ToDoUser user, String date, ModelMap map) {
		user.setDob(LocalDate.parse(date));
		user.setAge(Period.between(user.getDob(), LocalDate.now()).getYears());
		user.setPassword(AES.encrypt(user.getPassword(), "123"));
		;
		if (user.getAge() < 18) {
			map.put("dob", "* You have to be 18+ to create Account");
			return "Signup";
		} else {
			List<ToDoUser> list = dao.findByEmail(user.getEmail());
			if (list.isEmpty()) {
				dao.save(user);
				map.put("pass", "Account Created Success");
				return "Login";
			} else {
				map.put("email", "* Email Should be Unique");
				return "Signup";
			}
		}
	}

	public String login(String email, String password, ModelMap map, HttpSession session) {
		List<ToDoUser> list = dao.findByEmail(email);
		if (list.isEmpty()) {
			map.put("email", "* Incorrect Email");
			return "Login";
		} else {
			if (password.equals(AES.decrypt(list.get(0).getPassword(), "123"))) {
				session.setAttribute("todouser", list.get(0));

				map.put("list", dao.fetchAllTask(list.get(0).getId()));
				map.put("pass", "Login Success");
				return "ToDoHome";
			} else {
				map.put("password", "* Incorrect Password");
				return "Login";
			}
		}
	}
	public String loadHome(HttpSession session, ModelMap map) {
		ToDoUser user = (ToDoUser) session.getAttribute("todouser");
		if (user == null) {
			map.put("fail", "Invalid Session");
			return "Login";
		} else {
			map.put("list", dao.fetchAllTask(user.getId()));
			return "ToDoHome";
		}
	}

	public String logout(HttpSession session, ModelMap map) {
		session.invalidate();
		map.put("pass", "Logout Success");
		return "Login";
	}

	public String addTask(HttpSession session, ModelMap map) {
		ToDoUser user = (ToDoUser) session.getAttribute("todouser");
		if (user == null) {
			map.put("fail", "Invalid Session");
			return "Login";
		} else {
			return "AddTask";
		}
	}
	public String addTask(ToDoTask task, HttpSession session, ModelMap map) {
		ToDoUser user = (ToDoUser) session.getAttribute("todouser");
		if (user == null) {
			map.put("fail", "Invalid Session");
			return "Login";
		} else {
			task.setCreatedTime(LocalDateTime.now());
			task.setUser(user);
			dao.save(task);

			map.put("list", dao.fetchAllTask(user.getId()));
			map.put("pass", "Data Saved Success");
			return "ToDoHome";
		}
	}
	public String changeStatus(int id, HttpSession session, ModelMap map) {
		ToDoUser user = (ToDoUser) session.getAttribute("todouser");
		if (user == null) {
			map.put("fail", "Invalid Session");
			return "Login";
		} else {
			ToDoTask task = dao.fetchTaskById(id);
			task.setStatus(true);
			dao.update(task);
			map.put("list", dao.fetchAllTask(user.getId()));
			map.put("pass", "Status Changed Success");
			return "ToDoHome";
		}
	}
	public String deleteTask(int id, HttpSession session, ModelMap map) {
		ToDoUser user = (ToDoUser) session.getAttribute("todouser");
		if (user == null) {
			map.put("fail", "Invalid Session");
			return "Login";
		} else {
			ToDoTask task = dao.fetchTaskById(id);
			dao.delete(task);
			map.put("list", dao.fetchAllTask(user.getId()));
			map.put("pass", "Task Deleted Success");
			return "ToDoHome";
		}
	}
	public String loadEdit( int id,HttpSession session, ModelMap map) {
		ToDoUser user = (ToDoUser) session.getAttribute("todouser");
		if (user == null) {
			map.put("fail", "Invalid Session");
			return "Login";
		} else {
			ToDoTask task = dao.fetchTaskById(id);
			map.put("task", task);
			return "EditTask";
		}
	}
	public String updateTask( HttpSession session, ModelMap map,ToDoTask task) {
		ToDoUser user = (ToDoUser) session.getAttribute("todouser");
		if (user == null) {
			map.put("fail", "Invalid Session");
			return "Login";
		} else {
			task.setUser(user);
			task.setCreatedTime(LocalDateTime.now());
			dao.update(task);
			map.put("list", dao.fetchAllTask(user.getId()));
			map.put("pass", "Updated Success");
			return "ToDoHome";
		}
	}

	
}
