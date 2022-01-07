import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Classlistcus extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();
        RequestDispatcher rd=request.getRequestDispatcher("Dashboardcus.html");
        rd.include(request,response);
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/fitness","root","");
            PreparedStatement pst=con.prepareStatement("select * from class");
            ResultSet rs=pst.executeQuery();
            pw.println("<h3 style='font-size:30px;color:darkgreen;text-align:center'>List of Scheduled Classes</h3>");
            pw.println("<center><table border=2px style='color:darkgreen;font-size:30px;border-collapse:collapse;padding:15px'><tr><th style='padding:15px'>Name</th><th style='padding:15px'>Class Link</th><th style='padding:15px'>Date</th><th style='padding:15px'>Time</th></tr>");
            while(rs.next())
            {
                String Name=rs.getString("Name");
                String Link=rs.getString("Link");
                String Date=rs.getString("Date");
                String Time=rs.getString("Time");
                pw.println("<tr><td style='padding:15px'>"+Name+"</td><td style='padding:15px'>"+Link+"</td><td style='padding:15px'>"+Date+"</td><td style='padding:15px'>"+Time+"</td></tr>");
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
