package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ScoreDao;
import Dao.ScoreDaoImpl;
import Dao.TextDao;
import Dao.TextDaoImpl;
import vo.text;
import vo.textScore;

/**
 * Servlet implementation class CheckClassScore
 */
public class CheckClassScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckClassScore() {
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
		//获取页面的值(班号)
		String classes=request.getParameter("select");
		//创建集合列表
		List<text> textlist=new ArrayList<text>();
		List<textScore> textScorelist=new ArrayList<textScore>();
		//创建TextDao和ScoreDao对象
		TextDao textdi=new TextDaoImpl();
		ScoreDao psdi=new ScoreDaoImpl();
		//根据教师id和班号查找试卷
		textlist=textdi.selectByTidClasses(tid, classes);
		//根据教师id和试卷查找
		textScorelist=psdi.selectByTidText(tid, textlist);
		request.setAttribute("textScoreList", textScorelist);
		request.getRequestDispatcher("feedback.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
