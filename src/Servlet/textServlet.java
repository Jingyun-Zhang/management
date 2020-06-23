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
 * Servlet implementation class textServlet
 */
public class textServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public textServlet() {
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
		//获取老师id
		int tid=(Integer)session.getAttribute("Tid");
		//创建ProblemDao对象和TextDao对象
		ProblemDao pdi=new ProblemDaoImpl();
		TextDao textdi=new TextDaoImpl();
		//创建集合列表
		List<problem> plist=new ArrayList<problem>();//题目
		List<text> textlist=new ArrayList<text>();//试卷
		//获取题目和试卷
		plist=pdi.selectByTid(tid);
		textlist=textdi.selectByTid(tid);
		request.setAttribute("plist", plist);
		request.setAttribute("textlist", textlist);
		//创建集合
		request.getRequestDispatcher("text.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
