package com.services;

import com.dao.Identities.ProductId;
import com.dao.Manufacturer;
import com.dao.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.repositories.ProductRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager em;


    @Transactional //@Transactional annotation indicates that the method will be executed in the transaction. Spring will take care of transaction management.
    public List<Product> getProductsOnlyById(List<Long> ids)
    {
        return productRepository.findByPidIn(ids);
    }

    @Transactional
    public Optional<Product> getProductByEmbeddedId(ProductId productId)
    {
        return productRepository.findById(productId);
    }



    @Transactional
    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    @Transactional
    public Product addProduct(Product p)
    {
        return productRepository.save(p);
    }


    public boolean addProducts(List<Product> products)
    {
        int i = 0;
        int batchSize = 50;
        boolean status = true;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            for (Product p : products) {
                session.save(p);
                i++;
                if (i % batchSize == 0) {
                    // Flush a batch of inserts and release memory.
                    session.flush();
                    session.clear();
                }
            }
            tx.commit();
        } catch (Exception ex) {
            System.out.println("DB bulk save failed : {}".format(ex.toString()));
            tx.rollback();
            status = false;
        } finally {
            session.close();
        }

        return status;
    }

    private Session getSession() {
        return emf.unwrap(SessionFactory.class).openSession();
    }


}
