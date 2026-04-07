package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PruebaConexionServlet")
public class PruebaConexionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<html><body>");
        out.println("<h2>Prueba de Conexión a MySQL</h2>");
        
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Conectar a la base de datos
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/elcolombiano_restaurante?useSSL=false&serverTimezone=UTC",
                "root",
                "1234"
            );
            
            out.println("<p style='color:green;'>✅ Conexión exitosa a la base de datos</p>");
            conn.close();
            
        } catch (Exception e) {
            out.println("<p style='color:red;'>❌ Error: " + e.getMessage() + "</p>");
        }
        
        out.println("<br><a href='index.html'>Volver al inicio</a>");
        out.println("</body></html>");
    }
}