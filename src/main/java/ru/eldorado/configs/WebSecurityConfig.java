package ru.eldorado.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * Created by ozol on 03.10.2016.
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**"); // фильтры SS игнорят все урлы '/resources/**'
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // настройка запросов подлежащих авторизации
        http.authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // данные урлы доступны только пользователю с ролью ADMIN
                .antMatchers("/home/**").permitAll() // список урлов доступных без авторизации, но механизм Spring Secr - работает
                // .antMatchers("/resources/**").anonymous() // все ресурсы доступны без авторизации, фильтры Spring Secr - отключены см webSecurity
                .anyRequest().authenticated(); // все остальные урлы достпны только после аутентификации

        // настройка аутентификации (формы логирования)
        http.formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .failureUrl("/login?error")
                .permitAll();

        // настройка логаута
        http.logout()
                .permitAll() // разрешаем делать логаут всем
                .logoutUrl("/logout") // указываем URL логаута
                .logoutSuccessUrl("/login?logout")  // указываем URL при удачном логауте
                .invalidateHttpSession(true); // делаем не валидной текущую сессию

        // настройка защиты от csrf атак
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));

        // пользовательская авторизация
        http.regexMatcher("^/rest.*")
                .addFilterAfter(getTokenAuthFilter(),
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // создаем пользователей в heap'е JVM
        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("superadmin").password("superadmin").roles("SUPERADMIN");
    }

}
