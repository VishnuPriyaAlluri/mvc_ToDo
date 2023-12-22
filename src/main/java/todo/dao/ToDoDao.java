package todo.dao;

import java.util.List;

import javax.persistence.EntityManager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import todo.dto.ToDoUser;

@Component
public class ToDoDao {
     @Autowired 
	EntityManager  manager;    
	
			//finding user by email
		public List<ToDoUser> findByEmail(String Email){
			 return manager.createQuery("select x from ToDoUser x where email=?1").setParameter(1, Email).getResultList();
		}
		
		//saving user
				public void save(ToDoUser user) {
					manager.getTransaction().begin();
					manager.persist(user);
					manager.getTransaction().commit();
				}

//
//		//saving task
//		public void saveTask(Task task) {
//			manager.getTransaction().begin();
//			manager.persist(task);
//			manager.getTransaction().commit();
//		}
//		//updating user
//		public void updateUser(UserDto dto) {
//			manager.getTransaction().begin();
//			manager.merge(dto);
//			manager.getTransaction().commit();
//		}
//		public Task findTaskById(int id) {
//			return manager.find(Task.class, id);
//		}
//		public void updateTask(Task task) {
//			manager.getTransaction().begin();
//			manager.merge(task);
//			manager.getTransaction().commit();
//		}
//		
//		public UserDto findById(int id)
//		{
//			return manager.find(UserDto.class, id);
//		}
//		public void deleteTask(Task task) {
//			manager.getTransaction().begin();
//			manager.remove(task);
//			manager.getTransaction().commit();
//		}
}
