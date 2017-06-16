function getXMLHttpRequest()
{
   if(window.ActiveXObject) //IE 8 이상
   {
	  try{ return new ActiveXObject("Msxml2.XMLHTTP"); }
	  catch(e)
	  {
	     try{ return new ActiveXObject("Microsoft.XMLHTTP"); }
	      
	     catch(e1) {return null;}
	  }
	}
   else if(window.XMLHttpRequest) //모질라, 사파리 등 그외 브라우저
   {
	 return new XMLHttpRequest();   
   }
   else
   {
	 return null;   
   }
}
var httpRequest = null;

//sendRequest(url 경로, 전달할 파라미터, 완료시 콜백함수, get/post)
function sendRequest(url, params, callback, method)
{
  httpRequest = getXMLHttpRequest();
  //전송 방식 설정
  var httpMethod = method ? method : 'GET';
  
  if(httpMethod != 'GET' && httpMethod != 'POST')
  {
	 httpMethod = 'GET';  
  }
  //파라미터 값 설정
  var httpParams = (params == null || params == '') ? null : params;
  //url 설정
  var httpUrl = url;
  
  if(httpMethod == 'GET' && httpParams != null)
  {
	  httpUrl = httpUrl + "?" + httpParams;
  }
  
  //(전송방식, 요구할 페이지의 url, 비동기식으로 처리할지의 여부)
  httpRequest.open(httpMethod, httpUrl, true);
  httpRequest.setRequestHeader(
  'Content-Type', 'application/x-www-form-urlencoded');
  //처리할 콜백함수 지정, 서버가 클라이언트에 보낸 데이터를 연산해서 처리할 함수를 연결
  httpRequest.onreadystatechange = callback;
  //파라미터 전송
  httpRequest.send(httpMethod == 'POST' ? httpParams : null);
}
