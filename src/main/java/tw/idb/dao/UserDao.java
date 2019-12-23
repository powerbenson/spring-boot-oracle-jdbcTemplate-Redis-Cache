package tw.idb.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import tw.idb.mapper.UserRowMapper;
import tw.idb.vo.User;

@Repository
public class UserDao {

    @Autowired
    @Qualifier("namedParamJdbc")
    private NamedParameterJdbcTemplate namedParamJdbc;

    public User getUser(String userid) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userid", userid);

        StringBuilder sql = new StringBuilder();
        sql.append("select id, username, password_hash as password from sa_user where id = :userid");

        return namedParamJdbc.queryForObject(sql.toString(), params, new UserRowMapper());
    }
}
