package cl.globallogic.example.data;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.globallogic.example.data.entity.UserEntity;


public interface IUserDAO extends CrudRepository<UserEntity, UUID> {
	
	public int countByEmail(String email);
		

}
