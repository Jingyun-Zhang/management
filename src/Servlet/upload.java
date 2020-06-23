package Servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

/**
 * Servlet implementation class upload
 */
public class upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//1.创建工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        File f = new File("I:\\target");
        if(!f.exists()){
            f.mkdirs();
        }
    //2.设置文件的缓存路径
        factory.setRepository(f);
    //3.创建fileUpload组件
        ServletFileUpload fileupload = new ServletFileUpload(factory);
        fileupload.setHeaderEncoding("utf-8");
        //获取系统字符编码       
		try {
			//4.解析reqeust
			List<FileItem> fileItems= fileupload.parseRequest(request);
			PrintWriter pw = response.getWriter();
			//5.遍历集合
	        for(FileItem fileItem:fileItems){
	            //6.判断是否为普通字段
	            if(fileItem.isFormField()){
	                //获得字段名和字段值
	                String name = fileItem.getFieldName();
	                String value = fileItem.getString("utf-8");
	                pw.print("上传者"+value+"<br>");
	            }else{
	                //上传的文件路径 根据浏览器的不同会有区别
	                String fileName = fileItem.getName();
	                pw.println("文件来源"+fileName+"<br>");
	                //截取出文件名
	                fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
	                pw.print("成功上传文件："+fileName+"<br>");
	                 
	                //文件名唯一
	                fileName = UUID.randomUUID().toString().replace("-", "")+"_"+fileName;
	                 
	                //在服务器创建同名文件
	                String webPath = "/upload/";
	                //分离目录
	                String hexString =Integer.toHexString(fileName.hashCode());
	                String path = hexString.charAt(0) + "/" + hexString.charAt(1);
	                String filePath = this.getServletContext().getRealPath(webPath+path+fileName);
	                 
	                 
	                System.out.println(filePath);
	                //创建文件
	                File file = new File(filePath);
	                file.getParentFile().mkdirs();
	                file.createNewFile();
	                 
	                //获得上传文件流
	                InputStream in = fileItem.getInputStream();
	                //获得写入文件流
	                OutputStream out = new FileOutputStream(file);
	                //流的对拷
	                IOUtils.copy(in,out);
	                //释放资源
	                IOUtils.closeQuietly(in);
	                IOUtils.closeQuietly(out);
	                 
	                fileItem.delete();
	            }
	        }
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        PrintWriter pw = response.getWriter();       

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
