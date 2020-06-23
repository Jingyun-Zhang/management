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
import vo.score;
import vo.textScore;

/**
 * Servlet implementation class checkScoreServlet
 */
public class checkScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkScoreServlet() {
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
		//获取老师id,学号
		int tid=(Integer)session.getAttribute("tid");
		String sid=(String)session.getAttribute("Sid");
		//创建集合列表
		List<textScore> textScorelist=new ArrayList<textScore>();
		//创建ScoreDao对象
		ScoreDao psdi=new ScoreDaoImpl();
		//查找试卷得分情况
		textScorelist=psdi.selectByTidSid(tid,sid);
		//将试卷得分情况添加到request域中
		request.setAttribute("textScorelist",textScorelist);
		request.getRequestDispatcher("checkScore.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
