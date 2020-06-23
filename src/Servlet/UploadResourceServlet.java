package Servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Dao.ClassesDao;
import Dao.ClassesDaoImpl;
import Dao.ResourceDao;
import Dao.ResourceDaoImpl;
import util.DBTool;
import util.MutiFileUpload;
import vo.resource;

/**
 * Servlet implementation class UploadResourceServlet
 */
//@MultipartConfig
public class UploadResourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static String TEMP_FOLDER="UploadResourceServlet";
	//上传配置
	private static final int MEMORY_THRESHOLD=1024*1024*3;//3M
	private static final int MAX_FILE_SIZE=1024*1024*40;//40M
    private static final int MAX_REQUEST_SIZE=1024*1024*50;//50M
    //private String filePath;//存放上传文件的目录
    //private String tempFilePath;//存放临时文件的目录
    //上传文件存储目录
//    private static final String UPLOAD_DIRECTORY="UploadResourceServlet";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadResourceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理乱码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//创建会话
		HttpSession session=request.getSession(false);
		//获取教id
		int tid=(Integer) session.getAttribute("Tid");
		//获取教师姓名
		String trname=(String) session.getAttribute("Tname");
		//创建变量
		String rclass=null;//班号
		String fileName=null;
		long size = 0;
		//创建JavaBean对象（resource）
		resource res=new resource();
		//创建ResourceDao对象
		ResourceDao rdi=new ResourceDaoImpl();
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
		String uploadPath= this.getServletContext().getRealPath("./")+File.separator+"Upload"+File.separator+tid;
		//如果目录不存在则创建
		File uploadDir=new File(uploadPath);
		if(!uploadDir.exists()){
			uploadDir.mkdir();
		}
		try{
			//解析请求的内容提取文件数据
			@SuppressWarnings("unchecked")
			List<FileItem> formItems=upload.parseRequest(request);	
			if(formItems!=null && formItems.size()>0){
				//迭代表单数据
				for(FileItem item:formItems){
					//处理不在表单中的字段
					if(!item.isFormField()){
						size=item.getSize();						
						//System.out.println("大小："+readableFileSize(size));
						fileName=new File(item.getName()).getName();
						//System.out.println("文件名："+fileName);
						
						String filePath=uploadPath+File.separator+fileName;
						File storeFile=new File(filePath);
						//在控制台输出文件的上传路径
						//System.out.println(filePath);
						//保存文件到硬盘
						item.write(storeFile);
						//request.setAttribute("uploadmsg", "文件上传成功");	
					}else{
						//普通文件
						rclass=item.getString();
						//System.out.println("班号:"+rclass);
					}
					//获取上传的时间
					Date upDate=new Date();
					SimpleDateFormat stf = new SimpleDateFormat("yyyy-MM-dd");
					String dateNow=stf.format(upDate);
					Date dateNow2 = stf.parse(dateNow);
					Long Ndate = dateNow2.getTime();
					//System.out.println(stf.format(upDate));
					res.setRname(fileName);
					res.setTrid(tid);						
					res.setTrname(trname);
					res.setRdate(new java.sql.Date(Ndate));
					res.setRsize(readableFileSize(size));
					res.setRclass(rclass);
					//System.out.println("班号1111"+rclass);
					//System.out.println("班号2222"+res.getRclass());
				    rdi.insert(res);
				}
			}
		}catch(Exception ex){
			request.setAttribute("uploadmsg", "错误信息"+ex.getMessage());
		}
		//跳转到uploadResource.jsp
		request.getRequestDispatcher("ShowResourceServlet").forward(request,response);
	} 
        
	public static String readableFileSize(long size) {
		if (size <= 0) return "0"; 
		final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"}; 
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024)); 
		return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}    
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
