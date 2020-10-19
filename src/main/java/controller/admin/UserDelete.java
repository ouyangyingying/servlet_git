package controller.admin;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "UserDelete",urlPatterns = "/userDeleteServlet")
public class UserDelete extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("userid");
        UserDao userDao=new UserDao();
        int i = userDao.deleteUserById(id);
        if(i>0){
            //重定向
            resp.sendRedirect("/userSelectServlet");
        }else {
            req.getRequestDispatcher("error.jsp").forward(req,resp);
        }

    }
}
