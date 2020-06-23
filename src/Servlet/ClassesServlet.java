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
import Dao.TeacherDao;
import Dao.TeacherDaoImpl;
import vo.classes;
import vo.teacher;

/**
 * Servlet implementation class ClassesServlet
 */
public class ClassesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//创建会话
		HttpSession session=request.getSession(false);
		//获取教师id
		int tid=(Integer)session.getAttribute("Tid");
		//创建ClassesDao对象
		ClassesDao cdi=new ClassesDaoImpl();
		//创建List集合
		List<classes> clist1=new ArrayList<classes>();
		//创建TeacherDao对象
		//TeacherDao tdi=new TeacherDaoImpl();
		//根据教师id查找班级
		clist1=cdi.showAllClassesByTid(tid);
		//根据教师id查找学校
		//String school=tdi.selectByTid(tid).getTschool();
		//根据学校和班级查找学生
		//for(String c:clist1){}
		request.setAttribute("clist1", clist1);
		request.getRequestDispatcher("#").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
