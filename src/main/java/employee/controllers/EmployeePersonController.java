package employee.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mongodb.MongoClient;

import employee.models.EmployeePerson;

@Configuration 
@EnableMongoAuditing
@RestController
public class EmployeePersonController extends AbstractMongoConfiguration {
    
	
	@Override
	public MongoClient mongoClient() {
		return new MongoClient("localhost");
	}

	@Override
	protected String getDatabaseName() {
		return "test";
	}

    @RequestMapping("/api/ping")
    public EmployeePerson ping(@RequestParam(value="name", defaultValue="Ping") String name) throws Exception {
    	EmployeePerson emp =new EmployeePerson("ping", "ping");
    	MongoTemplate mongoTemplate = mongoTemplate();
    	mongoTemplate.insert(emp, "employeePerson");
        return emp;
    }
}
