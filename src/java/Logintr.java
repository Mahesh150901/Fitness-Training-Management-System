import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Logintr extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();
        String Username=request.getParameter("User");
        String Password=request.getParameter("Password");
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/fitness","root","");
            PreparedStatement pst=con.prepareStatement("Select Password from employee where Username=?");
            pst.setString(1,Username);
            ResultSet rs=pst.executeQuery();
            rs.next();
            String pass=rs.getString("Password");
            con.close();
            if(Password.equals(pass))
            {
                RequestDispatcher rd=request.getRequestDispatcher("Dashboardtr.html");
                rd.forward(request, response);
                pw.println("Successfully logged in");
            }
            else
            {
                pw.print("<center><h1 Style='color:beige'>Invalid Credentials<h1><center>");
                RequestDispatcher rdf=request.getRequestDispatcher("Logintr.html");
                rdf.include(request,response);
            }
        }
        catch(Exception e)
        {
            pw.println(e.getMessage());
        }
    }
}