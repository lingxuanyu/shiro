

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.AbstractFactory;
import org.junit.Test;

public class ShiroTest {
	@SuppressWarnings("deprecation")
	@Test
	public void testShiro() {
		//加载文件
		AbstractFactory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
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
		//主体登录
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
	
	@SuppressWarnings("deprecation")
	@Test
	public void testShiroByShiroMyRealM() {
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
		//主体登录
		try {
			subject.login(usernamePasswordToken);
		}catch(UnknownAccountException e) {
			System.out.println("accont error");
		}
		System.out.println("user isLogin:   " + subject.isAuthenticated());
		System.out.println("-------------------------------");
		System.out.println(subject.hasRole("role1"));
		System.out.println("--是否认证---------------------------");
		System.out.println(subject.isPermitted("user:update"));
		
		System.out.println("----------------------------------------");
	
		//主体登出
		subject.logout();
		System.out.println("user isLogin:   " + subject.isAuthenticated());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testShiroByShiroMyRealMByall() {
		//加载文件
		AbstractFactory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-cryptography.ini");
		//securityManager工厂
		SecurityManager instance = factory.getInstance();
		//设置到当前的securityManager的实例
		SecurityUtils.setSecurityManager(instance);
		//--------------------------------------------------------------------------
		//加载主体subject
		Subject subject = SecurityUtils.getSubject();
		//封装对题username  和 password
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("user1","12345");
		//主体登录
		try {
			subject.login(usernamePasswordToken);
		}catch(UnknownAccountException e) {
			System.out.println("accont error");
		}
		
		System.out.println("user isLogin:   " + subject.isAuthenticated());
		
		
		System.out.println(subject.hasRole("role1"));
		System.out.println(subject.hasRole("role2"));
		
		
		
		
		
		
		
	
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testShiroByShiroMyRealMByal1() {
		//加载文件
		AbstractFactory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
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
		//主体登录
		try {
			subject.login(usernamePasswordToken);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//角色判断
		System.out.println("user isLogin:   " + subject.isAuthenticated());
		System.out.println(subject.hasRole("role1"));
		System.out.println(subject.hasAllRoles(Arrays.asList("role1","role2")));
		System.out.println(Arrays.toString(subject.hasRoles(Arrays.asList("role1","role2"))));
		
		
		//权限判断
		System.out.println("user permission:  " + subject.isPermitted("user:select"));
		System.out.println("users all persmission:  " + subject.isPermittedAll("user:delete","user:updat"));
		System.out.println("user permission's list:" + Arrays.toString(subject.isPermitted("user:delete","user:create")));
	}
	
}
