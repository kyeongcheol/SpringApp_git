package net.madvirus.spring04.chap02.conf;

import net.madvirus.spring04.chap02.AuthFailLogger;
import net.madvirus.spring04.chap02.AuthenticationService;
import net.madvirus.spring04.chap02.PasswordChangeService;
import net.madvirus.spring04.chap02.User;
import net.madvirus.spring04.chap02.UserRepository;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config 
{
	@Bean
	public User user1()
	{
		return new User("bkchoi", "1234");
	}
	
	@Bean(name="user2")
	public User user()
	{
		return new User("madvirus", "1234");
	}
	
	@Bean
	public UserRepository userRepository()
	{
		UserRepository userRepo = new UserRepository();
		userRepo.setUsers(Arrays.asList(user1(),user()));
		return userRepo;
	}
	
	@Bean
	public PasswordChangeService pwChangeSvc()
	{
		return new PasswordChangeService(userRepository());
	}
	
	@Bean
	public AuthFailLogger authFailLogger()
	{
		AuthFailLogger logger = new AuthFailLogger();
		logger.setThreshold(2);
		return logger;
	}
	
	@Bean
	public AuthenticationService authenticationService()
	{
		AuthenticationService authSvc = 
				new AuthenticationService();
		authSvc.setFailLogger(authFailLogger());
		authSvc.setUserRepository(userRepository());
		return authSvc;
	}
	
	/*
	return new PasswordChangeService(userRepository());
	
	authSvc.setUserRepository(userRepository());
	
	2 line을 보면 같은 userRepository() 메서드가 리턴하는 동일한 객체를 가져다 쓰고 있다.

    userRepository() 메서드는 UserRepository 객체를 생성하여 매번 새로운 객체를 리턴하기 때문에,

    2 line이 호출하는 객체는 서로 달라야 한다.

         하지만 스프링은 설정 클래스를 상속받아 새로운 클래스를 만들어내기 때문에 여러 번 호출해도 실제로는 한 객체만 리턴이 되는 것.
      	
		
	*/

}
