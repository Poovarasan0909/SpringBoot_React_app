package com.bezkoder.spring.jpa.postgresql.manager;

import com.bezkoder.spring.jpa.postgresql.model.User;
import com.bezkoder.spring.jpa.postgresql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserManager {

    @Autowired
    private UserRepository userRepository;

    public Map<String, Object> createUser(Map<String, Object> body) {
        Map<String,Object> result = new HashMap<>();
        String name = body.get("name").toString();
        String age = body.get("age").toString();
        String sex = body.get("sex").toString();
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setSex(sex);
        userRepository.save(user);
        result.put("name",name);
        result.put("age",age);
        result.put("sex",sex);
        return result;
    }
}
