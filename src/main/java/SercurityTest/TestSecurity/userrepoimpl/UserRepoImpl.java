package SercurityTest.TestSecurity.userrepoimpl;

import SercurityTest.TestSecurity.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepoImpl extends MongoRepository<UserEntity,Long> {
    UserEntity findByUsername(String username);
}
