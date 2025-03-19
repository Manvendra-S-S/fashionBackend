package com.fashion.repo;

import com.fashion.response.ProdDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdRepo extends JpaRepository<ProdDetails, String> {

    @Query("SELECT p FROM ProdDetails p JOIN FETCH p.reviews WHERE p.productId = :productId")
    ProdDetails findProductById(String productId);
}

