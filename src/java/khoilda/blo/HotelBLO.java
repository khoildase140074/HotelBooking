/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoilda.blo;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import khoilda.db.Hotel;

/**
 *
 * @author User
 */
public class HotelBLO implements Serializable {

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

    public List searchByLikeInput(String search) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT h FROM Hotel h Where h.name Like :name";
        Query query = em.createQuery(jpql);
        query.setParameter("name", "%" + search + "%");
        List resultList = query.getResultList();
        return resultList;
    }
    
    public Hotel getHotelFromDB(int hotelID) {
        EntityManager em = emf.createEntityManager();
        Hotel hotel = null;
        String jpql = "Hotel.findByHotelID";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("hotelID", hotelID);
        hotel = (Hotel) query.getSingleResult();
        return hotel;
    } 
    
}
