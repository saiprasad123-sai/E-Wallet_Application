package com.projects.majorProjects;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    RedisTemplate<String,User> redisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;
    public void addUser(UserRequestDto userRequestDto) {
       User us =   User.builder()
               .age(userRequestDto.getAge())
               .username(userRequestDto.getUserName())
               .phNo(userRequestDto.getPhNo())
               .build();
       userRepository.save(us);
       saveInCache(us);
    }

    private void saveInCache(User us) {

        Map map = objectMapper.convertValue(us, Map.class);
        redisTemplate.opsForHash().putAll(us.getUsername(),map);
        redisTemplate.expire(us.getUsername(), Duration.ofHours(12));

    }

    public User getUserByUserName(String userName) {

        Map map = redisTemplate.opsForHash().entries(userName);
        User us = null;

        if(map==null){
            us = userRepository.getUserByUserName(userName);
            saveInCache(us);
            return us;
        }
        else {
            us = objectMapper.convertValue(map,User.class);
            return us;
        }
    }
}
