package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ProblemDao;
import Dao.ProblemDaoImpl;
import vo.problem;

/**
 * Servlet implementation class StartTestServlet
 */
public class StartTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartTestServlet() {
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
		int tid=(Integer) session.getAttribute("tid");
		//获取页面的值
		String textpid=request.getParameter("textpid");//习题id列表
		int textid=Integer.parseInt(request.getParameter("textid"));//试卷编号
		//将字符串转化为字符串数组
		String[] textpidlist=textpid.split(",");
		//创建ProblemDao对象
		ProblemDao textpdi=new ProblemDaoImpl();
		//创建集合列表
		List<problem> prolist=new ArrayList<problem>();
		prolist=textpdi.selectByPidlist(textpidlist);
		session.setAttribute("prolist", prolist);//对象题列表
		request.setAttribute("textid", textid);//试卷编号
		request.getRequestDispatcher("startTest.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
