
<%@ page import="java.util.List" %>
<%@ page import="com.demo.vo.Course" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>course</title>
  <style>
    #div4, #div5 {
      width: 50%;
      float: left;
      /*border: black;*/
      text-align: center;
    }
  </style>
</head>
<body>
<h2 align="center">学生作业管理系统——教师端</h2>
<hr color="black">
<h3 align="center">课程列表</h3>
<div>
  <table align="center" width="960" border="1" cellpadding="1" cellspacing="1">
    <tr align="center" height="50" bgcolor="gray">
      <td>课程ID</td>
      <td>课程名称</td>
      <td>课程学时</td>
      <td>创建时间</td>
      <td>更新时间</td>
      <td>操作</td>
    </tr>
    <%
      // 通过网络请求获取结果
      List<Course> list = (List<Course>) request.getAttribute("courseList");
      // 通过HomeworkJDBC对象获取结果
//    HomeworkJDBC shJDBC = new HomeworkJDBC();
//    List<Homework> list = hJDBC.selectAll();
      for (Course h : list) {
    %>
    <tr align="center" bgcolor="white" height="30">
      <td><%=h.getId()%>
      </td>
      <td><%=h.getCourseName()%>
      </td>
      <td><%=h.getCourseTime()%>
      </td>
      <td><%=h.getCreateTime()%>
      </td>
      <td><%=h.getUpdateTime()%>
      </td>
      <td>
        <form action="Student?method=deleteStudent" method="post" onsubmit="location.reload()">
          <input type="hidden" name="studentId" value="<%=h.getId()%>">
          <input type="submit" value="删除学生信息"/>
        </form>
      </td>
    </tr>
    <%
      }
    %>
  </table>
</div>
<div align="center">
  <div id="div4">
    <h3 align="center">添加课程</h3>
    <div align="center">
      <form action="Course?method=addCourse" method="post" onsubmit="location.reload()">
        <table>
          <tr>
            <td align="right">课程名称:&ensp;&ensp;</td>
            <td><input type="text" name="courseName"></td>
          </tr>
          <tr>
            <td valign="right">课程课时:&ensp;&ensp;</td>
            <td><input type="text" name="courseTime"></td>
          </tr>
          <tr>
            <td><br></td>
            <td><br></td>
          </tr>
          <tr>
            <td></td>
            <td>
              <input type="button" name="Submit" onclick="history.back();" value="返回上一页">&ensp;
              <input type="submit" value="添加课程"/>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
  <div id="div5">
    <h3 align="center">修改课程</h3>
    <div align="center">
      <form action="Course?method=updateCourse" method="post" onsubmit="location.reload()">
        <table>
          <tr>
            <td align="right">作业ID:&ensp;&ensp;</td>
            <td><input type="text" name="id"></td>
          </tr>
          <tr>
            <td align="right">课程名称:&ensp;&ensp;</td>
            <td><input type="text" name="courseName"></td>
          </tr>
          <tr>
            <td valign="right">课程课时:&ensp;&ensp;</td>
            <td><input type="text" name="courseTime"></td>
          </tr>
          <tr>
            <td><br></td>
            <td><br></td>
          </tr>
          <tr>
            <td></td>
            <td>
              <input type="button" name="Submit" onclick="history.back();" value="返回上一页">&nbsp;
              <input type="submit" value="修改课程"/>
            </td>
          </tr>
        </table>

      </form>
    </div>
  </div>

  <input type="button" name="Submit" onclick="history.back();" value="返回上一页">&ensp;
  <button onclick="location.href='index.jsp'" type="button">返回首页</button>&ensp;
  <button onclick="location.href='StudentHomework'" type="button">查看作业提交列表</button>&ensp;
  <button onclick="location.href='Student'" type="button">查看学生列表</button>&ensp;
</div>

</body>
</html>
