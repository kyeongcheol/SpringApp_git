package net.madvirus.spring04.chap02.erp;

public interface ErpClient 
{
	public void connect();
	
	public void close();
	
	public void sendPurchaseInfo(ErpOrderData oi);

}
