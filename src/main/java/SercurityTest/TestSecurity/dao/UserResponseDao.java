package SercurityTest.TestSecurity.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserResponseDao {
    Long id;
    String username;
    String msg;

}
