/*
 * package com.example.lms.service.mongo;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 * import org.springframework.stereotype.Service;
 * 
 * import com.example.lms.entity.User; import
 * com.example.lms.service.ServiceInterface; import
 * com.example.lms.service.UserInterface; import
 * com.fasterxml.jackson.core.JsonProcessingException;
 * 
 * import com.example.lms.util.UserCustody;
 * 
 * @Service("userMongoService") public class UserService implements
 * ServiceInterface<User>, UserInterface {
 * 
 * @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
 * 
 * @Override public User save(User object) { // TODO Auto-generated method stub
 * return null; }
 * 
 * @Override public User find(int id) { // TODO Auto-generated method stub
 * System.err.println("uau, id: " + id); return null; }
 * 
 * @Override public void delete(int id) { // TODO Auto-generated method stub
 * 
 * }
 * 
 * @Override public void log(String method, String interchange, Object object)
 * throws JsonProcessingException { // TODO Auto-generated method stub
 * 
 * }
 * 
 * @Override public UserDetails findByUsername(String username) { return new
 * UserCustody( new User("dejvis0678",
 * "$2a$10$6xcJOoEZzKCcw8sJz1hQe.EmBowSNkEpWEQqscmzW2AWa3PSOnz1O", true)); }
 * 
 * @Override public UserDetails loadUserByUsername(String username) throws
 * UsernameNotFoundException { // TODO Auto-generated method stub return new
 * UserCustody( new User("dejvis0678",
 * "$2a$10$6xcJOoEZzKCcw8sJz1hQe.EmBowSNkEpWEQqscmzW2AWa3PSOnz1O", true)); }
 * 
 * @Override public BCryptPasswordEncoder getbCryptPasswordEncoder() { return
 * bCryptPasswordEncoder; }
 * 
 * }
 */