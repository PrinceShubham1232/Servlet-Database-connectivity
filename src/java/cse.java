import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class cse extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String id = request.getParameter("id");
            String f_name = request.getParameter("f_name");
            String l_name = request.getParameter("l_name");
            String email = request.getParameter("email");
            String addr = request.getParameter("addr");
            String contact = request.getParameter("contact");
            String Submit = request.getParameter("Submit");
            
            //inserting data into database
            if (Submit.equals("Submit")) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1", "root", "");
                    PreparedStatement st = connect.prepareStatement("insert into tab1 values(?,?,?,?,?,?)");
                    st.setString(1, id);
                    st.setString(2, f_name);
                    st.setString(3, l_name);
                    st.setString(4, email);
                    st.setString(5, addr);
                    st.setString(6, contact);
                    int row = st.executeUpdate();
                    if (row == 1) {
                        out.println("Registration Successful");
                        RequestDispatcher rd = request.getRequestDispatcher("index.html");
                        rd.include(request, response);
                    } else {
                        out.println("Registration is not successful");
                        RequestDispatcher rd = request.getRequestDispatcher("index.html");
                        rd.include(request, response);
                    }
                } catch (Exception e) {
                  
                    out.println("Registration is not successful");
                    RequestDispatcher rd = request.getRequestDispatcher("index.html");
                    rd.include(request, response);
                }

            }
            if (Submit.equals("Delete")) {

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1", "root", "");
                    PreparedStatement st = connect.prepareStatement("DELETE FROM tab1 WHERE Stud_id=(?) ");
                    st.setString(1, id);
                    int row = st.executeUpdate();
                    if (row == 1) {
                        out.println("Data Deleted Successfully");
                        RequestDispatcher rd = request.getRequestDispatcher("index.html");
                        rd.include(request, response);
                    } else {
                        out.println("Data not deleted");
                        RequestDispatcher rd = request.getRequestDispatcher("index.html");
                        rd.include(request, response);
                    }
                } catch (Exception e) {
                    out.println(e);
                    out.println("Data not deleted");
                    RequestDispatcher rd = request.getRequestDispatcher("index.html");
                    rd.include(request, response);

                }
            }
            if (Submit.equals("Update")) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1", "root", "");
                    PreparedStatement st = connect.prepareStatement("UPDATE tab1 SET F_name=(?), L_name=(?), Email=(?), Address=(?), Phone=(?) WHERE Stud_id=(?)");
                    st.setString(1, f_name);
                    st.setString(2, l_name);
                    st.setString(3, email);
                    st.setString(4, addr);
                    st.setString(5, contact);
                    st.setString(6, id);
                    int row = st.executeUpdate();              
                    if (row == 1) {
                        out.println("Data Updated Successfully");
                        RequestDispatcher rd = request.getRequestDispatcher("index.html");
                        rd.include(request, response);
                    } else {
                        out.println("Data not Updated");
                        RequestDispatcher rd = request.getRequestDispatcher("index.html");
                        rd.include(request, response);
                    }

                } catch (Exception e) {

                    out.println(e);
                    out.println("Data not Updated");
                    RequestDispatcher rd = request.getRequestDispatcher("index.html");
                    rd.include(request, response);
                }
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
