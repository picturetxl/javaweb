package tailiang;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@MultipartConfig
public class MultipleUploadsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String getFilename(Part part)
	{
		String contentDispositionHeader=part.getHeader("content-disposition");
		System.out.println(contentDispositionHeader);//form-data; name="filename"; filename="load.txt"
		String[] elements=contentDispositionHeader.split(";");
		for (String element : elements) {
			if(element.trim().startsWith("filename")){// trim返回字符串的副本，忽略前导空白和尾部空白。
				return element.substring(element.indexOf("=") +1).trim().replace("\"", "");
			}
		}
		return null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer=response.getWriter();
		Collection<Part> parts =request.getParts();
		for(Part part :parts)
		{
			if(part.getContentType()!=null)
			{
				String fileName=getFilename(part);
				if(fileName!=null && !fileName.isEmpty() && fileName.endsWith(".zip"))
				{
					part.write("D:\\webpage\\download"+"/"+fileName);
					// writer.print("<br/>name: "+fileName);
					// writer.print("<br/>Size: "+part.getSize());
					writer.flush();
					writer.println("<script>");
					writer.println("alert('name:"+fileName+" size:"+part.getSize()+"')");
					writer.println("history.back();");
					writer.println("</script>");
				}
				else{
					// String partname=part.getName();
					// String filedvalue=request.getParameter(partname);
					// writer.print("<br/>"+partname+":"+filedvalue);
					writer.flush();
					writer.println("<script>");
					writer.println("alert('failed because you upload a file which is not a zip style');");
					writer.println("history.back();");
					writer.println("</script>");
				}
			}
		}
    }

}
