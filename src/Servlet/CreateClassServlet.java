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
import vo.classes;

/**
 * Servlet implementation class CreateClassServlet
 */
public class CreateClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateClassServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//处理乱码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//创建会话
		HttpSession session=request.getSession(false);
		//获取教师编号
		int Tid=(Integer)session.getAttribute("Tid");
		//获取页面信息
		String classes=request.getParameter("class");
		String major=request.getParameter("cmajor");
		//班级号
		String classes2=null;
		//创建ClassesDao对象
		ClassesDao cdi=new ClassesDaoImpl();
		//首先查看该班级是否已创建
		classes2=cdi.selectByTidClasses(Tid, classes).getClasses();
		if(classes2==null){
			//若未创建则创建班课
			cdi.insertClass(classes,Tid,major);
			request.getRequestDispatcher("ShowClassesServlet").forward(request,response);
		}else{
			String CCmsg="请勿重复创建班级";
			request.setAttribute("CCmsg", CCmsg);
			request.getRequestDispatcher("createClass.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
