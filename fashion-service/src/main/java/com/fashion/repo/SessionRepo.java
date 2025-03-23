package com.fashion.repo;

import com.fashion.request.SessionData;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface SessionRepo extends JpaRepository<SessionData,String> {

    @Query("select s from SessionData s where s.sessionId=:sid")
    List<SessionData> getWishListData(String sid);

    @Modifying
    @Transactional
    @Query("DELETE FROM SessionData s WHERE s.productUrl = :prodUrl")
    void deleteByProductUrl(@Param("prodUrl") String prodUrl);

}
