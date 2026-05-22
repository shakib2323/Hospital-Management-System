package com.hms.config;

//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
public class SecurityConfig {

	
	
	
	
	
	//Initially developed the system with basic authentication, 
	//then migrated to JWT based stateless authentication using Spring Security.
	
	
	
	
	
	
	
	
	
//    private final UserDetailsService userDetailsService;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .authenticationProvider(authenticationProvider())
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers(
//                    "/api/v1/**",
//                    "/swagger-ui/**",
//                    "/swagger-ui.html",
//                    "/v3/api-docs/**",
//                    "/css/**",
//                    "/js/**",
//                    "/images/**"
//                ).permitAll()
//                .anyRequest().authenticated()
//            )
//            .formLogin(form -> form
//                .defaultSuccessUrl("/dashboard", true)
//                .permitAll()
//            )
//            .logout(logout -> logout
//                .logoutSuccessUrl("/login")
//                .permitAll()
//            )
//            .csrf(csrf -> csrf.disable());
//
//        return http.build();
//    }
}