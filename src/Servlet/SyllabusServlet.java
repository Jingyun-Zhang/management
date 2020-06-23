package Servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class SyllabusServlet
 */
public class SyllabusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//上传配置
	private static final int MEMORY_THRESHOLD=1024*1024*3;//3M
	private static final int MAX_FILE_SIZE=1024*1024*40;//40M
	private static final int MAX_REQUEST_SIZE=1024*1024*50;//50M  
    //上传文件存储目录
    //private static final String UPLOAD_DIRECTORY="UploadResourceServlet";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SyllabusServlet() {
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
		//获取教师id
		int tid=(Integer) session.getAttribute("Tid");
		String fileName=null;
		String filePath=null;
		//检测是否为多媒体上传
		if(!ServletFileUpload.isMultipartContent(request)){
			//如果不是则停止
			PrintWriter writer=response.getWriter();
			writer.println("Error:表单必须包含enctype=multipart/form-data");
			writer.flush();
			return;
		}
		//配置上传参数
		DiskFileItemFactory factory=new DiskFileItemFactory();
		//设置内存临界值-超过后将产生临时文件并存储于临时目录中
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		//设置临时存储目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		ServletFileUpload upload=new ServletFileUpload(factory);
		//设置最大文件上传值
		upload.setFileSizeMax(MAX_FILE_SIZE);
		//设置最大请求值（包含文件和表单数据）
		upload.setSizeMax(MAX_REQUEST_SIZE);
		//中文处理
		upload.setHeaderEncoding("utf-8");
		//构造临时路径来存储上传的文件
		//这个路径相对当前应用的目录
		String uploadPath=this.getServletContext().getRealPath("./")+File.separator+"syllabus"+File.separator+tid;
		//如果目录不存在则创建
		File uploadDir=new File(uploadPath);
		if(!uploadDir.exists()){
			uploadDir.mkdirs();
		}
		/*BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(uploadPath), "UTF-8"));*/
		try{
			//解析请求的内容提取文件数据
			@SuppressWarnings("unchecked")
			List<FileItem> formItems=upload.parseRequest(request);
			if(formItems!=null && formItems.size()>0){
				//迭代表单数据
				for(FileItem item:formItems){
					//处理不在表单中的字段
					if(!item.isFormField()){
						fileName=new File(item.getName()).getName();
						//获取文件后缀名
						 String fileTyle=fileName.substring(fileName.lastIndexOf("."),fileName.length()); 
						filePath=uploadPath+File.separator+"教学大纲"+fileTyle;
						File storeFile=new File(filePath);
						//在控制台输出文件的上传路径
						//System.out.println(filePath);
						//保存文件到硬盘
						item.write(storeFile);
						request.setAttribute("fileName", fileName);
					}
				}
			}
		}catch(Exception ex){
			//request.setAttribute("fileName", "错误信息"+ex.getMessage());
		}
		//跳转到message.jsp
		//response.sendRedirect("readSyllabusServlet");
		request.getRequestDispatcher("readSyllabusServlet").forward(request,response);
	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
