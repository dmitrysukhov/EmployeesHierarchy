package employee.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class CustomRepositoryRestConfigurerAdapter extends RepositoryRestConfigurerAdapter {

   @Bean
   public Validator validator() {
       return new LocalValidatorFactoryBean();
   }

   @Override
   public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
	   String[] events = {"afterCreate", "beforeCreate", "afterSave", "beforeSave"};
	   for (String event: events) {   
		   validatingListener.addValidator(event, validator());
	   }
   }
}