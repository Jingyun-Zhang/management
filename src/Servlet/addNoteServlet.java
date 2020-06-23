package Servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class addNoteServlet
 */
public class addNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

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
		int tid=(Integer) session.getAttribute("Tid");
		//获取页面内容
		String content=request.getParameter("takeNotes");
		//System.out.println("content:"+content);
		//文件目录
		String path= this.getServletContext().getRealPath("./")+File.separator+"takeNotes";
		//文件名
		String filename=tid+".txt";
		File file=new File(path+"/"+filename);
		FileOutputStream fos = null;
	    OutputStreamWriter osw = null;
	    try{
	    	 fos = new FileOutputStream(file, true);
	         osw = new OutputStreamWriter(fos, "gbk");
	         osw.write(content); //写入内容
	         osw.write("\r\n");  //换行
	    }catch(Exception e){
	    	e.printStackTrace();
	    }finally{
	    	 try {
	                if (osw != null) {
	                    osw.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            try {
	                if (fos != null) {
	                    fos.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	    }
		//appendMethodA(path+"/"+filename, content);
		response.sendRedirect("takeNotesServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
