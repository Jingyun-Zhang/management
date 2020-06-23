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
import vo.chat;
import vo.classes;


/**
 * Servlet implementation class RaiseServlet
 */
public class RaiseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RaiseServlet() {
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
		//获取班级id
		classes classes=(vo.classes) session.getAttribute("classes");
		int cid=classes.getCid();
		//创建ChatDao对象
		ChatDao chdi=new ChatDaoImpl();
		//创建集合列表
		List<chat> chlist=new ArrayList<chat>();
		//根据教师id和班级id获取
		chlist=chdi.selectByTidCid(tid,cid);
		//将疑问存放到request域中
		request.setAttribute("chlist", chlist);
		//请求转发
		request.getRequestDispatcher("raise.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
