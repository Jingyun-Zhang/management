package Servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * Servlet implementation class sSyllabusServlet
 */
public class sSyllabusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sSyllabusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    //读取文件内容
    public static List<String> getPhoneNum(File filePath) {
        String text = null;
        String fileName = filePath.getName().toLowerCase();// 得到名字小写
        List<String> filecontent=new ArrayList<String>();
        try {
            FileInputStream in = new FileInputStream(filePath);
            if (fileName.endsWith(".doc")) { // doc为后缀的
                WordExtractor extractor = new WordExtractor(in);
                text = extractor.getText();
                if(text.length() > 0){
					//使用回车换行符分割字符串
					String [] arry = text.split("\\r\\n");
					for (String string : arry) {
						filecontent.add(string.trim());
					}
                }
            }
            if (fileName.endsWith(".docx")) { // docx为后缀的
         
                XWPFWordExtractor docx = new XWPFWordExtractor(new XWPFDocument(in));
                text = docx.getText();
                if(text.length() > 0){
 					//使用换行符分割字符串
 					String [] arry = text.split("\\n");
 					for (String string : arry) {
 						filecontent.add(string.trim());
 					}
 				}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return filecontent;
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
		int tid=(Integer) session.getAttribute("tid");
		List<String> fileContent=new ArrayList<String>();
		//路径,相对当前应用的目录
		String uploadPath=this.getServletContext().getRealPath("./")+File.separator+"syllabus"+File.separator+tid;
		//判断文件是否存在
		File file=new File(uploadPath+File.separator+"教学大纲.doc");
		if(!file.exists()){
			file=new File(uploadPath+File.separator+"教学大纲.docx");
		}
		/*if(!file.exists()){
			fileContent="教学大纲未上传";
		}*/
		
		fileContent=getPhoneNum(file);
		request.setAttribute("fileContent", fileContent);
		request.getRequestDispatcher("sSyllabus.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
