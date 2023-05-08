package com.demo.dao;



import com.demo.vo.Course;

import java.io.Serializable;
import java.util.Map;

/**
 * 用户模块的DAO层（数据层）接口，提供增删改查等数据库操作的方法抽象
 */
public interface CourseDAO {
    /**
     * 增加用户表记录
     *
     * @param vo
     * @return
     */
    void add(Course vo);

    /**
     * 根据主键id，删除对应的用户表记录
     *
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新用户表记录
     *
     * @param vo
     * @return
     */
    void update(Course vo);

    /**
     * 根据主键id获取用户表记录的详情
     *
     * @param id
     * @return
     */
    Course get(Serializable id);

    /**
     * 根据条件查询用户的列表与数量
     *
     * @param params
     * @return
     */
    Map<String, Object> list();
}
