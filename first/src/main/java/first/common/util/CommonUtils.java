package first.common.util;

import java.util.UUID;

public class CommonUtils 
{
	public static String getRandomString()
	{
		// -(대시) 부분을 공백으로..
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
