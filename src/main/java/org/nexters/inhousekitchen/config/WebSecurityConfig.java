package org.nexters.inhousekitchen.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration

/*springSecurityFilterChain을 포함하는 어노테이션*/
@EnableWebSecurity


/*security config 관련 클래스*/
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    
    @Override
    /*spring security 제외 경로설정 */
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.GET)
        				 .antMatchers("/resources/**");
        web.ignoring().antMatchers("/search/main")
        				 .antMatchers("/signup")
        				 .antMatchers("/signin")
        				 .antMatchers("classpath:/META-INF/resources/**");

        
    }
    

	@Override
    /*인증과 접근권한 설정*/
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/signin")
                .permitAll()
                .and()
            .logout()
                .permitAll();
        
  
   
    }

    @Autowired
    /*"user", "password"를 아이디, 비번으로 갖는 사용자는 USER라는 롤 부여받음. 정보는 메모리에 저장된다?*/
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("kde6260").password("momo2701").roles("USER");
    }
}