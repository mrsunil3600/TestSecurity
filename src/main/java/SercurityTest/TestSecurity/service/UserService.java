package SercurityTest.TestSecurity.service;

import SercurityTest.TestSecurity.dao.LoginRequestDao;
import SercurityTest.TestSecurity.dao.UserRequestDao;
import SercurityTest.TestSecurity.dao.UserResponseDao;
import SercurityTest.TestSecurity.entity.UserEntity;
import SercurityTest.TestSecurity.jwtservice.AuthJwtUtil;
import SercurityTest.TestSecurity.userrepoimpl.UserRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepoImpl userRepo;
    @Autowired
    AuthJwtUtil authJwtUtil;
    public   UserResponseDao getResponse(String username){
        UserEntity user=userRepo.findByUsername(username);
        UserResponseDao userResponse=new UserResponseDao();
        if(user!=null){
           userResponse.setMsg("All Ok Bhai Yeh Chor mil Gya HAi ");
           userResponse.setUsername(user.getUsername());
           userResponse.setId(user.getId());

        }else{
            userResponse.setMsg("Bhai Galt Hogy Sab kuch User Bhaag Gya Yha se ");
        }
        return  userResponse;
    }

    public String newUser(UserRequestDao userRequest){
        userRepo.save(userRequest);
        return  "User Saved SuccesFully Or Bta Bhai kya kru Apke liye ";
    }
    public List<UserEntity> getAllUser(){
      return   userRepo.findAll();
    }

    public String login(LoginRequestDao loginRequestDao) {
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDao.getUsername(),loginRequestDao.getPass()));
        UserEntity user=(UserEntity) authentication.getPrincipal();
        String token=authJwtUtil.createToken(user);
        return  token;
     }
}
