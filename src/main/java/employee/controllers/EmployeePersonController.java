package employee.controllers;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mongodb.MongoClient;

import employee.models.EmployeePerson;
import employee.models.Position;

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

    @RequestMapping("/findByPosition")
    public ResponseEntity<List<EmployeePerson>> findByPosition(@RequestParam(value="title") String title) throws Exception {
    	MongoTemplate mongoTemplate = mongoTemplate();
    	
    	Query positionQuery = new Query();
    	Query empQuery = new Query();
    	
    	positionQuery.addCriteria(Criteria.where("title").is(title));
    	Position position = mongoTemplate.findOne(positionQuery, Position.class, "position");
    	if(position != null) empQuery.addCriteria(Criteria.where("position").is(position.getId()));
    	else return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<EmployeePerson>());

    	List<EmployeePerson> emps = mongoTemplate.find(empQuery, EmployeePerson.class);
    	
    	return ResponseEntity.status(HttpStatus.OK).body(emps);
    }
    
    @RequestMapping(value = "/fireEmployeeAndSubordinates", method = RequestMethod.DELETE)
    public ResponseEntity<String> fireEmployeeAndSubordinates(@RequestParam(value="employeeId") String employeeId) throws Exception {  	
    	MongoTemplate mongoTemplate = mongoTemplate();
    	
    	ObjectId objID = new ObjectId(employeeId);
    	Query empQuery = new Query();
    	
    	EmployeePerson directHead = mongoTemplate.findById(objID, EmployeePerson.class);
    	if(directHead != null) empQuery.addCriteria(Criteria.where("directHeadId").is(directHead.getId()));
    	else return ResponseEntity.status(HttpStatus.CONFLICT).body("There is no such employee in the database");

    	List<EmployeePerson> emps = mongoTemplate.find(empQuery, EmployeePerson.class);
    	emps.forEach(emp->mongoTemplate.remove(emp));
    	
    	mongoTemplate.remove(directHead);
    	
    	return ResponseEntity.status(HttpStatus.OK).body("Employee and all his subordinates successfully deleted");
    }
}
