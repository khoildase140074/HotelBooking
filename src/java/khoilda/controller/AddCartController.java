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
import khoilda.blo.BookingBLO;
import khoilda.blo.RoomBLO;
import khoilda.db.Account;
import khoilda.db.Booking;
import khoilda.db.BookingDetail;
import khoilda.db.Room;

/**
 *
 * @author User
 */
public class AddCartController extends HttpServlet {

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
            int roomId = Integer.parseInt(request.getParameter("txtId"));
            int hotelID = Integer.parseInt(request.getParameter("txtHotelID"));
            HttpSession session = request.getSession();
            RoomBLO blo = new RoomBLO();
            Room room = blo.getRoomFromDB(roomId);
            Booking cart = (Booking)session.getAttribute("CART");
            Account acc = (Account)session.getAttribute("USER");
            if(cart == null){
                cart = new Booking();
            }
            boolean isFound = false;
            cart.setUserID(acc);
            for(int i = 0; i < cart.getBookingDetailList().size();i++){
                if(cart.getBookingDetailList().get(i).getRoomID().getRoomID() == roomId){
                    cart.getBookingDetailList().get(i).setQuantity(cart.getBookingDetailList().get(i).getQuantity() + 1);
                    isFound = true;
                }
            }
            if(!isFound){
                BookingDetail dt = new BookingDetail(1, 1, (int)room.getPrice(), room);
                cart.getBookingDetailList().add(dt);
            }
            int total = 0;
            for(int i = 0; i < cart.getBookingDetailList().size(); i++){
                total += (cart.getBookingDetailList().get(i).getQuantity() * cart.getBookingDetailList().get(i).getTotal());
            }
            cart.setTotal(total);
            session.setAttribute("CART", cart);
        } catch (Exception e) {
            log("Error At AddCartController: "+e.getMessage());
        } finally{
            request.getRequestDispatcher("RoomDetailController").forward(request, response);
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
