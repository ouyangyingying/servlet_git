package controller.admin;

import dao.UserDao;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SelectUserByIdForUpdate", urlPatterns = "/toUserUpdateServlet")
public class SelectUserByIdForUpdate extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userid = req.getParameter("userid");
        UserDao userDao = new UserDao();
        User user = userDao.selectUserInfoById(userid);
        //将user对象绑定到req，转发到用户信息修改页面
        req.setAttribute("user",user);
        req.getRequestDispatcher("/resources/admin/userupdate.jsp").forward(req,resp);

    }
}
