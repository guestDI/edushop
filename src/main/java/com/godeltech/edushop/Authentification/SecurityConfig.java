//package com.godeltech.edushop.Authentification;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
///**
// * Created by Dmitry on 04.10.2017.
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
////    protected void configure(AuthenticationManagerBuilder auth)
////            throws Exception {
////        auth.inMemoryAuthentication().withUser("administrator").password("administrator")
////                .roles("admin");
////    }
////
////    protected void configure(HttpSecurity http) throws Exception {
////        http.httpBasic().and().authorizeRequests().antMatchers("/getAll")
////                .hasRole("admin").and()
////                .csrf().disable().headers().frameOptions().disable();
////    }
////
////    @Autowired
////    @Qualifier("customUserDetailsService")
////    UserDetailsService userDetailsService;
////
////    @Autowired
////    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userDetailsService);
////    }
//}
