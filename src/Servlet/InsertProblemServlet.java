package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ProblemDao;
import Dao.ProblemDaoImpl;
import vo.problem;

/**
 * Servlet implementation class InsertProblemServlet
 */
public class InsertProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertProblemServlet() {
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
		//获取页面的值
		String quest=request.getParameter("problem");//题目
		String optionA=request.getParameter("optionA");//选项
		String optionB=request.getParameter("optionB");
		String optionC=request.getParameter("optionC");
		String optionD=request.getParameter("optionD");
		String solution=request.getParameter("solution");//正确选项
		String analyzing=request.getParameter("analyzing");//解析
		//创建JavaBean对象（problem）
		problem problem=new problem();
		problem.setTid(tid);//教师id
		problem.setPoblem(quest);//问题
		problem.setOptionA(optionA);//选项
		problem.setOptionB(optionB);
		problem.setOptionC(optionC);
		problem.setOptionD(optionD);
		problem.setSolution(solution);//正确答案
		problem.setAnalyzing(analyzing);//解析
/*		System.out.println(problem.getPoblem());
		System.out.println(problem.getOptionA());
		System.out.println(problem.getSolution());*/
		//创建ProblemDao对象
		ProblemDao pdi=new ProblemDaoImpl();
		//添加题目
		pdi.insertProblem(problem);
		//跳转
		response.sendRedirect("ShowProblemServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
