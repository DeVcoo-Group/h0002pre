package test.user;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.devcoo.agencyflight.core.user.User;
import com.devcoo.agencyflight.core.user.UserService;


public class TestUser {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		UserService userManager = (UserService) ctx.getBean("userServiceImp");
//
//		List<User> list = userManager.findAllUsers();
//		// System.out.println("User count: " + list.size());
//
		User user = new User();
		user.setName("John Doe");
//		userManager.insert(user);
//		List<User> list=userManager.getUser();
//		System.out.println("User list "+list.size());
		
		User user1= userManager.getAuthoriseUser("abc","123");
		
		System.out.println("User  "+user1.getName());

	}

}
