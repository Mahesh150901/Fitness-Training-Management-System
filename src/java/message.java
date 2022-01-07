import java.util.*;
import java.lang.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class message extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();
        String To=request.getParameter("To");
        String Subject=request.getParameter("Subject");
        String message=request.getParameter("Message");
        String User="abczyx1509@gmail.com";
        String password="abcxyz1509*";
        Properties prop=new Properties();
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.auth","true");
        prop.put("mail.user",User);
        prop.put("mail.password",password);
        prop.put("mail.port",465);
        prop.put("mail.smtp.starttls.enable","true");
        Session ses=Session.getInstance(prop, new javax.mail.Authenticator()
                {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(User,password);
                    }
                });
        try
        {
            MimeMessage me=new MimeMessage(ses);
            me.setFrom(new InternetAddress(User));
            me.addRecipient(Message.RecipientType.TO,new InternetAddress(To));
            me.setSubject(Subject);
            me.setText(message);
            Transport.send(me);
            RequestDispatcher rd=request.getRequestDispatcher("Mail.html");
            rd.include(request,response);
            pw.println("<h3 style='text-align:center;Color:red;font-size:60px'>Successfully sent a message</h3>");
        }
        catch(Exception e)
        {
            pw.println(e.getMessage());
        }
    }
}
