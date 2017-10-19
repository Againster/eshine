package dao;

import bean.Login;

/**
 * Created by root on 5/2/17.
 */
public interface LoginInterface {
    public Login findById(Long id);
    public Login findByUser(String user);
    public void save(Login login);
    public void save(Login login[]);
}
