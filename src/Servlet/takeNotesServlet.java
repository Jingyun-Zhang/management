package Servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class takeNotesServlet
 */
public class takeNotesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public takeNotesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * 读取一个文本 一行一行读取
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static List<String> readFile02(String path) throws IOException {
        // 使用一个字符串集合来存储文本中的路径 ，也可用String []数组
        List<String> list = new ArrayList<String>();
        FileInputStream fis = new FileInputStream(path);
        // 防止路径乱码  如果utf-8 乱码 改GBK   eclipse里创建的txt 用UTF-8，在电脑上自己创建的txt 用GBK
        InputStreamReader isr = new InputStreamReader(fis, "gbk");
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        while ((line = br.readLine()) != null) {
          // 如果 t x t文件里的路径 不包含---字符串    这里是对里面的内容进行一个筛选
          if (line.lastIndexOf("---") < 0) {
            list.add(line);
          }
        }
        br.close();
        isr.close();
        fis.close();
        return list;
      }
    
    /** 
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。 
     */  
/*    public static void readFileByBytes(String fileName) {  
        File file = new File(fileName);  
        InputStream in = null;  
        try {  
            System.out.println("以字节为单位读取文件内容，一次读一个字节：");  
            // 一次读一个字节  
            in = new FileInputStream(file);  
            int tempbyte;  
            while ((tempbyte = in.read()) != -1) {  
                System.out.write(tempbyte);  
            }  
            in.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
            return;  
        }  
        try {  
            System.out.println("以字节为单位读取文件内容，一次读多个字节：");  
            // 一次读多个字节  
            byte[] tempbytes = new byte[100];  
            int byteread = 0;  
            in = new FileInputStream(fileName);  
            ReadFromFile.showAvailableBytes(in);  
            // 读入多个字节到字节数组中，byteread为一次读入的字节数  
            while ((byteread = in.read(tempbytes)) != -1) {  
                System.out.write(tempbytes, 0, byteread);  
            }  
        } catch (Exception e1) {  
            e1.printStackTrace();  
        } finally {  
            if (in != null) {  
                try {  
                    in.close();  
                } catch (IOException e1) {  
                }  
            }  
        }  
    }*/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//处理乱码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charsetutf-8");
		//创建会话
		HttpSession session=request.getSession(false);
		//获取教师id
		int tid=(Integer) session.getAttribute("Tid");
		//文件目录
		String path= this.getServletContext().getRealPath("./")+File.separator+"takeNotes";
		File filedir=new File(path);
		if(!filedir.exists()){
			filedir.mkdir();
		}	
		//System.out.println("path:"+path);
		//文件名
		String filename=tid+".txt";
		//System.out.println("filename:"+filename);
		File file = new File(path+"/"+filename);
		System.out.println("file:"+file);
		if(!file.exists()){
			file.createNewFile();
		}
		//File file=new File(uploadPath+"://"+tid+".txt");
		//String content = "new append!"; 		
		List<String> fileContent=new ArrayList<String>();
		fileContent=readFile02(path+"/"+filename);
		//System.out.println("fileContent"+fileContent);
		request.setAttribute("fileContent",fileContent);
		request.getRequestDispatcher("takeNotes.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
