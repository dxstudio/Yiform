import java.io.FileInputStream; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
  
/** 
 * Servlet implementation class ServletDownload 
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ServletDownload" }) 
public class ServletDownload extends HttpServlet { 
  private static final long serialVersionUID = 1L; 
      
  /** 
   * @see HttpServlet#HttpServlet() 
   */
  public ServletDownload() { 
    super(); 
    // TODO Auto-generated constructor stub 
  } 
  
  

  
  

 
  	public static String toChinese(String str){
  	    if(str==null)str="";
  	    
  	     try {
  	      str=new String(str.getBytes("UTF-8"),"ISO-8859-1");
  	     } catch (UnsupportedEncodingException e) {
  	      // TODO Auto-generated catch block
  	      str="";
  	      e.printStackTrace();
  	     }
  	    
  	    return str;
  	   }

  /** 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) 
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    // TODO Auto-generated method stub 
      
    //获得请求文件名 
    String filepath = request.getParameter("filepath"); 
    String filename = request.getParameter("filename"); 
    System.out.println("2222222222222222"+filepath); 
    System.out.println("111111111111111111111"+filename); 
      
   
    //读取文件 
    InputStream in = new FileInputStream(filepath); 
    OutputStream out = response.getOutputStream();
    
    
    
    //response.addHeader("Content-Disposition", "attachment; filename=" + encodingFileName(filename)); 
	//response.addHeader("content-length", Long.toString(filesize));   
    
    
    response.addHeader("Content-Disposition", "attachment; filename=" +toChinese(filename)+".docx"); 
    
    //写文件 
    int b; 
    while((b=in.read())!= -1) 
    { 
      out.write(b); 
    } 
      
    in.close(); 
    out.close(); 
  } 
  
  /** 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) 
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    // TODO Auto-generated method stub 
  } 
  
} 
