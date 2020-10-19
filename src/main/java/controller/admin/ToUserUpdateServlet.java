package controller.admin;

import dao.UserDao;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "ToUserUpdateServlet",urlPatterns = "/userUpdateServlet")
public class ToUserUpdateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userid = req.getParameter("userid");
        String password = req.getParameter("password");
        String username = req.getParameter("username");
        //将参数封装到user
        User user=new User();
        user.setId(Integer.parseInt(userid));
        user.setUserName(username);
        user.setUserPassword(password);

        UserDao userDao=new UserDao();
        int i = userDao.updateUserInfo(user);
        if (i>0){
            req.getRequestDispatcher("/resources/admin/default.jsp").forward(req,
                    resp);
        }else {
            req.getRequestDispatcher("error.jsp").forward(req,resp);
        }
    }
}
