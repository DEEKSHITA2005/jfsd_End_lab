package com.klu.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {

        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            System.out.println("Preparing to update department...");
            System.out.println("New Name: Computer Science, New Location: Block A, Target Department ID: 1");

            String hql = "UPDATE Department SET name = :name, location = :location WHERE departmentId = :id";
            int updatedEntities = session.createQuery(hql)
                    .setParameter("name", "Computer Science")
                    .setParameter("location", "Block A")
                    .setParameter("id", 1)
                    .executeUpdate();

            if (updatedEntities > 0) {
                System.out.println(updatedEntities + " record(s) updated successfully!");
            } else {
                System.out.println("No records found with the specified ID to update.");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
