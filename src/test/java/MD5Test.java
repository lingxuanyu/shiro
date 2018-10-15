import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class MD5Test {
	
	@Test	
	public void testMd5() {
		Md5Hash md5Hash = new Md5Hash("12345","zhangsan",3);
		System.out.println(md5Hash);
	}
			
	
}
