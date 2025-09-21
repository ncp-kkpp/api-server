package com.kkpp.api_server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kkpp.api_server.entity.FreqIngredient;

@Repository
public interface FreqIngredientRepository extends JpaRepository<FreqIngredient, Long> {

    List<FreqIngredient> findTop10ByUserIdOrderBySearchCountDescUpdateDtDesc(String userId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = """
        INSERT INTO freq_search_ingredient (user_id, name)
        VALUES (:userId, :name)
        ON CONFLICT (user_id, name)
        DO UPDATE SET
            search_count = freq_search_ingredient.search_count + EXCLUDED.search_count
        """, nativeQuery = true)
    void upsertIngrdt(
            @Param("userId") String userId,
            @Param("name") String name
    );
    
    void deleteByUserId(String userId);
    
}
