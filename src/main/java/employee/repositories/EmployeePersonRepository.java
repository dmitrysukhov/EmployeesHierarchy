package employee.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import employee.models.EmployeePerson;

@RepositoryRestResource(collectionResourceRel = "employee", path = "employee")
public interface EmployeePersonRepository extends MongoRepository<EmployeePerson, String> {

	List<EmployeePerson> findByFirstNameLike(@Param("name") String name);
	
	List<EmployeePerson> findByFirstNameLikeAndLastNameLike(@Param("firstName") String firstName, @Param("lastName") String lastName);
	
	List<EmployeePerson> findByLastNameLike(@Param("name") String name);
	
	List<EmployeePerson> findByLastNameEndingWith(@Param("name") String name);

	List<EmployeePerson> findByEmailLike(@Param("email") String email);
}
