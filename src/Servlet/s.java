package Servlet;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class s
 */
public class s extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public s() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		/*String filename=null;
        
        DiskFileItemFactory factory=new DiskFileItemFactory();    //磁盘文件条目工厂
        ServletFileUpload upload=new ServletFileUpload(factory); //负责处理上传的文件数据，并将表单中每个输入项封装成一个fileitem对象中
        //设置上传文件的大小为10M
        factory.setSizeThreshold(2*1024*1024);
        List items=null;
        
        try {
            //parse  解析
            items=upload.parseRequest(request);   //得到一个保存了所有上传内容的List对象
        } catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Iterator iter=items.iterator();   //迭代上传的文件数据
        while(iter.hasNext()){
            FileItem item=(FileItem) iter.next();
            if(!item.isFormField()){  //如果不是上传的
                //根据时间戳创建头像文件
                filename=System.currentTimeMillis()+".jpg";
                //通过getrealpath获取上传文件夹，如果项目存在将存在当前项目下  不存在的话创建项目文件夹
                //图片文件夹
                String photoFolder=this.getServletContext().getRealPath("uploaded");
                File f=new File(photoFolder,filename);
                f.getParentFile().mkdirs();  //如果父文件夹不存在则自动创建
                //通过item.getInputStream() 获取浏览器上传的文件
                InputStream is = item.getInputStream();   //将文件读进来
                //复制文件
                FileOutputStream fos=new FileOutputStream(f);  //往界面上显示
                byte[] b=new byte[2*1024*1024];
                int len=0;
                while((len=is.read(b))!=-1){
                    fos.write(b, 0, len);
                }
                fos.close();
                
            }else{
                System.out.println(item.getFieldName());//heroName
                String value=item.getString();   
                value=new String(value.getBytes("ISO-8859-1"), "UTF-8");
                System.out.println(value);   //桑葚
            }
        }
        String html="<img width='200' height='150' src='uploaded/%s'/>";
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();
        pw.format(html, filename);*/
		request.setCharacterEncoding("utf-8");
		try{
		 if(ServletFileUpload.isMultipartContent(request)) {

             DiskFileItemFactory dff=new DiskFileItemFactory();// 创建该对象

             dff.setSizeThreshold(1024000);// 指定在内存中缓存数据大小,单位为byte

             ServletFileUpload sfu=new ServletFileUpload(dff);// 创建该对象

             sfu.setFileSizeMax(5000000);// 指定单个上传文件的最大尺寸

             sfu.setSizeMax(10000000);// 指定一次上传多个文件的 总尺寸

             FileItemIterator fii=sfu.getItemIterator(request);// 解析request 请求,并返回FileItemIterator集合

             StringBuffer stringBuffer=new StringBuffer();

             while(fii.hasNext()) {

                 FileItemStream fis=fii.next();// 从集合中获得一个文件流

                 if(!fis.isFormField() && fis.getName().length() > 0) {// 过滤掉表单中非文件

                     BufferedInputStream in=new BufferedInputStream(fis.openStream());// 获得文件输入流

                     BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(in));

                     String lineTxt=null;

                     while((lineTxt=bufferedReader.readLine()) != null) {

                         System.out.println(lineTxt);

                         stringBuffer.append(lineTxt+",");

                     }

                    

                 }

             }

             response.getWriter().println(stringBuffer.toString());// 终于成功了

         }

     } catch(Exception e) {

         e.printStackTrace();

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
