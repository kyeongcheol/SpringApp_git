package net.madvirus.spring04.chap02;

import net.madvirus.spring04.chap02.AuthenticationService;
import net.madvirus.spring04.chap02.PasswordChangeService;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainByXmlNamespace 
{
	public static void main(String [] args)
	{
		GenericXmlApplicationContext ctx 
		 = new GenericXmlApplicationContext("classpath:config-with-*.xml");
		
		AuthenticationService authSvc
		  = ctx.getBean("authenticationService", AuthenticationService.class);
		
		//bean의 id값이 지정되어 있지 않아도 기술된 타입으로 빈을 구해서 리턴할 수 있음
		authSvc.authenticate("bkchoi", "1234");
		
		PasswordChangeService pwChgSvc = ctx.getBean(PasswordChangeService.class);
		pwChgSvc.changePassword("bkchoi", "1234", "5678");
		authSvc.authenticate("bkchoi", "5678");
		ctx.close();
	}

}
