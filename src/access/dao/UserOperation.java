package dao;

import bean.Login;
import bean.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import so.so;

/**
 * Created by root on 5/10/17.
 */
public class UserOperation extends JdbcTemplate implements UserInterface {
    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User findByUser(String user) {
        String sql = "SELECT * FROM md_user WHERE user = ?;";
        User u = null;
        try {
            u = this.queryForObject(sql, new UserRowMapper(), user);
        } catch (EmptyResultDataAccessException e) {
            u = null;
        }
        return u;
    }

    @Override
    public void save(User user) {
        User u[] = new User[1];
        u[0] = user;
        this.save(u);
    }

    @Override
    public void save(User[] user) {
        ArrayList<User> x = new ArrayList<User>(Arrays.asList(user));
        String sql = "INSERT INTO md_user VALUES(?,?,?,?,?,?,?,?,?,?);";
        this.batchUpdate(sql, new UserBatchPreparedStatementSetter(x));
    }
}

class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setUser(resultSet.getString("user"));
        user.setPassword(resultSet.getString("password"));
        user.setGender(resultSet.getBoolean("gender"));
        user.setBirthday(resultSet.getDate("birthday"));
        user.setPhone(resultSet.getString("phone"));
        user.setMail(resultSet.getString("mail"));
        user.setAddress(resultSet.getString("address"));
        user.setCreate_date(resultSet.getDate("create_date"));
        user.setAuthority(resultSet.getString("authority"));
        return user;
    }
}


class UserBatchPreparedStatementSetter implements BatchPreparedStatementSetter {
    private ArrayList<User> list;

    UserBatchPreparedStatementSetter(ArrayList<User> list) {
        this.list = list;
    }

    @Override
    public void setValues(PreparedStatement ps, int i) throws SQLException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        User user = list.get(i);
        so.sqlNullOrValue(ps, 1, Types.BIGINT, user.getId());
        so.sqlNullOrValue(ps, 2, Types.VARCHAR, user.getUser());
        so.sqlNullOrValue(ps, 3, Types.VARCHAR, user.getPassword());
        so.sqlNullOrValue(ps, 4, Types.INTEGER, user.getGender() == true ? 1 : 0);
        so.sqlNullOrValue(ps, 5, Types.DATE, format.format(user.getBirthday()));
        so.sqlNullOrValue(ps, 6, Types.VARCHAR, user.getPhone());
        so.sqlNullOrValue(ps, 7, Types.VARCHAR, user.getMail());
        so.sqlNullOrValue(ps, 8, Types.VARCHAR, user.getAddress());
        so.sqlNullOrValue(ps, 9, Types.DATE, format.format(user.getCreate_date()));
        so.sqlNullOrValue(ps, 10, Types.VARCHAR, user.getAuthority());

    }

    @Override
    public int getBatchSize() {
        return this.list.size();
    }
}