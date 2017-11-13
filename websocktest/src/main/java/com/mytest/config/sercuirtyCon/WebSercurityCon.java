package com.mytest.config.sercuirtyCon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Administrator on 2017/11/12 0012.
 */
@Configuration
public class WebSercurityCon  extends WebSecurityConfigurerAdapter{

    @Autowired
    UserDetailSerImp userDetailSerImp;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*super.configure(auth);
        auth.inMemoryAuthentication()
                .withUser("root")
                .password("root")
                .roles("USER");*/

        auth.userDetailsService(userDetailSerImp);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        http.authorizeRequests().
                antMatchers("/", "/webjars/**", "/register", "/api/common/**", "/image/**","/index").permitAll()
                .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login").defaultSuccessUrl("/index", true).failureForwardUrl("/ee").permitAll()
                .and().
                    logout().permitAll().and().csrf().disable();
    }
}
