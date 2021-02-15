package com.josehernandez.meli.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final List<String> CUSTOM_PERMIT_METHODS_CORS = Collections.unmodifiableList(
            Arrays.asList(HttpMethod.GET.name(), HttpMethod.HEAD.name(), HttpMethod.POST.name(),
                    HttpMethod.DELETE.name(), HttpMethod.PATCH.name(), HttpMethod.PUT.name(),
                    HttpMethod.OPTIONS.name()));

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().configurationSource(request -> getCorsConfig()).and()
                .csrf().disable();
    }

    private CorsConfiguration getCorsConfig() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedMethods(CUSTOM_PERMIT_METHODS_CORS);
        config.applyPermitDefaultValues();
        return config;
    }
}