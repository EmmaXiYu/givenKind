package org.givenkind.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

@Configuration
@EnableJpaRepositories(
		entityManagerFactoryRef="entityManagerFactory",
		transactionManagerRef="transactionManager",
		basePackages={"org.givenkind.repository"}
		)
@EnableTransactionManagement
public class DataSourceConfig {

	@Resource
	Environment env;
	
	private static final String URL = "db.url";
	private static final String USER = "db.user";
	private static final String PASSWORD = "db.password";
	
	@Bean(name="dataSource")
	public DataSource dataSource() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setURL(env.getRequiredProperty(URL));
		dataSource.setUser(env.getRequiredProperty(USER));
		dataSource.setPassword(env.getRequiredProperty(PASSWORD));
		return dataSource;
	}
	
	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory =
				new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPersistenceProviderClass(HibernatePersistence.class);
		entityManagerFactory.setPackagesToScan("org.givenkind.model");
		entityManagerFactory.setJpaProperties(getHibernateProperties());
		entityManagerFactory.afterPropertiesSet();
		return entityManagerFactory;
	}
	
	@Bean(name="transactionManager")
	@Primary
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
	
	private Properties getHibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.put("hibernate.show_sql", "true");
		return hibernateProperties;
	}
	
}
