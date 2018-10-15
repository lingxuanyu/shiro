

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
		//�����ļ�
		AbstractFactory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		//securityManager����
		SecurityManager instance = factory.getInstance();
		//���õ���ǰ��securityManager��ʵ��
		SecurityUtils.setSecurityManager(instance);
		
		//--------------------------------------------------------------------------
		//��������subject
		Subject subject = SecurityUtils.getSubject();
		//��װ����username  �� password
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("user1","12345");
		
		//--------------------------------------------------------------------------		
		//�����¼
		try {
			subject.login(usernamePasswordToken);
			
		}catch(UnknownAccountException e) {
			System.out.println("accont error");
		}
		System.out.println("user isLogin:   " + subject.isAuthenticated());
		//����ǳ�
		subject.logout();
		System.out.println("user isLogin:   " + subject.isAuthenticated());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testShiroByShiroMyRealM() {
		//�����ļ�
		AbstractFactory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		//securityManager����
		SecurityManager instance = factory.getInstance();
		//���õ���ǰ��securityManager��ʵ��
		SecurityUtils.setSecurityManager(instance);
		
		//--------------------------------------------------------------------------
		//��������subject
		Subject subject = SecurityUtils.getSubject();
		//��װ����username  �� password
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("user1","12345");
		
		//--------------------------------------------------------------------------		
		//�����¼
		try {
			subject.login(usernamePasswordToken);
		}catch(UnknownAccountException e) {
			System.out.println("accont error");
		}
		System.out.println("user isLogin:   " + subject.isAuthenticated());
		System.out.println("-------------------------------");
		System.out.println(subject.hasRole("role1"));
		System.out.println("--�Ƿ���֤---------------------------");
		System.out.println(subject.isPermitted("user:update"));
		
		System.out.println("----------------------------------------");
	
		//����ǳ�
		subject.logout();
		System.out.println("user isLogin:   " + subject.isAuthenticated());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testShiroByShiroMyRealMByall() {
		//�����ļ�
		AbstractFactory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-cryptography.ini");
		//securityManager����
		SecurityManager instance = factory.getInstance();
		//���õ���ǰ��securityManager��ʵ��
		SecurityUtils.setSecurityManager(instance);
		//--------------------------------------------------------------------------
		//��������subject
		Subject subject = SecurityUtils.getSubject();
		//��װ����username  �� password
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("user1","12345");
		//�����¼
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
		//�����ļ�
		AbstractFactory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
		//securityManager����
		SecurityManager instance = factory.getInstance();
		//���õ���ǰ��securityManager��ʵ��
		SecurityUtils.setSecurityManager(instance);
		
		//--------------------------------------------------------------------------
		//��������subject
		Subject subject = SecurityUtils.getSubject();
		//��װ����username  �� password
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("user1","12345");
		
		//--------------------------------------------------------------------------		
		//�����¼
		try {
			subject.login(usernamePasswordToken);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//��ɫ�ж�
		System.out.println("user isLogin:   " + subject.isAuthenticated());
		System.out.println(subject.hasRole("role1"));
		System.out.println(subject.hasAllRoles(Arrays.asList("role1","role2")));
		System.out.println(Arrays.toString(subject.hasRoles(Arrays.asList("role1","role2"))));
		
		
		//Ȩ���ж�
		System.out.println("user permission:  " + subject.isPermitted("user:select"));
		System.out.println("users all persmission:  " + subject.isPermittedAll("user:delete","user:updat"));
		System.out.println("user permission's list:" + Arrays.toString(subject.isPermitted("user:delete","user:create")));
	}
	
}
