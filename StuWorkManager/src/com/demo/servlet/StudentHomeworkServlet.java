package com.demo.servlet;



import com.demo.dao.HomeworkStudentDAO;
import com.demo.dao.impl.HomeworkStudentDAOImpl;
import com.demo.vo.Course;
import com.demo.vo.StudentHomework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.logging.LoggingMXBean;

/**
 * 教师端-学生已提交作业控制器
 */
@WebServlet(name = "StudentHomeworkServlet", urlPatterns = "/StudentHomework")
public class StudentHomeworkServlet extends HttpServlet {
    private HomeworkStudentDAO studentHomeworkJDBC = new HomeworkStudentDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> courseList = studentHomeworkJDBC.list(null);
        req.setAttribute("studentHomeworkList", courseList.get("list"));
        req.getRequestDispatcher("/jsp/studentHomework.jsp").forward(req, resp);
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

    protected void addStudentHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        StudentHomework s = new StudentHomework();
        s.setHomeworkContent(req.getParameter("content"));
        s.setHomeworkTitle(req.getParameter("title"));
        s.setStudentId(Long.parseLong(req.getParameter("studentId")));
        s.setHomeworkId(Long.parseLong(req.getParameter("homeworkId")));

        studentHomeworkJDBC.add(s);
        // 刷新页面
        resp.sendRedirect("HomeworkStudent");
    }

}

