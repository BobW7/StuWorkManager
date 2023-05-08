package com.demo.servlet;


import com.demo.service.StudentService;
import com.demo.service.impl.StudentServiceImpl;
import com.demo.vo.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

// 教师看到学生列表
@WebServlet(name = "StudentServlet", urlPatterns = "/Student")
public class StudentServlet extends HttpServlet {
    private StudentService studentJDBC= new StudentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> map= studentJDBC.list(null);

        req.setAttribute("studentList", map.get("list"));

        req.getRequestDispatcher("/jsp/teacher.jsp").forward(req, resp);
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

    protected void addStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Student s = new Student();
        s.setId(Long.parseLong(req.getParameter("studentId")));
        s.setName(req.getParameter("studentName"));
        studentJDBC.add(s);
        // 刷新页面
        resp.sendRedirect("Student");
    }

    protected void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Student s = new Student();
        s.setId(Long.parseLong(req.getParameter("studentId")));
        s.setName(req.getParameter("studentName"));
        studentJDBC.update(s);
        // 刷新页面
        resp.sendRedirect("Student");
    }

    protected void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        studentJDBC.delete(Long.parseLong(req.getParameter("studentId")));
        // 刷新页面
        resp.sendRedirect("Student");
    }

}

