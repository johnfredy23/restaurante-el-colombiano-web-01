package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PruebaProductoServlet")
public class PruebaProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<html><head><title>Menú del Restaurante</title>");
        out.println("<style>");
        out.println("table { border-collapse: collapse; width: 80%; margin: auto; }");
        out.println("th, td { border: 1px solid black; padding: 8px; text-align: left; }");
        out.println("th { background-color: #055C8A; color: white; }");
        out.println("</style></head><body>");
        out.println("<h1 style='text-align:center;'>Productos del Restaurante El Colombiano</h1>");
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/elcolombiano_restaurante?useSSL=false&serverTimezone=UTC",
                "root",
                "1234"
            );
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT id, nombre, precio, descripcion FROM producto");
            
            out.println("<table>");
            out.println("<tr><th>ID</th><th>Nombre</th><th>Precio</th><th>Descripción</th></tr>");
            
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("nombre") + "</td>");
                out.println("<td>$" + rs.getDouble("precio") + "</td>");
                out.println("<td>" + rs.getString("descripcion") + "</td>");
                out.println("</tr>");
            }
           out.println("</table>");
            
        } catch (Exception e) {
            out.println("<p style='color:red;'>Error al cargar productos: " + e.getMessage() + "</p>");
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        
        out.println("<br><p style='text-align:center;'><a href='index.html'>Volver al inicio</a></p>");
        out.println("</body></html>");
    }
}