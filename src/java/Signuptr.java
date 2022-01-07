import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Signuptr extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
     response.setContentType("text/html");
     PrintWriter pw=response.getWriter();
     String Name=request.getParameter("Name");
     String Email=request.getParameter("Email");
     String Phone=request.getParameter("Phone");
     String Username=request.getParameter("User");
     String Password=request.getParameter("Password");
     try
     {
         Class.forName("com.mysql.jdbc.Driver");
         Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/fitness","root","");
         PreparedStatement pst=con.prepareStatement("insert into employee values(?,?,?,?,?)");
         pst.setString(1,Name);
         pst.setString(2,Email);
         pst.setString(3,Phone);
         pst.setString(4,Username);
         pst.setString(5,Password);
         int i=pst.executeUpdate();
         con.close();
         if(i>0)
         {
             pw.println("<h1 style='color:red'>Successfully Registered Please login to account<h1>");
             String Subject="Fitness";
             String message="You have successfully joined in fitness";
             Properties pro=new Properties();
             String User="abczyx1509@gmail.com";
             String password="abcxyz1509*";
             pro.put("mail.smtp.host","smtp.gmail.com");
             pro.put("mail.smtp.auth","true");
             pro.put("mail.user",User);
             pro.put("mail.password",password);
             pro.put("mail.port",465);
             pro.put("mail.smtp.starttls.enable","true");
             Session ses=Session.getInstance(pro,new javax.mail.Authenticator() 
             {
                 protected PasswordAuthentication getPasswordAuthentication()
                 {
                    return  new PasswordAuthentication(User,password);
                 }
             });
             try
             {
                 MimeMessage mim=new MimeMessage(ses);
                 mim.setFrom(new InternetAddress(User));
                 mim.addRecipient(Message.RecipientType.TO,new InternetAddress(Email));
                 mim.setSubject(Subject);
                 mim.setText(message);
                 Transport.send(mim);
             }
             catch(Exception e)
             {
                 pw.println(e.getMessage());
             }
         }
         else
         {
             pw.println("Incorrect Details Registration failed");
         }
     }
     catch(Exception e)
     {
         pw.println(e.getMessage());
     }
    }
}
