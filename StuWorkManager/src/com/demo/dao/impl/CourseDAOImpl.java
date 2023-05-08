package com.demo.dao.impl;


import com.demo.dao.CourseDAO;
import com.demo.util.Util;
import com.demo.vo.Course;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户模块的DAO层（数据层）的具体实现类，对CourseDAO接口中定义的增删改查等抽象方法作出具体的功能实现
 */
public class CourseDAOImpl implements CourseDAO {

    @Override
    public void add(Course vo) {
        String sql = "insert into `course` (`course_name`,`course_time`,`create_time`,`update_time`) values(?,?,?,?)";
        try {
            Connection c = Util.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            
            ps.setString(1, vo.getCourseName());
            ps.setInt(2, vo.getCourseTime());
            ps.setString(3, LocalDate.now().toString());
            ps.setString(4, LocalDate.now().toString());
            ps.execute();
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Course vo) {
        String sql = "update `course` set `course_name` = ? ,`course_time` = ? ,`update_time` = ?  where `id` = ?";
        try {
            Connection c = Util.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, vo.getCourseName());
            ps.setInt(2, vo.getCourseTime());
            ps.setString(3, LocalDate.now().toString());
            ps.setLong(4, vo.getId());
            ps.execute();
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(long id) {
        try {
            Connection c = Util.getConnection();
            Statement s = c.createStatement();
            String sql = "delete from `course` where id = " + id;
            s.execute(sql);
            s.close();
            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Course get(Serializable id) {
        Course vo = null;
        try {
            Connection c = Util.getConnection();
            Statement s = c.createStatement();
            String sql = "select * from `course` where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                vo = new Course();
                vo.setId(rs.getLong("id"));
                vo.setCourseName(rs.getString("course_name"));
                vo.setCourseTime(rs.getInt("course_time"));
                vo.setCreateTime(rs.getString("create_time"));
                vo.setUpdateTime(rs.getString("update_time"));
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

    @Override
    public Map<String, Object> list() {
        List<Course> list = new ArrayList();
        int totalCount = 0;
        String condition = "";
        String sqlList;
        try {
            Connection c = Util.getConnection();
            PreparedStatement ps;
            ResultSet rs;
                sqlList = "select * from `course` where 1=1 " + condition + " order by id asc ";
                ps = c.prepareStatement(sqlList);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Course vo = new Course();
                    vo.setId(rs.getLong("id"));
                    vo.setCourseName(rs.getString("course_name"));
                    vo.setCourseTime(rs.getInt("course_time"));
                    vo.setCreateTime(rs.getString("create_time"));
                    vo.setUpdateTime(rs.getString("update_time"));
                    list.add(vo);
                }
            String sqlCount = "select count(*) from `course` where 1=1 " + condition;
            ps = c.prepareStatement(sqlCount);
            rs = ps.executeQuery();
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
            rs.close();
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> result = new HashMap();
        result.put("list", list);
        result.put("totalCount", totalCount);
        return result;
    }
}
