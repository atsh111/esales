package com.styros.esales;

import com.styros.esales.service.DAOAuthenticationProviderImpl;
import com.styros.esales.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.criterion.Restrictions.and;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
    public SpringSecurityConfig(){
        super();
    }

/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        auth.parentAuthenticationManager(new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                if(authentication.getName().equals(authentication.getCredentials()))
                {
                    return new UsernamePasswordAuthenticationToken(authentication.getName(),
                            authentication.getCredentials(),authorities);
                }
                throw new BadCredentialsException("Bad Credentials");
            }
        });
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/assets/**","/assets/js/**","/assets/bootstrap/**","/assets/bootstrap/css/**          ")
                .permitAll()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/index")
                .permitAll()
                .antMatchers("/api/**")
                .permitAll()
                .antMatchers("/**")
                .authenticated()
                .and()
                .authenticationProvider(authenticationProvider())
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .httpBasic()
                ;





        /*http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/*")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/index")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/",true)
                .failureUrl("/login?error=true")
                .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/");*/
    }

    //@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myUserDetailsService());
        return authProvider;
    }
    @Bean
    public UserDetailsServiceImpl myUserDetailsService() {
        return new UserDetailsServiceImpl();
    }
}
