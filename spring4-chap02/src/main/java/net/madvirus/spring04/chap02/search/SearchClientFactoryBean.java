package net.madvirus.spring04.chap02.search;

import org.springframework.beans.factory.FactoryBean;

public class SearchClientFactoryBean implements FactoryBean<SearchClientFactory> 
{
	private String server;
	private int port;
	private String contentType;
	private String encoding="utf-8";
	
	private SearchClientFactory searchClientFactory;

	public void setServer(String server) {
		this.server = server;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	//오버라이딩
	//실제 스프링 빈으로 사용될 객체 리턴
	@Override
	public SearchClientFactory getObject() throws Exception 
	{
        if(this.searchClientFactory!=null) //this : SearchClientFactory, 객체를 의미
        	return this.searchClientFactory;
        
		SearchClientFactoryBuilder builder = new SearchClientFactoryBuilder();
		builder.server(server)
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	

}
