package tw.idb.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tw.idb.dao.UserDao;
import tw.idb.vo.User;

import java.util.Map;

@Service("userService")
public class UserService {

    public static String USER_PREFIX = "user:";
    public static int USER_TTL = 300;

    @Autowired
    @Qualifier("redisService")
    private RedisService redisService;

    @Autowired
    private UserDao userDao;

    public User getUser(String userid) {
        return userDao.getUser(userid);
    }

    public User getUserCache(String userid) {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.convertValue(redisService.hgetAll(USER_PREFIX + userid), User.class);
        return user;
    }

    public void setUserCache(User user) {
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, String> userMap = oMapper.convertValue(user, Map.class);

        redisService.putAll(USER_PREFIX + user.getId(), userMap, USER_TTL);
    }

    public boolean isUserCacheExist(String userid) {
        return redisService.exists(USER_PREFIX + userid);
    }
}
