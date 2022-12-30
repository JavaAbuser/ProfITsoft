package servlets;

import models.User;
import storage.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        List<User> allUsers = Users.getUsers();

        User potentialUser = null;

        for(User user : allUsers){
            if(user.getLogin().equals(login) && user.getPassword().equals(password)){
                potentialUser = user;

                HttpSession session = req.getSession();
                session.setAttribute("user", user.getLogin());
                req.setAttribute("userName", user.getName());
            }
        }
        if(potentialUser == null) {
            req.setAttribute("unknown", true);
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/menu");
        }
    }
}
