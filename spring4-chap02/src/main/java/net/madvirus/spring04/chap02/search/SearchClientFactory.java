package net.madvirus.spring04.chap02.search;

public interface SearchClientFactory 
{
	public void init();
	
	public SearchClient create();

}
