import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class customer extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();
        RequestDispatcher rp=request.getRequestDispatcher("Dashboardtr.html");
        rp.include(request,response);
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/fitness","root","");
            PreparedStatement pst=con.prepareStatement("Select * from customer");
            ResultSet rs=pst.executeQuery();
            pw.println("<h3 style='text-align:center;color:red;font-family:TimesNewRoman;font-size:60px;'>List of Customers</h3>");
            pw.println("<center><table border=2px style='color:blue;font-family:TimesNewRoman;font-size:30px;border-collapse:collapse; padding:15px'><tr><th style='padding:15px'>Name</th><th style='padding:15px'>Email</th><th style='padding:15px'>Phone</th></tr>");
            while(rs.next())
            {
                String name=rs.getString("Name");
                String email=rs.getString("Email");
                String phone=rs.getString("Phone");
                pw.println("<tr><td style='padding:15px'>"+name+"</td><td style='padding:15px'>"+email+"</td><td style='padding:15px'>"+phone+"</td></tr>");
            }
            pw.println("</table></center>");
            con.close();
        }
        catch(Exception e)
        {
            pw.println(e.getMessage());
        }
    }

}
