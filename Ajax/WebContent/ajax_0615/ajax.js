//패키지 생성 방법
var ajax = {};
ajax.xhr = {};

//httpRequest.js의 sendRequest 메서드와 같음
ajax.xhr.Request = function(url, params, callback, method)
{
  this.url = url;
  this.params = params;
  this.callback = callback;
  this.method = method;
  this.send();
}

ajax.xhr.Request.prototype = 
{
	//이름 : 값
	getXMLHttpRequest: function() 
	{
		if (window.ActiveXObject) 
		{
			try 
			{
				return new ActiveXObject("Msxml2.XMLHTTP");
			} 
			catch(e) 
			{
				try 
				{
					return new ActiveXObject("Microsoft.XMLHTTP");
				} 
				catch(e1) { return null; }
			}
		} 
		else if (window.XMLHttpRequest) {return new XMLHttpRequest();} 
		
		else {return null;}		
		
	}, //정의가 끝나면 , (콤마로) 구분
		
	send: function()
	{

	    this.req = this.getXMLHttpRequest();
		
		var httpMethod = this.method ? this.method : 'GET';
		if (httpMethod != 'GET' && httpMethod != 'POST') 
		{
			httpMethod = 'GET';
		}
		var httpParams = (this.params == null || this.params == '') ? 
		                 null : this.params;
		var httpUrl = this.url;
		if (httpMethod == 'GET' && httpParams != null) 
		{
			httpUrl = httpUrl + "?" + httpParams;
		}
		this.req.open(httpMethod, httpUrl, true);
		this.req.setRequestHeader(
			'Content-Type', 'application/x-www-form-urlencoded');
		var request = this;
		this.req.onreadystatechange = function() 
		{
			request.onStateChange.call(request);
		}
		this.req.send(httpMethod == 'POST' ? httpParams : null);
	},
	
	onStateChange: function() 
	{
		this.callback(this.req);
	}
	
	
}