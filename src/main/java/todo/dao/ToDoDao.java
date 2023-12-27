package todo.dao;

import java.util.List;

import javax.persistence.EntityManager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import todo.dto.ToDoTask;
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

				public void save(ToDoTask task) {
					
					manager.getTransaction().begin();
					manager.persist(task);
					manager.getTransaction().commit();
				}

				public List<ToDoTask> fetchAllTask(int id) {
					return manager.createQuery("select x from ToDoTask x where user_id=?1").setParameter(1,id).getResultList();
				}				

}
