package com.moshka;


import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class BicycleChallengeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BicycleChallengeApplication.class, args);
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(BicycleChallengeApplication.class.getName());
	@Autowired
	private Environment environment;


	@Bean("dataSource")
	public DataSource getDataSource(){
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl(environment.getProperty("spring.datasource.url"));
		dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUsername(environment.getProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource.password"));
		LOGGER.info("## getDatasource : "+dataSource);
		return dataSource;
	}


	@Autowired
	@Bean("sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) throws IOException {

		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect",environment.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.setProperty("hibernate.show_sql",environment.getProperty("spring.jpa.show-sql"));
		properties.setProperty("current_session_context_class",environment.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
		properties.setProperty("hibernate.hbm2ddl.auto",environment.getProperty("spring.jpa.hibernate.ddl-auto"));

		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setPackagesToScan("");
		factoryBean.setDataSource(dataSource);
		factoryBean.setHibernateProperties(properties);
		factoryBean.afterPropertiesSet();
		return  factoryBean.getObject();

	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(sessionFactory);
		hibernateTransactionManager.setNestedTransactionAllowed(true);
		return hibernateTransactionManager;
	}


	@Override
	public void run(String... args) throws Exception {
		LOGGER.info(" Application is running ..... Now");
	}
}
