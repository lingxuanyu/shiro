

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.AbstractFactory;

public class Shiro {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		//加载文件
		AbstractFactory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		//securityManager工厂
		SecurityManager instance = factory.getInstance();
		//设置到当前的securityManager的实例
		SecurityUtils.setSecurityManager(instance);
		
		//--------------------------------------------------------------------------
		//加载主体subject
		Subject subject = SecurityUtils.getSubject();
		//封装对题username  和 password
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("user1","12345");
		
		//--------------------------------------------------------------------------		
		//主体登录->securityManager->委托Authentication认证(对比realm和输入的账号密码是否一致进行判断）
		try {
			subject.login(usernamePasswordToken);
			
		}catch(UnknownAccountException e) {
			System.out.println("accont error");
		}
		System.out.println("user isLogin:   " + subject.isAuthenticated());
		//主体登出
		subject.logout();
		System.out.println("user isLogin:   " + subject.isAuthenticated());
	}
	
}
