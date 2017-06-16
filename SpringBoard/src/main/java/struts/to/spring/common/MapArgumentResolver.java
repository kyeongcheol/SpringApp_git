package struts.to.spring.common;

import java.util.Enumeration; //열거형

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

import struts.to.spring.common.BoardCommonMap;

//파라미터 수집
//컨트롤러에 들어오는 파라미터를 수정하거나 공통적으로 추가 해주어야 하는 경우 사용
//그래서 컨트롤러에 도달하기 전에 그 요청의 파라미터를 수정할 수 있다.
public class MapArgumentResolver implements HandlerMethodArgumentResolver
{
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) 
	{
		//Resolver가 적용 가능한지 검사하는 역할
		//컨트롤러의 파라미터가 BoardCommandMap 클래스인지 검사
		return BoardCommonMap.class.isAssignableFrom(parameter.getParameterType());
		
		//Controller에서는 Map 형식을 -> BoardCommonMap 객체로 파라미터 값을 받음
	
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, 
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception 
	{
		//BoardCommonMap 객체 생성
		BoardCommonMap bcm = new BoardCommonMap();
		
		//서블릿 request를 이용해 파라미터 수집
		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		
		//수집된 파라미터를 열거형 객체에 넣어줌
		//수집된 파라미터는 map key와 value
		Enumeration<?> enumeration = request.getParameterNames();
		
		String key = null;
		String[] values = null;
		
		while(enumeration.hasMoreElements())
		{
			//열거형 객체를 뒤져서 key를 String 형의 key 변수에 집어넣고..
			key = (String)enumeration.nextElement();
			
			//그 key를 이용하여 value를 찾음
			values = request.getParameterValues(key);
			
			//만약 key에 해당되는 value가 있으면
			if(values!=null)
			{
				if(values.length > 1)
				{
					bcm.put(key, values);
				}
				//end of if
				else
				{
					bcm.put(key, values[0]);
				}
				//end of else
			} //end of if
			
		} //end of while
		
		return bcm;
	}
	//end of method

	
}
