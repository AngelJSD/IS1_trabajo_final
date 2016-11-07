package config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.lyncode.jtwig.mvc.JtwigViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"controller", "repository", "service"})
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver viewResolver() {
		JtwigViewResolver jtwigViewResolver = new JtwigViewResolver();
		jtwigViewResolver.setPrefix("/WEB-INF/views/");
		jtwigViewResolver.setSuffix(".twig.html");
		return jtwigViewResolver;
	}

}
