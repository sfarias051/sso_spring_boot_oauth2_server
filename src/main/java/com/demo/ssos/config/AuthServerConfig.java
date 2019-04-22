package com.demo.ssos.config;

import com.demo.ssos.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
 
    @Override
    public void configure(
      AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
          .checkTokenAccess("isAuthenticated()");
    }
 
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
          .withClient(Constants.AUTHORIZATION_CLIENT_ID)
          .secret(passwordEncoder.encode(Constants.AUTHORIZATION_SECRET))
          .authorizedGrantTypes(
                  Constants.GRANT_AUTHORIZATION_CODE,
                  Constants.GRANT_REFRESH_TOKEN,
                  Constants.GRANT_CLIENT_CREDENTIALS
          )
          .scopes("user_info")
          .autoApprove(true)
          .redirectUris(
                  Constants.REDIRECT_CLIENT_1,
                  Constants.REDIRECT_CLIENT_2
          );
    }
}
