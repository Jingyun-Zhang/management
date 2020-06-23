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
import vo.classes;
import vo.problem;

/**
 * Servlet implementation class ShowProblemServlet
 */
public class ShowProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowProblemServlet() {
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
		int tid=(Integer)session.getAttribute("Tid");
		//获取教师创建的班级
		//List<classes> clist=(List<classes>) session.getAttribute("clist1");
		//创建List列表
		List<problem> plist=new ArrayList<problem>();
		//创建problemDao对象
		ProblemDao pdi=new ProblemDaoImpl();
		//遍历班级
		/*for(classes c:clist){
			//根据教师和班级查找问题
			plist=pdi.selectByTidClasses(tid, c.getClasses());
		}*/
		//根据教师id查找题目
		plist=pdi.selectByTid(tid);
		//将题目列表存放到request域中
		request.setAttribute("plist", plist);
		//请求转发
		request.getRequestDispatcher("showAllProblem.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
