package com.lingxuanyu.relam;

import java.util.ArrayList;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class PermissionRealm extends AuthorizingRealm{

	public String getName() {
		return "PermissionRealm";
	}
	
	//授权orization
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		
		ArrayList<String> roles = new ArrayList<String>();
		ArrayList<String> permissions = new ArrayList<String>();
		
		//从数据库查询相应的信息
		roles.add("role1");
		permissions.add("user:update");
		
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addRoles(roles);
		simpleAuthorizationInfo.addStringPermissions(permissions);
			
		return simpleAuthorizationInfo;
	}

	
	//认证entication
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
