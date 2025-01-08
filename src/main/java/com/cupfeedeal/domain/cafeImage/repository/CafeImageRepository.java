package com.cupfeedeal.domain.cafeImage.repository;

import com.cupfeedeal.domain.cafeImage.entity.CafeImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CafeImageRepository extends JpaRepository<CafeImage, Long> {
    public List<CafeImage> findAllByCafeId(Long cafeId);
}