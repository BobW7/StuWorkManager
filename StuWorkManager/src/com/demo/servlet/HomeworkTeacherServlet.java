package com.demo.servlet;




import com.demo.dao.HomeworkDAO;
import com.demo.dao.HomeworkStudentDAO;
import com.demo.dao.impl.HomeworkDAOImpl;
import com.demo.dao.impl.HomeworkStudentDAOImpl;
import com.demo.vo.Homework;
import com.demo.vo.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 教师端-学生发布作业控制器
 */
@WebServlet(name = "HomeworkTeacherServlet", urlPatterns = "/HomeworkTeacher")
public class HomeworkTeacherServlet extends HttpServlet {
    private HomeworkDAO studentJDBC= new HomeworkDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> map= studentJDBC.list(null);

        req.setAttribute("homework", map.get("list"));

        req.getRequestDispatcher("/jsp/homework-teacher.jsp").forward(req, resp);
    }

    // https://www.cnblogs.com/xiaochuan94/p/9184444.html
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        // 获取方法名
        String name = req.getParameter("method");

        if(name == null || name.isEmpty()){
            throw new RuntimeException("method parameter does not exist");
        }

        // 获得当前类的Class对象
        Class c = this.getClass();
        Method method = null;
        try {
            // 使用反射机制获取在本类中声明了的方法
            method =  getClass().getDeclaredMethod(name, HttpServletRequest.class, HttpServletResponse.class);
            // 反射调用方法
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    protected void addHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Homework s = new Homework();
        s.setHomeworkContent(req.getParameter("content"));
        s.setHomeworkTittle(req.getParameter("title"));
        studentJDBC.add(s);
        // 刷新页面
        resp.sendRedirect("HomeworkTeacher");
    }

    protected void updateHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Homework s = new Homework();
        s.setId(Long.parseLong(req.getParameter("homeworkId")));
        s.setHomeworkContent(req.getParameter("content"));
        s.setHomeworkTittle(req.getParameter("title"));
        studentJDBC.update(s);
        // 刷新页面
        resp.sendRedirect("HomeworkTeacher");
    }

    protected void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        studentJDBC.delete(Long.parseLong(req.getParameter("id")));
        // 刷新页面
        resp.sendRedirect("HomeworkTeacher");
    }

}

