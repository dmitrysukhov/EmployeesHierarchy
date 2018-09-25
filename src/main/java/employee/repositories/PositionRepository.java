package employee.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import employee.models.Position;

@RepositoryRestResource(collectionResourceRel = "position", path = "position")
public interface PositionRepository extends MongoRepository<Position, String> {
}
