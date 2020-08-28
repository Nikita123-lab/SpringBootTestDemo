package com.lnt.sb.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	
	// added
	@Value("${spring.datasource.driverClassName}")
	private String DRIVER;

	@Value("${spring.datasource.password}")
	private String PASSWORD;

	@Value("${spring.datasource.url}")
	private String URL;

	@Value("${spring.datasource.username}")
	private String USERNAME;

	@Value("${spring.jpa.hibernate.dialect}")
	private String DIALECT;

	@Value("${spring.jpa.show-sql}")
	private String SHOW_SQL;

	/*
	 * @Value("${spring.jpa.hibernate.ddl-auto}") private String HBM2DDL_AUTO;
	 */

	@Value("${entitymanager.packagesToScan}")
	private String PACKAGES_TO_SCAN;

	@Value("${ENABLE_AAD_AUTHENTICATION}")
	private String aadAuthenticationStatus;

	@Value("${ENABLE_CSRF}")
	private String csrfStatus;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		//auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		CookieCsrfTokenRepository cctr = null;
		if (csrfStatus != null && csrfStatus.equalsIgnoreCase("TRUE")) {
			cctr = new CookieCsrfTokenRepository();
			cctr.setCookieHttpOnly(false);
			cctr.setCookiePath("/");
		}

		if (aadAuthenticationStatus != null && aadAuthenticationStatus.equalsIgnoreCase("TRUE")) {
			// We don't need CSRF for this example
			httpSecurity.csrf().disable().cors().
			// dont authenticate this particular request
					and().authorizeRequests().antMatchers("/isPasswordSet", "/updatePassword", "/authenticate",
							"/sendEmailToUser", "/otpValidation")
					.permitAll().
					// all other requests need to be authenticated
					anyRequest().authenticated().and().
					// make sure we use stateless session; session won't be used to
					// store user's state.
					exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

			// Add a filter to validate the tokens with every request
			// httpSecurity.addFilterBefore(jwtRequestFilter,
			// UsernamePasswordAuthenticationFilter.class);
		} else {
			httpSecurity.cors().and().authorizeRequests().anyRequest().permitAll();
			if (csrfStatus != null && csrfStatus.equalsIgnoreCase("TRUE")) {
				httpSecurity.csrf().ignoringAntMatchers("/api/cookie/**").csrfTokenRepository(cctr);
			} else {
				httpSecurity.csrf().disable();
			}
		}
	}

	// Added for hibernate

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", DIALECT);
		hibernateProperties.put("hibernate.show_sql", SHOW_SQL);
		// hibernateProperties.put("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);
		sessionFactory.setHibernateProperties(hibernateProperties);

		return sessionFactory;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	/*
	 * @Bean public LocalContainerEntityManagerFactoryBean entityManagerFactory(
	 * DataSource dataSource) { LocalContainerEntityManagerFactoryBean emf = new
	 * LocalContainerEntityManagerFactoryBean(); emf.setDataSource(dataSource);
	 * emf.setPackagesToScan(PACKAGES_TO_SCAN); emf.setJpaVendorAdapter(new
	 * HibernateJpaVendorAdapter());
	 * //emf.setJpaPropertyMap(irdbConfig.getAdditionalHibernateProperties());
	 * return emf; }
	 */

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}