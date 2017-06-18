package net.madvirus.spring04.chap02.erp;

import java.util.Properties;

//추상 클래스
public abstract class ErpClientFactory 
{
	
   public static ErpClientFactory instance()
   {
	   Properties props = new Properties();
	   props.setProperty("server", "localhost");
	   
	   return instance(props);
   }
   
   //overloading
   //static 메서드를 이용해 객체 생성
   public static ErpClientFactory instance(Properties props)
   {
	   return new DefaultErpClientFactory(props);
   }
   
   //protected 생성자
   protected ErpClientFactory()
   {
	   
   }
   
   //추상 메서드
   public abstract ErpClient create();
   
   

}
