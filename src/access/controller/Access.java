package controller;

import bean.User;
import dao.UserOperation;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import servletcontext.LangResourceLoadListener;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by root on 5/4/17.
 */
@Controller
@RequestMapping("/access")
public class Access {
    private Logger log;
    private ObjectMapper mapper;

    public Access() {
        log = LoggerFactory.getLogger("error");
        mapper = new ObjectMapper();
    }

    @RequestMapping(path = {"/login", "/login/"}, method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) {
        WebApplicationContext app = (WebApplicationContext) session.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        request.setAttribute("action_login", "access/doLogin");
        request.setAttribute("href_forget_password", "");
        request.setAttribute("href_signup", "access/register");
        String from = request.getParameter("from");
        session.setAttribute("from", from);
        log.info("from: {}", from);
        return "access/login";
    }

    @RequestMapping(path = "/doLogin", method = RequestMethod.GET)
    public String doLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) throws IOException {
        WebApplicationContext app = (WebApplicationContext) session.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        String rt = null;

        String user = request.getParameter("user");
        String pass = request.getParameter("password");
        String auto = request.getParameter("auto_signin");

        Cookie[] cookies = request.getCookies();
        for (Cookie x : cookies) {
            if(x.getName().equals("user")) {
                return "0";
            }
        }

        UserOperation op = (UserOperation) app.getBean("user_operation");
        User u = op.findByUser(user);
        if (u == null) {
            rt = "-1";
        }
        if (u != null && u.getPassword().equals(pass)) {
            session.setAttribute("user", user);
            Cookie cookie = new Cookie("user", user);
            cookie.setPath((String)request.getAttribute("baseurl"));
            cookie.setMaxAge(2 * 60 * 60);
            response.addCookie(cookie);
        } else {
            request.setAttribute("input_user", user);
            request.setAttribute("input_password", "");
            request.setAttribute("input_auto_checked", auto != null ? "checked" : "");
            rt = "access/login";
        }
        return rt;
    }

    @RequestMapping(path = {"/register", "/register/"}, method = RequestMethod.GET)
    public String register(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) {
        ServletContext ctx = session.getServletContext();
        model.addAttribute("action_register_user", "access/doRegister");
        log.info("Language:{}", request.getHeader("Accept-Language"));

        log.info("session id: {}", session.getId());
        return "access/register";
    }

    @RequestMapping(path = "/doRegister", method = {RequestMethod.GET, RequestMethod.POST})
    public String doRegister(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) {
        log.info("user: {}", request.getParameter("user"));
        log.info("password: {}", request.getParameter("password"));
        log.info("gender: {}" , request.getParameter("gender"));
        log.info("phone: {}" , request.getParameter("phone"));
        log.info("mail: {}" , request.getParameter("mail"));
        log.info("address: {}" , request.getParameter("address"));

        WebApplicationContext app = (WebApplicationContext) session.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        User user = new User();
        user.setUser(request.getParameter("user"));
        user.setPassword(request.getParameter("password"));
        user.setGender(request.getParameter("gender").equals("male") ? true : false);
        user.setPhone(request.getParameter("phone"));
        user.setMail(request.getParameter("mail"));
        user.setAddress(request.getParameter("address"));
        user.setCreate_date(new Date());
        user.setBirthday(request.getParameter("birthday") == null ? new Date(0) : new Date());

        UserOperation op = (UserOperation) app.getBean("user_operation");
        op.save(user);

        return "redirect:/access/login";
    }

    @RequestMapping(path = "/doCheck", method = RequestMethod.GET)
    public void doCheck(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) throws IOException {
            WebApplicationContext app = (WebApplicationContext) session.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);

            String jsonRequest = URLDecoder.decode(request.getQueryString(), "utf8");
            String jsonResponse = verify(app, jsonRequest);

            PrintWriter writer = response.getWriter();
            writer.append(jsonResponse);

            writer.flush();
            writer.close();
    }

    private String verify(WebApplicationContext app, String jsonRequest) throws IOException {
        String jsonResponse = null;
        Map<String, String> request = mapper.readValue(jsonRequest, Map.class);
        Map<String, Object> response = new HashMap<>();
        String command = request.get("command");
        if (!command.equals("verify")) {
            return null;
        }
        String subject = request.get("subject");
        String object = request.get("object");
        switch (subject) {
            case "user":
                UserOperation op = (UserOperation) app.getBean("user_operation");
                User u = op.findByUser(object);
                if (u == null) {
                    response.put("result", "valid");
                } else {
                    response.put("result", "invalid");
                }
                break;
            default:
                jsonResponse = null;
        }

        jsonResponse = mapper.writeValueAsString(response);
        log.info(jsonRequest + "->" + jsonResponse);

        return jsonResponse;
    }
}
