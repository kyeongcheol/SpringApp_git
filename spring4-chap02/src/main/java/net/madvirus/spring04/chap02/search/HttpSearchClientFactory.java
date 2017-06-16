package net.madvirus.spring04.chap02.search;

public class HttpSearchClientFactory implements SearchClientFactory
{
	private String server;
	private String contentType;
	private String encoding;

	@Override
	public void init() 
	{
		
	}

	@Override
	public SearchClient create() 
	{

		return null;
	}
   
  
}
