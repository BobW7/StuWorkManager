package com.demo.service.impl;



import com.demo.dao.CourseDAO;
import com.demo.dao.impl.CourseDAOImpl;
import com.demo.service.CourseService;
import com.demo.vo.Course;

import java.io.Serializable;
import java.util.Map;

public class CourseServiceImpl implements CourseService {


    @Override
    public void add(Course vo) {
        CourseDAO CourseDAO = new CourseDAOImpl();
        CourseDAO.add(vo);
    }

    @Override
    public void delete(long id) {
        CourseDAO CourseDAO = new CourseDAOImpl();
        CourseDAO.delete(id);
    }

    @Override
    public void update(Course vo) {
        CourseDAO CourseDAO = new CourseDAOImpl();
        CourseDAO.update(vo);
    }

    @Override
    public Course get(Serializable id) {
        CourseDAO CourseDAO = new CourseDAOImpl();
        return CourseDAO.get(id);
    }

    @Override
    public Map<String, Object> list() {
        CourseDAO CourseDAO = new CourseDAOImpl();
        return CourseDAO.list();
    }
}
