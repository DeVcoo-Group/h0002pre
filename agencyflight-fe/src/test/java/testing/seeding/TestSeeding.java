package testing.seeding;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.devcoo.agencyflight.core.std.ApplicationContext;
import com.devcoo.agencyflight.core.user.User;
import com.devcoo.agencyflight.core.user.UserService;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="/spring-master.xml")
public class TestSeeding {
//	@Test
	@DirtiesContext
	public void init() {
		System.out.println("*********************Testing Process**********************");
		UserService userService = (UserService) ApplicationContext.getContext().getBean("userServiceImp");
		List<User> users = userService.findUser("m", "123");
		if(users.isEmpty()) {
			User userTest = new User();
			userTest.setName("m");
			userTest.setPassword("123");
			userTest.setDelete(false);
			userService.save(userTest);
			System.out.println("User Insert Success!!!!!!!!");
		} else {
			System.out.println("User Test Exist!!!!!");
		}
		//CountryService countryService = (CountryService) ApplicationContext.getContext().getBean("countryServiceImp");
		System.out.println("*********************Ending Testing Process**********************");
	}
}
