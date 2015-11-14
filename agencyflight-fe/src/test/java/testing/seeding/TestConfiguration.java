package testing.seeding;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.devcoo.agencyflight.core.user.UserService;
import com.devcoo.agencyflight.core.user.UserServiceImp;

@Configuration
public class TestConfiguration {
	@Bean
	public UserService getUserService() {
		return new UserServiceImp();
	}
}
