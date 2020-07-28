package com.moshka;

import com.moshka.model.Car;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;

@EnableJpaRepositories(entityManagerFactoryRef = "sessionFactory")
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
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
	private EntityManager em;
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

	@Bean("dateFormat")
	public SimpleDateFormat getTimeFormat(){
		final String TIME_FORMAT = "HH:mm:ss";
		return new SimpleDateFormat(TIME_FORMAT);
	}

	@Bean("timeFormat")
	public SimpleDateFormat getDateFormat(){
		final String DATE_FORMAT = "yyyy/MM/dd";
		return new SimpleDateFormat(DATE_FORMAT);
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		for(int i=0;i<10;i++){
			Car myCar = new Car();
			myCar.setModel(" Model " +i);
			myCar.setName(" Name " +i);
			em.persist(myCar);
			System.out.println(" Car saved " +myCar.getName());

		}
		Car myCar2 = new Car();
		myCar2.setModel("Model");
		myCar2.setName("Name");
		em.persist(myCar2);
		LOGGER.info(" Application is running ..... Now " +
				"\n ***** It should be consider we can add more attributes to our Models depending on our requirements ,Its Just Dummy " +
				" for qualification purposes  **** ");
	}
}
