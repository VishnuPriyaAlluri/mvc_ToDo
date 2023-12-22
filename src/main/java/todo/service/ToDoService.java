package todo.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import todo.dao.ToDoDao;
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
				map.put("email","Email is unique");
				return "Signup";
			}
 		}     
     }
//
//	public boolean login(String email, String password) {
//		// TODO Auto-generated method stub
//		return false;
//	}

//	public String login(String email, String password) {
//		List<ToDoUser> user=dao.findByEmail(email);
//		if(user==null) {
//			map.put()
//			return "Login";
//		}else {
//			if (password.equals(AES.decrypt(user.getPassword(), "123")))
//				return "Signup";
//			else
//				return "";
//		}
//		}
//		
//	}

}
