/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoilda.blo;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import khoilda.db.Discount;

/**
 *
 * @author User
 */
public class DiscountBLO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("HotelBookingPU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public int getDiscountPercent(int discountId) {
        EntityManager em = emf.createEntityManager();
        try {
            Discount d = em.find(Discount.class, discountId);
            return d.getDiscount();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public static void main(String[] args) {
        System.out.println(new DiscountBLO().getDiscountPercent(123456));
    }
}
