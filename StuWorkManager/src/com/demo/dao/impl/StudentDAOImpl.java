package com.demo.dao.impl;

import com.demo.dao.StudentDAO;
import com.demo.util.Util;
import com.demo.vo.Student;

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
 * 用户模块的DAO层（数据层）的具体实现类，对StudentDAO接口中定义的增删改查等抽象方法作出具体的功能实现
 */
public class StudentDAOImpl implements StudentDAO {

    //@Override
    public void add(Student vo) {
        String sql = "insert into `s_student` (`id`,`name`,`create_time`,`update_time`) values(?,?,?,?)";
        try {
            Connection c = Util.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setLong(1, vo.getId());
            ps.setString(2, vo.getName());
            ps.setString(3, LocalDate.now().toString());
            ps.setString(4, LocalDate.now().toString());
            ps.execute();
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Override
    public void update(Student vo) {
        String sql = "update `s_student` set `name` = ?, update_time=?   where `id` = ?";
        try {
            Connection c = Util.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, vo.getName());
            ps.setString(2, LocalDate.now().toString());
            ps.setLong(3, vo.getId());
//            ps.setLong(8, vo.getId());
            ps.execute();
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Override
    public boolean delete(long id) {
        try {
            Connection c = Util.getConnection();
            Statement s = c.createStatement();
            String sql = "delete from `s_student` where id = " + id;
            s.execute(sql);
            s.close();
            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //@Override
    public Student get(Serializable id) {
        Student vo = null;
        try {
            Connection c = Util.getConnection();
            Statement s = c.createStatement();
            String sql = "select * from `s_student` where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                vo = new Student();
                vo.setId(rs.getLong("id"));
                vo.setName(rs.getString("name"));
                vo.setCreateTime(rs.getDate("create_time"));
                vo.setUpdateTime(rs.getDate("update_time"));
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

    //@Override
    public Map<String, Object> list(Map<String, Object> params) {
        List<Student> list = new ArrayList();
        int totalCount = 0;
        String condition = "";
        String sqlList;

        try {
            Connection c = Util.getConnection();
            PreparedStatement ps;
            ResultSet rs;
                sqlList = "select * from `s_student` where 1=1 " + condition + " order by id asc ";
                ps = c.prepareStatement(sqlList);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Student vo = new Student();
                    vo.setId(rs.getLong("id"));
                    vo.setName(rs.getString("name"));
                    vo.setCreateTime(rs.getDate("create_time"));
                    vo.setUpdateTime(rs.getDate("update_time"));
                    list.add(vo);
                }
            String sqlCount = "select count(*) from `s_student` where 1=1 " + condition;
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
