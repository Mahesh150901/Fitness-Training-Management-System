import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Forgettr extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();
        String User=request.getParameter("User");
        String mail=request.getParameter("Email");
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/fitness","root","");
            PreparedStatement pst=con.prepareStatement("select Password from employee where Username=? and Email=?");
            pst.setString(1,User);
            pst.setString(2,mail);
            ResultSet rs=pst.executeQuery();
            if(rs.next()==false)
            {
                pw.println("<h3 style='font-size:30px'>Incorrect Credentials</h3>");
            }
            else
            {
                String Password=rs.getString("Password");
                pw.println("<h3 style='font-size:30px'>Password has Sent to your registered mail</h3>");
                String Subject="Password Recover";
                String message=request.getParameter("Your Login Password is "+Password);
                String Usermail="abczyx1509@gmail.com";
                String passwordmail="abcxyz1509*";
                Properties prop=new Properties();
                prop.put("mail.smtp.host","smtp.gmail.com");
                prop.put("mail.smtp.auth","true");
                prop.put("mail.user",User);
                prop.put("mail.password",passwordmail);
                prop.put("mail.port",465);
                prop.put("mail.smtp.starttls.enable","true");
                Session ses=Session.getInstance(prop, new javax.mail.Authenticator()
                        {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication()
                            {
                                return new PasswordAuthentication(Usermail,passwordmail);
                            }
                        });
                try
                {
                    MimeMessage me=new MimeMessage(ses);
                    me.setFrom(new InternetAddress(Usermail));
                    me.addRecipient(Message.RecipientType.TO,new InternetAddress(mail));
                    me.setSubject(Subject);
                    me.setText(message);
                    Transport.send(me);
                }
                catch(Exception e)
                {
                    pw.println(e.getMessage());
                }
            }
            RequestDispatcher rd=request.getRequestDispatcher("Forgettr.html");
            rd.include(request,response);
        }
        catch(Exception e)
        {
            pw.println(e.getMessage());
        }
    }
}