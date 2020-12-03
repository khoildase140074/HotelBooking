/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoilda.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoilda.blo.BookingBLO;
import khoilda.db.Account;
import khoilda.db.Booking;
import khoilda.db.Room;
import khoilda.db.Status;

/**
 *
 * @author User
 */
public class AddToDataBaseController extends HttpServlet {
    private static final String SUCCESS = "SearchController";
    private static final String FAIL = "verify.jsp";

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
        String url = FAIL;
        try {
            String code = request.getParameter("txtCode");
            String checkIn = request.getParameter("txtCheckIn");
            String checkOut = request.getParameter("txtCheckOut");
            String checkCode = request.getParameter("txtCheckCode");
            LocalDateTime myDate = LocalDateTime.now();
            DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            String date = myDate.format(myFormat);
            HttpSession session = request.getSession();
            Booking cart = (Booking)session.getAttribute("CART");
            Account acc = (Account) session.getAttribute("USER");
            int status = 1;
            BookingBLO blo = new BookingBLO();
            int bookingID = blo.getBookingID();
            int total = Integer.parseInt(request.getParameter("txtTotal"));
            if(code.equals(checkCode)){
                if(blo.addToDB(bookingID, total, date, checkIn, checkOut, new Account(acc.getUserID()), new Status(status))){
                    for(int i = 0; i < cart.getBookingDetailList().size(); i++){
                        int bookingDetailID = blo.getBookingDetailID();
                        int quantity = cart.getBookingDetailList().get(i).getQuantity();
                        int room = cart.getBookingDetailList().get(i).getRoomID().getRoomID();
                        blo.addDetailToDB(bookingDetailID, quantity, total, new Booking(bookingID), new Room(room));
                    }
                    url = SUCCESS;
                    session.removeAttribute("CART");
                } else {
                    url = FAIL;
                }
            }
        } catch (Exception e) {
            log("Error At AddToDataBaseController: "+e.getMessage());
        } finally{
            request.getRequestDispatcher(url).forward(request, response);
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
