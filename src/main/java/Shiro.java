

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
		//�����¼->securityManager->ί��Authentication��֤(�Ա�realm��������˺������Ƿ�һ�½����жϣ�
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
	
}
