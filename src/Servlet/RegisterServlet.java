package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.SchoolDao;
import Dao.SchoolDaoImpl;
import Dao.TeacherDao;
import Dao.TeacherDaoImpl;
import vo.administrator;
import vo.teacher;

public class RegisterServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public RegisterServlet() {
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
		//获取教师注册信息
		String tnum=request.getParameter("Tnum");//教师编号
		String tname=request.getParameter("Tname");//姓名
		String tsex=request.getParameter("Tsex");//性别
		//System.out.println("性别："+tsex);
		String tschool=request.getParameter("Tschool");//所在学校
		String title=request.getParameter("title");//职称
		String temail=request.getParameter("Temail");//邮箱
		String tphone=request.getParameter("Tphone");//电话
		String tupass=request.getParameter("Tpass");//密码
		String tuIdentify=request.getParameter("TIdentify");//确认密码

			
		
		//正则表达式检验邮箱
		if(!("".equals(temail))){
			String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern p = Pattern.compile(REGEX_EMAIL);
			Matcher m = p.matcher(temail);
			if(!(m.matches())){
				String RTmsg="邮箱有误，请重新填写";
				request.setAttribute("RTmsg",RTmsg);
				request.getRequestDispatcher("register.jsp").forward(request,response); 
				return;
			}
		}
		//正则表达式检验电话
		if(!("".equals(tphone))){
			//String REGEX_phone = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";
			String REGEX_phone="^[1][3,4,5,7,8][0-9]{9}$";
			Pattern p = Pattern.compile(REGEX_phone);
			Matcher m = p.matcher(tphone);
			if(!(m.matches())){
				String RTmsg="电话有误，请重新填写";
				request.setAttribute("RTmsg",RTmsg);
				request.getRequestDispatcher("register.jsp").forward(request,response);
				return;
			}
		}

		//创建TeacherDao类
		TeacherDao tdi=new TeacherDaoImpl();
		//创建javaBean对象（teacher、administrator）
		teacher teacher=new teacher();
		administrator admin=new administrator();
		
		if("".equals(tnum) || "".equals(tname) || "".equals(tschool) || "".equals(title) || "".equals(tupass) || "".equals(tuIdentify)){
			//若教师编号、姓名、所在学校、职称、登录名、密码或确认密码为空，则转发到注册界面
			String RTmsg="教师编号、姓名、所在学校、职称、密码、确认密码不能为空";
			request.setAttribute("RTmsg",RTmsg);
			request.getRequestDispatcher("register.jsp").forward(request,response);
		}else{
			//若不为空，查看教师是否已注册
			teacher=tdi.selectByTnumTschool(tnum,tschool);
			if(teacher!=null){
				//该教师已注册
				String LTmsg="您已注册，请勿重复注册账号";
				request.setAttribute("LTmsg",LTmsg);
				request.getRequestDispatcher("Tlogin.jsp").forward(request,response);
			}else if(!(tupass.equals(tuIdentify))){
				//否则检查密码和确认密码是否一致
				String RTmsg="密码和确认密码不一致";
				request.setAttribute("RTmsg",RTmsg);
				request.getRequestDispatcher("register.jsp").forward(request,response);
			}else{
				String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";
				Pattern p = Pattern.compile(REGEX_PASSWORD);
				Matcher m = p.matcher(tupass);
				if(!(m.matches())){
					String RTmsg="密码由6-20位字母或数字组成";
					request.setAttribute("RTmsg",RTmsg);
					request.getRequestDispatcher("register.jsp").forward(request,response);
				}else{
					teacher=new teacher();
					teacher.setTnum(tnum);
					teacher.setTname(tname);
					teacher.setTsex(tsex);
					teacher.setTschool(tschool);
					teacher.setTitle(title);
					teacher.setTemail(temail);
					teacher.setTphone(tphone);
					admin.setTunum(tnum);
					admin.setTuschool(tschool);
					admin.setTupass(tupass);
					tdi.insertTeacher(teacher);
					admin.setTuid(tdi.selectByTnumTschool(tnum, tschool).getTid());
					tdi.insertAdmin(admin);
					//创建SchoolDao对象
					SchoolDao schdio=new SchoolDaoImpl();
					//查找学校，若不存在则添加学校
					schdio.selectSchool(tschool);
					String LTmsg="注册成功，请登录";
					request.setAttribute("LTmsg",LTmsg);
					request.getRequestDispatcher("Tlogin.jsp").forward(request,response);
				}				
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
