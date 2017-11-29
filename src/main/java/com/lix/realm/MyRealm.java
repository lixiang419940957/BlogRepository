package com.lix.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.lix.pojo.Blogger;
import com.lix.service.BloggerService;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private BloggerService bloggerService;

    /**
     * 验证当前登录的用户
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        Blogger blogger = bloggerService.queryBloggerByUserName(userName);
        if (blogger != null) {
            SecurityUtils.getSubject().getSession().setAttribute("currentUser", blogger); // 当前用户信息存到session中
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(blogger.getUserName(),
                    blogger.getPassword(), this.getName());
            return authcInfo;
        } else {
            return null;
        }
    }

    /**
     * 为当限前登录的用户授予角色和权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 设置realm的名称
     */
    @Override
    public void setName(String name) {
        super.setName("myRealm");
    }

}
