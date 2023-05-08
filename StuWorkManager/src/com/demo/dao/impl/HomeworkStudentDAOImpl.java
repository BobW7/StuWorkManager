package com.demo.dao.impl;

import com.demo.dao.HomeworkStudentDAO;
import com.demo.dao.StudentDAO;
import com.demo.util.Util;
import com.demo.vo.Homework;
import com.demo.vo.Student;
import com.demo.vo.StudentHomework;

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
 * 学生作业提交记录
 */
public class HomeworkStudentDAOImpl implements HomeworkStudentDAO {

    //@Override
    public void add(StudentHomework vo) {
        String sql = "insert into `s_student_homework` (`student_id`,`homework_id`,`homework_title`,`homework_content`,`create_time`,`update_time`) values(?,?,?,?,?,?)";
        try {
            Connection c = Util.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            
            ps.setLong(1, vo.getStudentId());
            ps.setLong(2, vo.getHomeworkId());
            ps.setString(3, vo.getHomeworkTitle());
            ps.setString(4, vo.getHomeworkContent());
            ps.setString(5, LocalDate.now().toString());
            ps.setString(6, LocalDate.now().toString());
            ps.execute();
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Override
    public void update(StudentHomework vo) {
        String sql = "update `s_student` set `Studentname` = ? ,`password` = ? ,`real_name` = ? ,`Student_sex` = ? ,`Student_phone` = ? ,`Student_text` = ? ,`Student_type` = ?  where `id` = ?";
        try {
            Connection c = Util.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            
//            ps.setString(1, vo.getStudentname());
//            ps.setString(2, vo.getPassword());
//            ps.setString(3, vo.getRealName());
//            ps.setString(4, vo.getStudentSex());
//            ps.setString(5, vo.getStudentPhone());
//            ps.setString(6, vo.getStudentText());
//            ps.setString(7, vo.getStudentType());
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
            String sql = "delete from `s_student_homework` where id = " + id;
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
    public StudentHomework get(Serializable id) {
        StudentHomework vo = null;
        try {
            Connection c = Util.getConnection();
            Statement s = c.createStatement();
            String sql = "select * from `s_student` where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                vo = new StudentHomework();
                vo.setId(rs.getLong("id"));
//                vo.setName(rs.getString("name"));
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
        List<StudentHomework> list = new ArrayList();
        int totalCount = 0;
        String condition = "";
        String sqlList;

        try {
            Connection c = Util.getConnection();
            PreparedStatement ps;
            ResultSet rs;
                sqlList = "select * from `s_student_homework` where 1=1 " + condition + " order by id asc ";
                ps = c.prepareStatement(sqlList);
                rs = ps.executeQuery();
                while (rs.next()) {
                    StudentHomework vo = new StudentHomework();
                    vo.setId(rs.getLong("id"));
                    vo.setHomeworkId(rs.getLong("homework_id"));
                    vo.setStudentId(rs.getLong("student_id"));
                    vo.setHomeworkId(rs.getLong("homework_id"));
                    vo.setHomeworkTitle(rs.getString("homework_title"));
                    vo.setHomeworkContent(rs.getString("homework_content"));
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
