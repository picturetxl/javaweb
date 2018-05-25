package tailiang;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * DownLoadServlet
extends HttpServlet */
public class DownLoadServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String filename=request.getParameter("filename");
        String dataDirector="D:\\webpage\\download";
        File file=new File(dataDirector,filename);
        if(file.exists())
        {
            response.setContentType("application/zip");
            response.addHeader("Content-Disposition", "attachment;filename="+filename);
            byte[]buffer=new byte[1024];
            FileInputStream fileInputStream=null;
            BufferedInputStream bufferedInputStream=null;
            try {
                fileInputStream=new FileInputStream(file);
                bufferedInputStream=new BufferedInputStream(fileInputStream);
                OutputStream outputStream=response.getOutputStream();
                int i=bufferedInputStream.read(buffer);
                while(i!=-1)
                {
                    outputStream.write(buffer,0,i);
                    i=bufferedInputStream.read(buffer);
                }
            } catch (IOException e) {
                System.out.println(e.toString());
            }finally {
                if(bufferedInputStream!=null)
                    bufferedInputStream.close();
                if(fileInputStream!=null)
                    fileInputStream.close();
            }
        }

        }
}