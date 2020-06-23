package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import vo.classes;
import vo.student;

/**
 * Servlet implementation class ShowClaServlet
 */
public class ShowClaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowClaServlet() {
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
		//获取班号
		String classes=(String)request.getAttribute("classes");
		//System.out.println(classes+"班号111111");
		//创建TeacherDao对象
		TeacherDao tdi=new TeacherDaoImpl();
		//创建ClassesDao对象
		ClassesDao cdi=new ClassesDaoImpl();
		//根据教师id查找学校
		String school=tdi.selectByTid(tid).getTschool();
		//System.out.println("学校："+school);
		//根据教师id和班级查找班级号
		int cid=cdi.selectByTidClasses(tid, classes).getCid();
		//System.out.println("班级编号："+cdi);
		//获取班级列表
		//List<classes> clist=(List<classes>) session.getAttribute("clist1");
		//创建学生列表
		List<student> slist=new ArrayList<student>();
		//创建StudentDao对象
		StudentDao sdi=new StudentDaoImpl();
		//根据学校和班级号查找学生
		slist=sdi.selectBySchoolClass(school,cid);
	/*	for(student s:slist){
			System.out.println("学号："+s.getSid());
			System.out.println("姓名"+s.getSname());
		}*/
		request.setAttribute("slist", slist);
		request.setAttribute("classes", classes);
		request.getRequestDispatcher("showCla.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
