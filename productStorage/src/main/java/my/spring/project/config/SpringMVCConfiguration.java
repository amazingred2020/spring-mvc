package my.spring.project.config;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import my.spring.project.dao.ProductDao;
import my.spring.project.dao.ProductDaoImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "my.spring.project")
public class SpringMVCConfiguration implements WebMvcConfigurer{

	@Bean
	public DataSource getDataSource() {
		EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
		return dbBuilder.setType(EmbeddedDatabaseType.HSQL)
				.addScripts("classpath:schema.sql", "classpath:data.sql")
                .build();
	}

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean
	public ProductDao getProductDao() {
		return new ProductDaoImpl(getDataSource());
	}

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
	
	@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(getJacksonHttpMessageConverter());
    }
 
    @Bean
    public MappingJackson2HttpMessageConverter getJacksonHttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        return converter;
    }
}
