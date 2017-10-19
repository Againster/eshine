package bean;
import java.util.Date;

/**
 * Created by root on 4/27/17.
 */
public class Login {
    private Long id;
    private String user;
    private String last_10_login_device;
    private String last_10_login_mode;
    private Date first_latest_fail_date;
    private int fail_amount;
    private String first_password_question;
    private String second_password_question;
    private String third_password_question;
    private String first_password_answer;
    private String second_password_answer;
    private String third_password_answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLast_10_login_device() {
        return last_10_login_device;
    }

    public void setLast_10_login_device(String last_10_login_device) {
        this.last_10_login_device = last_10_login_device;
    }

    public String getLast_10_login_mode() {
        return last_10_login_mode;
    }

    public void setLast_10_login_mode(String last_10_login_mode) {
        this.last_10_login_mode = last_10_login_mode;
    }

    public Date getFirst_latest_fail_date() {
        return first_latest_fail_date;
    }

    public void setFirst_latest_fail_date(Date first_latest_fail_date) {
        this.first_latest_fail_date = first_latest_fail_date;
    }

    public int getFail_amount() {
        return fail_amount;
    }

    public void setFail_amount(int fail_amount) {
        this.fail_amount = fail_amount;
    }

    public String getFirst_password_question() {
        return first_password_question;
    }

    public void setFirst_password_question(String first_password_question) {
        this.first_password_question = first_password_question;
    }

    public String getSecond_password_question() {
        return second_password_question;
    }

    public void setSecond_password_question(String second_password_question) {
        this.second_password_question = second_password_question;
    }

    public String getThird_password_question() {
        return third_password_question;
    }

    public void setThird_password_question(String third_password_question) {
        this.third_password_question = third_password_question;
    }

    public String getFirst_password_answer() {
        return first_password_answer;
    }

    public void setFirst_password_answer(String first_password_answer) {
        this.first_password_answer = first_password_answer;
    }

    public String getSecond_password_answer() {
        return second_password_answer;
    }

    public void setSecond_password_answer(String second_password_answer) {
        this.second_password_answer = second_password_answer;
    }

    public String getThird_password_answer() {
        return third_password_answer;
    }

    public void setThird_password_answer(String third_password_answer) {
        this.third_password_answer = third_password_answer;
    }
}
