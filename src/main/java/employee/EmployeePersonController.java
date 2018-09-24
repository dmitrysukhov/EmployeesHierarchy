package employee;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration 
@EnableMongoAuditing
@RestController
public class EmployeePersonController extends AbstractMongoConfiguration {
    
	@Override
	public MongoClient mongoClient() {
		// TODO Auto-generated method stub
		return new MongoClient("localhost");
	}

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return "test";
	}

    @RequestMapping("/api/new")
    public EmployeePerson greeting(@RequestParam(value="name", defaultValue="World") String name) throws Exception {
    	EmployeePerson emp =new EmployeePerson("test", "test");
    	MongoTemplate mongoTemplate = mongoTemplate();
    	mongoTemplate.insert(emp, "employeePerson");
        return emp;
    }
}
