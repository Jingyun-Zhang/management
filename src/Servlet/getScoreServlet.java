package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ScoreDao;
import Dao.ScoreDaoImpl;
import vo.problem;
import vo.score;

/**
 * Servlet implementation class getScoreServlet
 */
public class getScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getScoreServlet() {
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
		//获取老师id,题目，学号
		int tid=(Integer)session.getAttribute("tid");
		String sid=(String) session.getAttribute("Sid");
		List<problem> prolist=(List<problem>) session.getAttribute("prolist");//试卷 对象题集合
		//获取request域中的值
		int textid=Integer.parseInt(request.getParameter("textid"));//试卷编号
		int grade=0;//分数
		String row="";//正误
		/*String prolist=request.getParameter("prolist");//对象题集合
*/		//遍历		
		for(problem pro:prolist){
			if(pro.getSolution().equals(request.getParameter(""+pro.getPid()))){
				grade=grade+1;
				row=row+1;
				//System.out.println("正确");
			}else{
				row=row+0;
				//System.out.println("错误");
			}
		}
		//System.out.println("score"+score);
		//System.out.println("row"+row);
		//创建javaBean对象（score）
		score score=new score();
		score.setPstextid(textid);
		score.setPssid(sid);
		score.setGrade(grade);
		score.setPsrow(row);
		//创建ScoreDao对象
		ScoreDao psdi=new ScoreDaoImpl();
		//添加成绩
		psdi.insertScore(score);
		response.sendRedirect("checkScoreServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
