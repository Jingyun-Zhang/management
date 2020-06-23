package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ChatDao;
import Dao.ChatDaoImpl;
import vo.chat;
import vo.classes;

/**
 * Servlet implementation class AdvanceServlet
 */
public class InsertRaiseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertRaiseServlet() {
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
		//获取会话内容
		int tid=(Integer) session.getAttribute("tid");//教师编号
		classes classes=(vo.classes) session.getAttribute("classes");//班级
		int cid=classes.getCid();//班级编号
		//获取页面的值
		String raise=request.getParameter("raise");
		//创建JavaBean对象
		chat ch=new chat();
		//创建ChatDao对象
		ChatDao chdi=new ChatDaoImpl();
		ch.setRaise(raise);
		ch.setChcid(cid);
		ch.setChtid(tid);
		chdi.insertRaise(ch);
		response.sendRedirect("RaiseServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
