package Servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

/**
 * Servlet implementation class readSyllabusServlet
 */
public class readSyllabusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public readSyllabusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    //读取文件内容
    public static List<String> getPhoneNum(File filePath) {
        String text = null;
        String fileName = filePath.getName().toLowerCase();// 得到名字小写
        String[] paraTexts=null;
        List<String> filecontent=new ArrayList<String>();
        try {    
            FileInputStream in = new FileInputStream(filePath);
            if (fileName.endsWith(".doc")) { // doc为后缀的
            	//HWPFDocument doc = new HWPFDocument(is);
                WordExtractor extractor = new WordExtractor(in);
             /*   //获取各个段落的文本
                paraTexts= extractor.getParagraphText();
                for(String s:paraTexts){
                	filecontent.add(s);
                }*/
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

        //return text;
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
		int tid=(Integer) session.getAttribute("Tid");
		//上传文件保存的路径
		String uploadPath=this.getServletContext().getRealPath("./")+File.separator+"syllabus"+File.separator+tid+File.separator+"教学大纲";
		File file=new File(uploadPath+".doc");
		if(!file.exists()){
			file=new File(uploadPath+".docx");
		}
		//System.out.println(file);
		List<String> fileContent=new ArrayList<String>();
		fileContent=getPhoneNum(file);
		//System.out.println("教学大纲："+fileContent);
		request.setAttribute("fileContent", fileContent);
		request.getRequestDispatcher("syllabus.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
