package com.lingxuanyu.relam;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRelam extends AuthorizingRealm{

	public String getName() {
		return "MyRelam";
	}
	
	// ⁄»®orization
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	
	//»œ÷§entication
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username  = (String) token.getPrincipal();
		System.out.println(username);
		if(!"user1".equals(username)) {
			return null;
		}
		String password = "12345";
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,password,getName());
		return simpleAuthenticationInfo;
	}

}
