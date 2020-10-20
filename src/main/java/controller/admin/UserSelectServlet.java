package controller.admin;

import dao.UserDao;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 根据uiserid 或者无条件查询用户表
 */
@WebServlet(name = "UserSelectServlet", urlPatterns = "/userSelectServlet")
public class UserSelectServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收参数
        String userId = req.getParameter("userid");
        String currentPage1 = req.getParameter("currentPage1");

        //将这个参数传给UserDao 的userSelect(String userid)
        UserDao userDao = new UserDao();
        //获取数据库tb_user表的是数据条数
        int dataTotal = userDao.getDataTotal();
        //一页显示五条
        int pageSize = 5;
        //算出多少页
        int page = dataTotal % pageSize != 0 ? dataTotal / pageSize + 1 : dataTotal / pageSize;
        //假设当前页 currentPage
        int currentPage=1;
        currentPage = Integer.parseInt(currentPage1);
        if(currentPage>page){
            //当前页数大于总页数，停留在最后一页
            currentPage=page;
        }
        if(currentPage<1){
            //当前页小于1，停留在第一页
            currentPage=1;
        }




        try {
            List<User> list = userDao.userSelect(userId,currentPage,pageSize);
            System.out.println(list.toString());
            //将数据绑定到userlist.jsp页面
            //将当前页绑定到页面，作为一个参数值传进服务端
            req.setAttribute("page",page);
            req.setAttribute("currentPage",currentPage);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/resources/admin/userlist.jsp").forward(req, resp);


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
