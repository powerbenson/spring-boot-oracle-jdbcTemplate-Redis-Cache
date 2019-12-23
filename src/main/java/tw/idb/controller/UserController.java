package tw.idb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.idb.exception.SystemErrorException;
import tw.idb.exception.UnAuthorizedException;
import tw.idb.response.Response;
import tw.idb.service.TokenService;
import tw.idb.service.UserService;
import tw.idb.vo.User;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("tokenService")
    private TokenService tokenService;

    @RequestMapping(value = "/getUser/{userid}" , method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Response> getUser(
            @RequestHeader(value = "token", required = false) String token,
            @PathVariable(value = "userid", required = false) String userid) {
        Response response = new Response();

        if(!tokenService.checkToken(token)) throw new UnAuthorizedException();

        try{
            if(userService.isUserCacheExist(userid)) {
                response.getElements().put("user", userService.getUserCache(userid));
            }else {
                User user = userService.getUser(userid);
                userService.setUserCache(user);

                response.getElements().put("user", user);
            }
        }catch (Exception e ){
            throw new SystemErrorException();
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
