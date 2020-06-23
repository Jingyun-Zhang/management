package Servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ScheckResourceServlet
 */
public class ScheckResourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScheckResourceServlet() {
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
		//获取页面内容(资料名称)
		String rname=request.getParameter("rname");
		//System.out.println("rname"+rname);
		String path = this.getServletContext().getRealPath("./")+File.separator+"Upload"+File.separator+tid+"/"+rname;
		 List<String> fileContent=new ArrayList<String>();
		 try {
			//fileContent=readWord(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*//创建文件
		 File file = new File(path);
		 //获取文件名
		 String fileName=file.getName(); 
		 //获取文件后缀名
		 String fileTyle=fileName.substring(fileName.lastIndexOf("."),fileName.length()+1); 
		 System.out.println(fileTyle);*/
 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
