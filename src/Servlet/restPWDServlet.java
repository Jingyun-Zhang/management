package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.StudentDao;
import Dao.StudentDaoImpl;
import Dao.TeacherDao;
import Dao.TeacherDaoImpl;
import vo.teacher;

/**
 * Servlet implementation class restPWDServlet
 */
public class restPWDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public restPWDServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//处理乱码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//创建会话
		HttpSession session=request.getSession(false);
		//获取教师id
		int tid=(Integer) session.getAttribute("Tid");
		//获取页面的值
		String sid=request.getParameter("sid");
		System.out.println("sid"+sid);
		//创建TeacherDao对象
		TeacherDao tdi=new TeacherDaoImpl();
		//创建JavaBean对象
		teacher teacher=new teacher();
		//根据教师id获取学校
		String school=tdi.selectByTid(tid).getTschool();
		//创建StudentDao对象
		StudentDao sdi=new StudentDaoImpl();
		//根据学生学号和所在学校更改学生密码
		sdi.updatePass(sid, school, "000000");
		response.sendRedirect("ShowClaServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
