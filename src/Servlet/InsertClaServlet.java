package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ClassesDao;
import Dao.ClassesDaoImpl;
import Dao.StudentDao;
import Dao.StudentDaoImpl;
import Dao.TeacherDao;
import Dao.TeacherDaoImpl;
import vo.student;
import vo.user;

/**
 * Servlet implementation class InsertClaServlet
 */
public class InsertClaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertClaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//处理乱码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//创建会话
		HttpSession session=request.getSession(false);
		//获取教师id
		int tid=(Integer)session.getAttribute("Tid");
		//创建TeacherDao对象
		TeacherDao tdi=new TeacherDaoImpl();
		//根据教师id查找学校
		String school=tdi.selectByTid(tid).getTschool();
		//获取页面的值
		String sid=request.getParameter("sid");//学号
		//System.out.println(sid+"sid学号??????");
		String sname=request.getParameter("sname");//姓名
		String ssex=request.getParameter("ssex");//性别
		String sclass=request.getParameter("class");//班级
		if("".equals(sclass)){
			request.setAttribute("showclamsg", "请选择班级");
			request.getRequestDispatcher("showCla.jsp").forward(request, response);
		}else{
			//创建StudentDao对象和ClassDao对象
			StudentDao sdi=new StudentDaoImpl();
			ClassesDao cdi=new ClassesDaoImpl();
			//班级编号
			int cid=cdi.selectByTidClasses(tid, sclass).getCid();
			//按学生学号和学校查找学生
			if(sdi.selectBySidSschool(sid, school)==null){
				//创建JavaBean对象（student和user）
				student student=new student();		
				user suser=new user(); 
				student.setSid(sid);
				student.setSname(sname);
				student.setSsex(ssex);
				student.setSclass(cid);
				student.setSschool(school);
				suser.setSuname(sid);
				suser.setSuschool(school);
				sdi.insertStudent(student);
				sdi.insertSuser(suser);
				//将班号存放到request域中
				request.setAttribute("classes", sclass);
				//请求转发
				request.getRequestDispatcher("ShowClaServlet").forward(request, response);
			}else{
				request.setAttribute("classes", "已添加该学生");
				//请求转发
				request.getRequestDispatcher("insertCla.jsp").forward(request, response);
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
