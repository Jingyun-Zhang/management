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
import vo.classes;
import vo.problem;
import vo.text;
import vo.textScore;

/**
 * Servlet implementation class sProblemServlet
 */
public class sProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sProblemServlet() {
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
		int tid=(Integer)session.getAttribute("tid");
		//获取班级
		classes classes=(vo.classes) session.getAttribute("classes");
		//获取学号
		String sid=(String) session.getAttribute("Sid");
		//获取班号
		String cla=classes.getClasses();
		//System.out.println("班号："+cla);
		//创建集合列表
		List<text> textlist=new ArrayList<text>();
		//创建TextDao对象
		TextDao textdi=new TextDaoImpl();
		//创建ScoreDao对象
		ScoreDao textScore=new ScoreDaoImpl();
		//根据教师id和学号获取已测试的试卷列表
		List<textScore> textScorel=new ArrayList<textScore>();
		textScorel=textScore.selectByTidSid(tid, sid);
		//获取textlist
		textlist=textdi.selectByTidClasses(tid,cla);
		request.setAttribute("textlist", textlist);//试卷列表
		request.setAttribute("textScorel", textScorel);//已进行测试的试卷列表
		/*for(textScore textid:textScorel){
			System.out.println(textid.getTextid());
		}*/
		request.getRequestDispatcher("stext.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
