package employee.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import employee.models.EmployeePerson;

@RepositoryRestResource(collectionResourceRel = "employee", path = "employee")
public interface EmployeePersonRepository extends MongoRepository<EmployeePerson, String> {
	
	// find by first name
	List<EmployeePerson> findByFirstNameLike(@Param("name") String name);
	
	List<EmployeePerson> findByFirstNameLikeAndLastNameLike(@Param("firstName") String firstName, @Param("lastName") String lastName);
	
	// find by last name
	List<EmployeePerson> findByLastNameLike(@Param("name") String name);
	List<EmployeePerson> findByLastNameEndingWith(@Param("name") String name);
	
	// find by email
	List<EmployeePerson> findByEmailLike(@Param("email") String email);
}