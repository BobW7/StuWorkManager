package com.demo.service.impl;

import com.demo.dao.StudentDAO;
import com.demo.dao.impl.StudentDAOImpl;
import com.demo.service.StudentService;
import com.demo.vo.Student;

import java.io.Serializable;
import java.util.Map;

/**
 * 用户模块的Service层（业务层）的具体实现类，对UserService接口中定义的抽象方法作出具体的功能实现
 */
public class StudentServiceImpl implements StudentService {
    //@Override
    public void add(Student vo) {
        StudentDAO studentDAO = new StudentDAOImpl();
        studentDAO.add(vo);
    }

    //@Override
    public void delete(long id) {
        StudentDAO studentDAO = new StudentDAOImpl();
        studentDAO.delete(id);
    }

    //@Override
    public void update(Student vo) {
        StudentDAO studentDAO = new StudentDAOImpl();
        studentDAO.update(vo);
    }

    //@Override
    public Student get(Serializable id) {
        StudentDAO studentDAO = new StudentDAOImpl();
        return studentDAO.get(id);
    }

    //@Override
    public Map<String, Object> list(Map<String, Object> params) {
        StudentDAO studentDAO = new StudentDAOImpl();
        return studentDAO.list(params);
    }
}
