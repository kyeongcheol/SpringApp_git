package first.common.logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//로그
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

//Interceptor
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter
{
	protected Log log = LogFactory.getLog(LoggerInterceptor.class);

	
	//컨트롤러 실행하기 전
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
    		Object handler) throws Exception 
		{
			if(log.isDebugEnabled())
		     {
		    	   log.debug("======================================          "
		    	   		+ "START         ======================================");
		    	   
		    	   log.debug(" Request URI \t: " + request.getRequestURI());
		      }
			
		   return super.preHandle(request, response, handler);
		}
		
	//컨트롤러 실행하고 뷰 실행하기 전
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) throws Exception 
	{
		if(log.isDebugEnabled())
		{
			log.debug("======================================           "
					+ "END          ======================================\n");
		}
      
	}
	
}
