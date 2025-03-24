import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedProduct = request.getParameter("product");

        HttpSession session = request.getSession();
        ArrayList<String> cart;

        Object cartObj = session.getAttribute("cart");
        if (cartObj instanceof ArrayList<?>) {
            cart = (ArrayList<String>) cartObj;
        } else {
            cart = new ArrayList<>();
        }

        if (selectedProduct != null) {
            cart.add(selectedProduct);
            session.setAttribute("cart", cart);
        }

        response.sendRedirect("pages/cart.jsp");
    }
}
