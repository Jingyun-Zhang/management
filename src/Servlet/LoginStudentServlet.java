package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ClassesDao;
import Dao.ClassesDaoImpl;
import Dao.StudentDao;
import Dao.StudentDaoImpl;
import vo.classes;
import vo.student;
import vo.user;

public class LoginStudentServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public LoginStudentServlet() {
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
		String Sname=request.getParameter("Sunum");
		String Sschool=request.getParameter("Suschool");
		String Spass=request.getParameter("Supass");
		//创建会话
		HttpSession session=request.getSession();
		//创建StudentDao对象
		StudentDao sdi=new StudentDaoImpl();
		//创建javaBean对象(student)
		user user=new user();
		if("".equals(Sname) || "".equals(Sschool) || "".equals(Spass)){
			//若用户名、学校或密码为空
			String LSmsg="用户名、学校或密码不能为空";
			request.setAttribute("LSmsg",LSmsg);
			request.getRequestDispatcher("Slogin.jsp").forward(request,response);
		}else{
			//用户名、学校、密码不为空时，首先检查该用户是否存在
			user=sdi.selectBySunameSuschool(Sname,Sschool);
			if(user!=null){
				//用户存在，则检查密码是否正确
				if(user.getSupass().equals(Spass)){
					//密码正确，登录成功
					session.setAttribute("Sid", Sname);//学号
					session.setAttribute("Sschool", Sschool);//学校
					//创建JavaBean对象
					student student=new student();
					//创建StudentDao对象和ClassesDao对象
					StudentDao sdi2=new StudentDaoImpl();
					ClassesDao cdi=new ClassesDaoImpl();
					student=sdi2.selectBySidSschool(Sname, Sschool);
					//根据班级编号查找班级
					classes classes=cdi.selectByCid(student.getSclass());
					session.setAttribute("sname", student.getSname());//学生姓名
					session.setAttribute("classes", classes);//班级
					session.setAttribute("tid", classes.getCteacher());//教师编号
					response.sendRedirect("Sfirst.jsp");
				}else{
					//密码错误
					String LSmsg="密码错误，请重新输入";
					request.setAttribute("LSmsg",LSmsg);
					request.getRequestDispatcher("Slogin.jsp").forward(request,response);
				}
			}else{
				//用户不存在
				String LTmsg="用户不存在";
				request.setAttribute("LTmsg",LTmsg);
				request.getRequestDispatcher("Slogin.jsp").forward(request,response);
			}
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
