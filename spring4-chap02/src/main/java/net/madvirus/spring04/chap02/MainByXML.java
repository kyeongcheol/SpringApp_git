package net.madvirus.spring04.chap02;

import org.springframework.context.support.GenericXmlApplicationContext;

import net.madvirus.spring04.chap02.AuthException;
import net.madvirus.spring04.chap02.AuthenticationService;
import net.madvirus.spring04.chap02.PasswordChangeService;
import net.madvirus.spring04.chap02.UserNotFoundException;

public class MainByXML 
{
	public static void main(String [] args)
	{
		GenericXmlApplicationContext ctx =
				new GenericXmlApplicationContext("classpath:config.xml");
		
		AuthenticationService authSvc
		   = ctx.getBean("authenticationService", AuthenticationService.class);
		
		//AuthException 처리, 비밀번호만 다를시 
		runAuthAndCatchAuthEx(authSvc, "bkchoi", "1111");
		runAuthAndCatchAuthEx(authSvc, "bkchoi", "11111");
		runAuthAndCatchAuthEx(authSvc, "bkchoi", "111111");
		
		//UserNotFoundException 처리..(ID 값이 다를 경우..)
		try
		{
			authSvc.authenticate("bkchoi2", "1111");
		}
		catch(UserNotFoundException ex)
		{
			
		}
		authSvc.authenticate("bkchoi", "1234");
		PasswordChangeService pwChgSvc = ctx.getBean(PasswordChangeService.class);
		pwChgSvc.changePassword("bkchoi", "1234", "5678");
		runAuthAndCatchAuthEx(authSvc, "bkchoi", "1234");
		authSvc.authenticate("bkchoi", "5678");
		ctx.close();
		 
	}
	
	private static void runAuthAndCatchAuthEx
	(AuthenticationService authSvc, String userId, String password)
	{
		try
		{
			authSvc.authenticate(userId, password);
		}
		catch(AuthException ex)
		{
			
		}
		
	}

}

