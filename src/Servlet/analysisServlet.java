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
import Dao.TextDao;
import Dao.TextDaoImpl;
import vo.problem;
import vo.text;

/**
 * Servlet implementation class analysisServlet
 */
public class analysisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public analysisServlet() {
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
		int textid=Integer.parseInt(request.getParameter("textid"));//试卷编号
		//System.out.println("解析："+textid);
		//创建TextDao对象
		TextDao textdi=new TextDaoImpl();
		//创建JavaBean对象（text）
		text text=new text();
		text=textdi.selectByTextid(textid);
		//获取试卷编号中的题目
		String pidlist=text.getTextpid();		
		//将字符串转化为字符串数组
		String[] textpidlist=pidlist.split(",");
		//创建ProblemDao对象
		ProblemDao textpdi=new ProblemDaoImpl();
		//创建集合列表
		List<problem> prolist=new ArrayList<problem>();
		prolist=textpdi.selectByPidlist(textpidlist);
		request.setAttribute("problemlist", prolist);
		request.getRequestDispatcher("analysis.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
