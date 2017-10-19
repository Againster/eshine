package dao;

import bean.User;

/**
 * Created by root on 5/10/17.
 */
public interface UserInterface {
    public User findById(Long id);
    public User findByUser(String user);
    public void save(User user);
    public void save(User login[]);
}
