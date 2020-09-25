package br.newtonpaiva.av.revistas.repository;

import br.newtonpaiva.av.revistas.domain.Revista;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RevistaRepository extends MongoRepository<Revista, String> {
}
