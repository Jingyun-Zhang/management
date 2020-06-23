package Servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.QingjiaDao;
import Dao.QingjiaDaoImpl;
import vo.classes;
import vo.qingjia;

/**
 * Servlet implementation class SqingjiaServlet
 */
public class SqingjiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SqingjiaServlet() {
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
		
		//SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat st1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat st2 = new SimpleDateFormat("yyyy-MM-dd");
		//获取会话内容
		int tid=(Integer) session.getAttribute("tid");
		String sid=(String) session.getAttribute("Sid");//学号
		String sname=(String) session.getAttribute("sname");//学生姓名
		classes classes=(classes)session.getAttribute("classes");
		String sclass=classes.getClasses();//班级
		//获取页面内容
		String salreason=request.getParameter("Salreason");//原因
		String salstartdate=request.getParameter("Salstartdate");//开始时间
		String salenddate=request.getParameter("Salenddate");//结束时间
		 //datestart;
		try {
			Date datestart = st1.parse(salstartdate);
			Date dateend=st2.parse(salenddate);
			Long start = datestart.getTime();
			Long end= dateend.getTime();
			//创建Javabean对象(qingjia)
			qingjia al=new qingjia();
			al.setAlsid(sid);
			al.setAlsname(sname);
			al.setAlclass(sclass);
			al.setAlreason(salreason);
			al.setAlstartdate(new java.sql.Date(start));
			al.setAlenddate(new java.sql.Date(end));
			al.setAltid(tid);
			//创建QingjiaDao对象
			QingjiaDao aldi=new QingjiaDaoImpl();
			aldi.insertqingjia(al);
			request.setAttribute("salmsg", "已提交");
			request.getRequestDispatcher("sqingjia.jsp").forward(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
