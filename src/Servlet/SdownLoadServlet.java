package Servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SdownLoadServlet
 */
public class SdownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SdownLoadServlet() {
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
		//获取教id
		int tid=(Integer) session.getAttribute("tid");
		String file = request.getParameter("filename"); //客户端传递的需要下载的文件名
		System.out.println(file);
        String path = this.getServletContext().getRealPath("./")+File.separator+"Upload"+File.separator+tid+"/"+file; //默认认为文件在当前项目的根目录
        FileInputStream fis = new FileInputStream(path);
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(file, "utf-8"));
        ServletOutputStream out = response.getOutputStream();
        byte[] bt = new byte[1024];
        int length = 0;
        while((length=fis.read(bt))!=-1){
            out.write(bt,0,length);
        }
        out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
