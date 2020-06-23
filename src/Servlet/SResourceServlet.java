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
import Dao.ResourceDao;
import Dao.ResourceDaoImpl;
import vo.classes;
import vo.resource;

/**
 * Servlet implementation class SResourceServlet
 */
public class SResourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SResourceServlet() {
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
		//获取老师id
		int tid=(Integer)session.getAttribute("tid");
		//获取班级
		classes classes=(vo.classes) session.getAttribute("classes");
		//获取班级id
		int cid=classes.getCid();
		//获取班级
		String sclass=classes.getClasses();
		//创建ResourceDao对象
		ResourceDao rdi=new ResourceDaoImpl();
		//创建集合列表
		List<resource> rlist=new ArrayList<resource>();
		rlist=rdi.selectByTidRclass(tid, sclass);
		//将资源列表存放到request域中
		request.setAttribute("rlist", rlist);
		//请求转发
		request.getRequestDispatcher("Sresource.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
