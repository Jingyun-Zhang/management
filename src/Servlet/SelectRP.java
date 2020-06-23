package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ChatDao;
import Dao.ChatDaoImpl;
import Dao.ClassesDao;
import Dao.ClassesDaoImpl;
import Dao.ProblemDao;
import Dao.ProblemDaoImpl;
import Dao.ResourceDao;
import Dao.ResourceDaoImpl;
import vo.chat;
import vo.classes;
import vo.problem;
import vo.resource;

/**
 * Servlet implementation class SelectRP
 */
public class SelectRP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectRP() {
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
		//获取页面的值
		String classes=request.getParameter("select");
		//System.out.println(classes);
		String op=request.getParameter("op");
		//System.out.println("xuanze:"+op);
		//创建会话
		HttpSession session=request.getSession(false);
		//获取老师id
		int tid=(Integer)session.getAttribute("Tid");
		if("r".equals(op)){
			//创建List列表
			List<resource> rlist=new ArrayList<resource>();
			//创建ResourceDao对象
			ResourceDao rdi=new ResourceDaoImpl();
			//根据班级和老师获取上传的资料
			rlist=rdi.selectByTidRclass(tid, classes);
			//将资源列表存放到request域中
			request.setAttribute("rlist", rlist);
			//请求转发
			request.getRequestDispatcher("showAllResource.jsp").forward(request, response);
		}else if("a".equals(op)){
			//创建List列表
			List<chat> chlist=new ArrayList<chat>();
			//创建ClassDao对象和ChatDao对象
			ClassesDao cdi=new ClassesDaoImpl();
			ChatDao chdi=new ChatDaoImpl();
			//根据教师id和班号查找班级编号
			int cid=cdi.selectByTidClasses(tid, classes).getCid();
			//根据班级编号和教师id获取师生间互动问题
			chlist=chdi.selectByTidCid(tid, cid);
			//将聊天内容列表存放到request域中
			request.setAttribute("chlist", chlist);
			//请求转发
			request.getRequestDispatcher("answer.jsp").forward(request, response);
		}else{
			/*//创建List集合
			List<classes> clist=new ArrayList<classes>();
			//创建ClassesDao对象
			ClassesDao cdi=new ClassesDaoImpl();
			//根据教师id查找班级
			clist=cdi.showAllClassesByTid(tid);*/
			//将班级存放到request域中
			request.setAttribute("classes", classes);
			//请求转发
			request.getRequestDispatcher("ShowClaServlet").forward(request, response);			
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
