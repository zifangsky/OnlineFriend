package com.zifangsky.OnlineFriend.servlet.member;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.zifangsky.OnlineFriend.model.member.Login;
import com.zifangsky.OnlineFriend.model.member.UploadFile;
import com.zifangsky.OnlineFriend.util.DbConn;
import com.zifangsky.OnlineFriend.util.StringUtil;

public class HandleUploadImage extends HttpServlet{
	
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{		
		HttpSession session = request.getSession(true);
		Login loginBean = (Login) session.getAttribute("login");
		
		if(loginBean == null){
			response.sendRedirect("login.jsp");
			return;
		}
		else
			uploadImage(request,response);
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doPost(request, response);
	}

	private void uploadImage(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		Login loginBean = (Login) session.getAttribute("login");
		
		String id = loginBean.getId();
		UploadFile uploadFileBean = new UploadFile();
		request.setAttribute("userImage", uploadFileBean);
			
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);  //判断提交的表单是否是文件上传表单
		String savedFileName = "";  //最后保存的文件名
		String backNews = "";
		if(isMultipart){
			FileItemFactory fileItemFactory = new DiskFileItemFactory();  //获得磁盘文件条目工厂 
			ServletFileUpload upload = new ServletFileUpload(fileItemFactory);  //高水平的API文件上传处理  
			try {
				FileItem item =  (FileItem) upload.parseRequest(request).get(0);  //只有一个上传文件，取第一个即可

//				String path1 = request.getRealPath("/data/userfile/image");  //此方法已经过时
//				String path1 = session.getServletContext().getRealPath("/data/userfile/image");  //也可以这样写
				String path1 = this.getServletContext().getRealPath("/data/userfile/image");  //文件保存路径

				
				if(!item.isFormField()){
					String value = StringUtil.xssEncode(item.getName());  //获取上传文件的文件名
					int start = value.lastIndexOf("\\");
					String fileName = value.substring(start+1);  //文件名
					String filetype = "jpg";
					//过滤文件格式
					if (fileName.length() > 0) {   
			            int start1 = fileName.lastIndexOf('.');   
			            if ((start1 >-1) && (start1 < (fileName.length() - 1))) {  
			                String fileTemptype = fileName.substring(start1 + 1);   
			                if("jpg".equals(fileTemptype) || "jpeg".equals(fileTemptype) || "png".equals(fileTemptype))
			                	filetype = fileTemptype;	                	
			            }   
					}
														
					//重命名文件
					String fileName1 = StringUtil.getNewFileNameString(5)+"."+filetype;	
					savedFileName = fileName1;
					item.write(new File(path1,fileName1));
/*						
					//保存文件，手动写
					OutputStream out = new FileOutputStream(new File(path1,fileName1));                   
                    InputStream in = item.getInputStream() ;                        
                    int temp = 0 ;  
                    byte [] buf = new byte[1024] ;  
                      
                    System.out.println("获取上传文件的总共的容量："+item.getSize());  
  
                    // in.read(buf) 每次读到的数据存放在   buf 数组中  
                    while((temp = in.read(buf)) != -1)  
                    {  
                        //在   buf 数组中 取出数据 写到 （输出流）磁盘上  
                        out.write(buf, 0, temp);                         
                    }                       
                    in.close();  
                    out.close();  
*/					
				}	
				
				backNews = "图像上传成功";
				uploadFileBean.setUploadFileOk(true);
				uploadFileBean.setSavedFileName(savedFileName);
			} catch (Exception e) {
				backNews = "图像上传失败";	
			}
		}
		
		//更新数据库
		Connection connection = DbConn.getConnection();
		try {
			PreparedStatement pStatement = connection.prepareStatement("update member set pic=? where id=?");
			pStatement.setString(1, savedFileName);
			pStatement.setString(2, id);
			
			int num = pStatement.executeUpdate();
			if(num == 1)
				backNews = backNews + "，更新数据库成功";
			else
				backNews = backNews + "，更新数据库失败";	
			connection.close();
		} catch (SQLException e) {
			backNews = backNews + "，更新数据库失败";	
		}
		uploadFileBean.setBackNews(backNews);
				
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/showUploadImageMess.jsp");
		dispatcher.forward(request, response);
	} 
}
