package com.example.service;

import com.example.model.Household;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class HouseholdDAO {
    private EntityManagerFactory entityManagerFactory;

    public HouseholdDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("householdPU");
    }

    public void saveHousehold(Household household) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(household);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Household> getAllHouseholds() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Household> query = entityManager.createQuery("SELECT h FROM Household h", Household.class);
        List<Household> households = query.getResultList();
        entityManager.close();
        return households;
    }

    public void deleteHousehold(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Household household = entityManager.find(Household.class, id);
        if (household != null) {
            entityManager.remove(household);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void close() {
        entityManagerFactory.close();
    }
}
