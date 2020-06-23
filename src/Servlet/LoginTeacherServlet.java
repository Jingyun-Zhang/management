package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ClassesDao;
import Dao.ClassesDaoImpl;
import Dao.TeacherDao;
import Dao.TeacherDaoImpl;
import vo.administrator;
import vo.classes;

public class LoginTeacherServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public LoginTeacherServlet() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理乱码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取页面的值
		String Tunum=request.getParameter("Tunum");
		String Tuschool=request.getParameter("Tuschool");
		String Tupass=request.getParameter("Tupass");		
		//创建会话
		HttpSession session=request.getSession();
		//创建TeacherDao对象
		TeacherDao tdi=new TeacherDaoImpl();
		//创建javaBean对象（administrator）
		administrator admin=new administrator();
		if("".equals(Tunum) || "".equals(Tuschool) || "".equals(Tupass)){
			//若教师编号、所在学校或密码为空
			String LTmsg="教师编号、所在学校、密码不能为空";
			request.setAttribute("LTmsg",LTmsg);
			request.getRequestDispatcher("Tlogin.jsp").forward(request,response);
		}else if((admin=tdi.selectByTunumTuschool(Tunum,Tuschool))!=null){
			//教师编号、所在学校、密码不为空时，首先检查该管理员是否存
			//管理员存在，则检查密码是否正确
			if(admin.getTupass().equals(Tupass)){
				//密码正确，登录成功
				//创建ClassesDao对象
				ClassesDao cdi=new ClassesDaoImpl();
				//创建List集合
				List<classes> clist1=new ArrayList<classes>();
				//根据教师id查找班级
				clist1=cdi.showAllClassesByTid(admin.getTuid());
				//根据教师id查询教师姓名
				String Tname=tdi.selectByTid(admin.getTuid()).getTname();
				//将教师id、教师姓名、以及该教师创建的班级放置到会话中
				session.setAttribute("Tid", admin.getTuid());
				session.setAttribute("Tname", Tname);
				session.setAttribute("clist1", clist1);
				response.sendRedirect("Tfirst.jsp");
			}else{
				//密码错误
				String LTmsg="密码错误，请重新输入";
				request.setAttribute("LTmsg",LTmsg);
				request.getRequestDispatcher("Tlogin.jsp").forward(request,response);
			}
		}else{
				//未注册
				String LTmsg="请先注册";
				request.setAttribute("LTmsg",LTmsg);
				request.getRequestDispatcher("Tlogin.jsp").forward(request,response);
		}	
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}
