package tw.idb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("tokenService")
public class TokenService {

    private static final String TOKEN_PREFIX = "token:";

    @Autowired
    @Qualifier("redisService")
    private RedisService redisService;

    public boolean checkToken(String token) {
        boolean is_token = redisService.exists(TOKEN_PREFIX + token);
        return is_token;
    }
}
