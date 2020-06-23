package Servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.StudentDao;
import Dao.StudentDaoImpl;

/**
 * Servlet implementation class UpdatePwdServlet
 */
public class UpdatePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePwdServlet() {
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
		//获取页面信息
		String pwd=request.getParameter("Spass");
		String identy=request.getParameter("Sidentify");
		//若密码或确认密码为空
		if("".equals(pwd) || "".equals(identy)){
			String upmsg="密码或确认密码不能为空";
			request.setAttribute("upmsg",upmsg);
			request.getRequestDispatcher("updatePwd.jsp").forward(request,response);
		}else if(!(pwd.equals(identy))){
			//否则检查密码和确认密码是否一致
			String upmsg="密码和确认密码不一致";
			request.setAttribute("upmsg",upmsg);
			request.getRequestDispatcher("updatePwd.jsp").forward(request,response);
		}else{
			//检查密码是否符合正则表达式
			String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";
			Pattern p = Pattern.compile(REGEX_PASSWORD);
			Matcher m = p.matcher(pwd);
			//不符合
			if(!(m.matches())){
				String upmsg="密码由6-20位字母或数字组成";
				request.setAttribute("upmsg",upmsg);
				request.getRequestDispatcher("updatePwd.jsp").forward(request,response);
			}else{ //符合
				//创建会话
				HttpSession session=request.getSession(false);
				//获取会话的值
				String sid=(String) session.getAttribute("Sid");//学号
				String sschool=(String) session.getAttribute("Sschool");//学校
				//创建studentDao对象
				StudentDao sudi=new StudentDaoImpl(); 
				sudi.updatePass(sid,sschool,pwd);
			/*	String LSmsg="密码修改成功，请重新登录";
				request.setAttribute("LSmsg",LSmsg);*/
				request.getRequestDispatcher("QuitServlet").forward(request,response);
			}
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
