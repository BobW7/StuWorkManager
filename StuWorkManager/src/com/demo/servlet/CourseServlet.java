package com.demo.servlet;



import com.demo.service.CourseService;
import com.demo.service.impl.CourseServiceImpl;
import com.demo.vo.Course;

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

/**
 * 教师课程列表
 * 可以对课程进行增删查改
 */
@WebServlet(name = "CourseServlet", urlPatterns = "/Course")
public class CourseServlet extends HttpServlet {
    private CourseService courseService= new CourseServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> courseList = courseService.list();
        req.setAttribute("courseList", courseList.get("list"));
        req.getRequestDispatcher("/jsp/course.jsp").forward(req, resp);
    }

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

    protected void addCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Course s = new Course();
        s.setCourseName(req.getParameter("courseName"));
        s.setCourseTime(Integer.parseInt(req.getParameter("courseTime")));
        courseService.add(s);
        // 刷新页面
        resp.sendRedirect("Course");
    }

    protected void updateCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Course s = new Course();
        s.setId(Long.parseLong(req.getParameter("id")));
        s.setCourseName(req.getParameter("courseName"));
        s.setCourseTime(Integer.parseInt(req.getParameter("courseTime")));
        courseService.update(s);
        // 刷新页面
        resp.sendRedirect("Course");
    }

    protected void deleteCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        courseService.delete(Long.parseLong(req.getParameter("id")));
        // 刷新页面
        resp.sendRedirect("Course");
    }
}

