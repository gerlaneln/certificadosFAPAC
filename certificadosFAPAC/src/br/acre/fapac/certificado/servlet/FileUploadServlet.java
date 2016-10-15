package br.acre.fapac.certificado.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import br.acre.fapac.certificado.dao.AlunoDAO;



/**
 * A Java servlet that handles file upload from client.
 * 
 * @author www.codejava.net
 */
//@WebServlet
public class FileUploadServlet extends HttpServlet  {
  private static final long serialVersionUID = 1L;
  
  // location to store file uploaded
  private static final String UPLOAD_DIRECTORY = "upload";

  // upload settings
  private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;   // 3MB
  private static final int MAX_FILE_SIZE     = 1024 * 1024 * 1000; // 1GB
  private static final int MAX_REQUEST_SIZE  = 1024 * 1024 * 1000; // 1GB
  
   

  /**
   * Upon receiving file upload submission, parses the request to read
   * upload data and saves the file on disk.
   */
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    // checks if the request actually contains upload file
	  String filePath = "";
	  PrintWriter out = response.getWriter();
    if (!ServletFileUpload.isMultipartContent(request)) {
      // if not, we stop here
      PrintWriter writer = response.getWriter();
      writer.println("Error: Form must has enctype=multipart/form-data.");
      writer.flush();
      return;
    }
    
//    File diretorio = new File("C:\\testando/");
//     diretorio.mkdir();

    // configures upload settings
    DiskFileItemFactory factory = new DiskFileItemFactory();
    // sets memory threshold - beyond which files are stored in disk 
    factory.setSizeThreshold(MEMORY_THRESHOLD);
    // sets temporary location to store files
    factory.setRepository(new File (System.getProperty("java.io.tmpdir")));

    ServletFileUpload upload = new ServletFileUpload(factory);
    
    // sets maximum size of upload file
    upload.setFileSizeMax(MAX_FILE_SIZE);
    
    // sets maximum size of request (include file + form data)
    upload.setSizeMax(MAX_REQUEST_SIZE);

    // constructs the directory path to store upload file
    // this path is relative to application's directory
    String uploadPath = getServletContext().getRealPath("") +File.separator + UPLOAD_DIRECTORY;
    
    System.out.println(uploadPath);
    
    // creates the directory if it does not exist
    File uploadDir = new File(uploadPath);
    if (!uploadDir.exists()) {
      uploadDir.mkdir();
    }

    try {
      // parses the request's content to extract file data
      @SuppressWarnings("unchecked")
      List<FileItem> formItems = upload.parseRequest(request);

      if (formItems != null && formItems.size() > 0) {
        // iterates over form's fields
        for (FileItem item : formItems) {
          // processes only fields that are not form fields
          if (!item.isFormField()) {
            String fileName = new File(item.getName()).getName();
            filePath = uploadPath + File.separator + fileName;
            File storeFile = new File(filePath);

            // saves the file on disk
            item.write(storeFile);
          // request.setAttribute("message",
          // "Upload has been done successfully!");
            
            System.out.println(filePath);
            
          }
        }
      }
      
    } catch (Exception ex) {
      request.setAttribute("message",
         "There was an error: " + ex.getMessage());
  //	request.getRequestDispatcher("novaPlanilha.jsp").forward(request, response);
	
    }
 

    // redirects client to message page
    	//getServletContext().getRequestDispatcher("/message.jsp").forward(
      //  request, response);
    	AlunoDAO alunoDAO = new AlunoDAO();
    	File file = new File(filePath);
    	System.out.println(filePath);
    	
    	try {
    	alunoDAO.lerPlanila(file);
    	//response.sendRedirect("administrador.jsp");
    	} catch (Exception e) {
    		e.printStackTrace();    	 	
    	out.println("<script type=\"text/javascript\">");
    	out.println(" alert('Erro ao carregar planilha');");
    	out.println("</script>");		
    	}
    	
    	
    

    
  }
	
	
}
