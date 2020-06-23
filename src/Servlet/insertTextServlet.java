package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import Dao.TextDao;
import Dao.TextDaoImpl;
import vo.text;

//import org.apache.commons.lang.StringUtils;

/**
 * Servlet implementation class insertTextServlet
 */
public class insertTextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertTextServlet() {
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
		//System.out.println("tid"+tid);
		//获取页面的值
		String[] plist=request.getParameterValues("choose");//题目编号
		String classes=request.getParameter("classes");
		//数组转化为字符串
		String pidlist=StringUtils.join(plist,",");
		//System.out.println("classes"+classes);
		//创建JavaBean对象（text）
		text text=new text();
		text.setTexttid(tid);
		text.setTextpid(pidlist);
		text.setTextclass(classes);
		//创建TextDao对象
		TextDao textdi=new TextDaoImpl();
		textdi.insert(text);
		response.sendRedirect("textServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
