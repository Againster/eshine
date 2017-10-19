package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.ui.ModelMap;
/**
 * Created by root on 5/4/17.
 */
@Controller
public class Root {

    public void root() {}

    @RequestMapping(path = {"/", ""}, method = {RequestMethod.GET, RequestMethod.POST})
    public String webRoot (HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) {
        return "root/root";
    }
}
