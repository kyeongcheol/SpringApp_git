package net.madvirus.spring04.chap02.erp;

import java.util.Properties;

public class DefaultErpClientFactory extends ErpClientFactory
{
	private String server;
	
	public DefaultErpClientFactory(Properties props)
	{
		this.server = props.getProperty("server");
	}

	//추상메서드 오버라이딩
	@Override
	public ErpClient create() 
	{
	   //anonymous inner class 생성
       return new ErpClient()
       {
    	   
    	//오버라이딩   
		@Override
		public void connect() 
		{
			System.out.println("연결함:" + server);
		}

		@Override
		public void close() 
		{
			System.out.println("연결 끊음:" + server);
			
		}

		@Override
		public void sendPurchaseInfo(ErpOrderData oi) 
		{
		    System.out.println("연결 끊음:" +server);
		}
    	   
    	      	   
       }; //anonymous inner class 끝
	}
	
	

}
