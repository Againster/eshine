package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by root on 6/13/17.
 */

@Controller
@RequestMapping("/chat")
public class Chat {
    @RequestMapping(path = {"/", ""}, method = {RequestMethod.GET, RequestMethod.POST})
    public String explore(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) throws Exception {
        return "chat/chat";
    }
}
