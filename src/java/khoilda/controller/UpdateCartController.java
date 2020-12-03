/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoilda.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoilda.blo.RoomBLO;
import khoilda.db.Booking;
import khoilda.db.Room;

/**
 *
 * @author User
 */
public class UpdateCartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            int cartQuantity = Integer.parseInt(request.getParameter("txtCartQty"));
            int roomId = Integer.parseInt(request.getParameter("txtId"));
            RoomBLO blo = new RoomBLO();
            Room room = blo.getRoomFromDB(roomId);
            HttpSession session = request.getSession();
            Booking cart = (Booking) session.getAttribute("CART");
            boolean valid = true;
            for(int i = 0; i < cart.getBookingDetailList().size(); i++){
                if(cart.getBookingDetailList().get(i).getRoomID().getRoomID() == roomId){
                    if(cartQuantity > room.getQuantity() || cartQuantity < 0){
                        valid = false;
                        request.setAttribute("NOTIFY", "Update Fail!!");
                    }
                }
            }
            if(valid){
                for(int i = 0; i < cart.getBookingDetailList().size(); i++){
                    if(cart.getBookingDetailList().get(i).getRoomID().getRoomID() == roomId){
                        cart.getBookingDetailList().get(i).setQuantity(cartQuantity);
                    }
                }
                session.setAttribute("CART", cart);
            }
        } catch (Exception e) {
            log("ERROR At UpdateCartController: "+e.getMessage());
        } finally{
            request.getRequestDispatcher("detailCart.jsp").forward(request, response);
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
