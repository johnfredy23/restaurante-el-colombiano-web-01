package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegistroClienteServlet")
public class RegistroClienteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nom = request.getParameter("nombre");
        String ape = request.getParameter("apellido");
        String doc = request.getParameter("documento");
        String cor = request.getParameter("correo");
        String tel = request.getParameter("telefono");
        String fecha = request.getParameter("fechaNacimiento");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><body>");
        
        if (cor == null || !cor.contains("@")) {
            out.println("<h2>Error de validacion</h2>");
            out.println("<p style='color:red;'>Error: El correo debe contener el simbolo @</p>");
            out.println("<br><a href='registrocliente.html'>Volver al formulario</a>");
            out.println("</body></html>");
            return;
        }
        
        out.println("<h2>Cliente Registrado</h2>");
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/elcolombiano_restaurante?useSSL=false&serverTimezone=UTC",
                "root",
                "1234"
            );
            
            String sql = "INSERT INTO cliente (nombre, apellidos, documento, correo, telefono, fechaNacimiento) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nom);
            pstmt.setString(2, ape);
            pstmt.setString(3, doc);
            pstmt.setString(4, cor);
            pstmt.setString(5, tel);
            pstmt.setString(6, fecha);
            
            int resultado = pstmt.executeUpdate();
            
            if (resultado > 0) {
                out.println("<p style='color:green;'>Cliente registrado con exito</p>");
                out.println("<p>Nombre: " + nom + " " + ape + "</p>");
                out.println("<p>Documento: " + doc + "</p>");
                out.println("<p>Correo: " + cor + "</p>");
                out.println("<p>Telefono: " + tel + "</p>");
                out.println("<p>Fecha de nacimiento: " + fecha + "</p>");
            } else {
                out.println("<p style='color:red;'>Error: No se pudo registrar el cliente</p>");
            }
            
        } catch (Exception e) {
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        
        out.println("<br><a href='registrocliente.html'>Volver al formulario</a>");
        out.println("</body></html>");
    }
}