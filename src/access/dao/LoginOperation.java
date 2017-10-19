package dao;

import bean.Login;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;

import so.so;

/**
 * Created by root on 5/2/17.
 */
public class LoginOperation extends JdbcTemplate implements LoginInterface {
    public LoginOperation() {
    }

    @Override
    public Login findById(Long id) {
        String sql = "SELECT * FROM md_login WHERE id = ?;";
        Login login = this.queryForObject(sql, new LoginRowMapper(), id);
        return login;
    }

    @Override
    public Login findByUser(String user) {
        String sql = "SELECT * FROM md_login WHERE user = ?;";
        Login login = this.queryForObject(sql, new LoginRowMapper(), user);
        return login;
    }

    @Override
    public void save(Login login) {
        Login l[] = new Login[1];
        l[0] = login;
        this.save(l);
    }

    @Override
    public void save(Login[] login) {
        ArrayList<Login> x = new ArrayList<Login>(Arrays.asList(login));
        String sql = "INSERT INTO md_login VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        this.batchUpdate(sql, new LoginBatchPreparedStatementSetter(x));
    }
}

class LoginRowMapper implements RowMapper<Login> {
    @Override
    public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
        Login login = new Login();
        login.setId(rs.getLong("id"));
        login.setUser(rs.getString("user"));
        login.setLast_10_login_device(rs.getString("last_10_login_device"));
        login.setLast_10_login_mode(rs.getString("last_10_login_mode"));
        login.setFirst_latest_fail_date(rs.getDate("first_latest_fail_date"));
        login.setFail_amount(rs.getInt("fail_amount"));
        login.setFirst_password_question(rs.getString("1st_password_question"));
        login.setSecond_password_question(rs.getString("2nd_password_question"));
        login.setThird_password_question(rs.getString("3rd_password_question"));
        login.setFirst_password_answer(rs.getString("1st_password_answer"));
        login.setSecond_password_answer(rs.getString("2nd_password_answer"));
        login.setThird_password_answer(rs.getString("3rd_password_answer"));
        return login;
    }
}

class LoginBatchPreparedStatementSetter implements BatchPreparedStatementSetter {
    ArrayList<Login> list;

    public LoginBatchPreparedStatementSetter(ArrayList<Login> list) {
        this.list = list;
    }

    @Override
    public void setValues(PreparedStatement ps, int i) throws SQLException {
        Login login = list.get(i);
        so.sqlNullOrValue(ps, 1, Types.INTEGER, login.getId());
        so.sqlNullOrValue(ps, 2, Types.VARCHAR, login.getUser());
        so.sqlNullOrValue(ps, 3, Types.VARCHAR, login.getLast_10_login_device());
        so.sqlNullOrValue(ps, 4, Types.VARCHAR, login.getLast_10_login_mode());
        so.sqlNullOrValue(ps, 5, Types.VARCHAR, login.getFirst_latest_fail_date().toString());
        so.sqlNullOrValue(ps, 6, Types.INTEGER, login.getFail_amount());
        so.sqlNullOrValue(ps, 7, Types.VARCHAR, login.getFirst_password_question());
        so.sqlNullOrValue(ps, 8, Types.VARCHAR, login.getSecond_password_question());
        so.sqlNullOrValue(ps, 9, Types.VARCHAR, login.getThird_password_question());
        so.sqlNullOrValue(ps, 10, Types.VARCHAR, login.getFirst_password_answer());
        so.sqlNullOrValue(ps, 11, Types.VARCHAR, login.getSecond_password_answer());
        so.sqlNullOrValue(ps, 12, Types.VARCHAR, login.getThird_password_answer());
        /*
        ps.setLong(1, login.getId());
        ps.setString(2, login.getUser());
        ps.setString(3, login.getLast_10_login_device());
        ps.setString(4, login.getLast_10_login_mode());
        ps.setInt(5, login.getLimited_fail_amount());
        ps.setInt(6, login.getLimited_fail_expire());
        ps.setString(7, login.getFirst_latest_fail_date().toString());
        ps.setInt(8, login.getFail_amount());
        ps.setString(9, login.getFirst_password_question());
        ps.setString(10, login.getSecond_password_question());
        ps.setString(11, login.getThird_password_question());
        ps.setString(12, login.getFirst_password_answer());
        ps.setString(13, login.getSecond_password_answer());
        ps.setString(14, login.getThird_password_answer());
        */
    }

    @Override
    public int getBatchSize() {
        return list.size();
    }
}