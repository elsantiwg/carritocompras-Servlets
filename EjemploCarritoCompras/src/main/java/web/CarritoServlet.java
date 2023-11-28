package web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/CarritoServlet")
public class CarritoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");

        //creamos o recuperamos el objeto http sesion
        HttpSession sesion = request.getSession();

        //recuperamos la lista de articulos agregados previamente si es que existen
        List<String> articulos = (List<String>) sesion.getAttribute("articulos");

        //verificamos si la lista de articulos existe
        if (articulos == null) {
            //inicializamos la lista de articulos
            articulos = new ArrayList<>();
            sesion.setAttribute("articulos", articulos);
        }

        //procesamos el nuevo articulo
        String articuloNuevo = request.getParameter("articulo");

        //revisamos y agregamos el articulo nuevo
        if (articuloNuevo != null && !articuloNuevo.trim().equals("")) {
            articulos.add(articuloNuevo);
        }

        try (
                //imprimir la lista de articulos
                PrintWriter out = response.getWriter()) {
            out.print("<h1>lista de articulos</h1>");
            out.print("<br>");
            //iteramos todos los articulos
            for (String articulo : articulos) {
                out.print("<li>" + articulo + "</li>");
            }
            //agregamos un link para regresar al inicio
            out.print("<br>");
            out.print("<a href='/EjemploCarritoCompras'>regresar al inicio</a>");
        }
    }
}
