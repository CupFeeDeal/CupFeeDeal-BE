package com.cupfeedeal.domain.UserCafeLike.service;

import com.cupfeedeal.domain.User.entity.User;
import com.cupfeedeal.domain.User.repository.UserRepository;
import com.cupfeedeal.domain.UserCafeLike.entity.UserCafeLike;
import com.cupfeedeal.domain.UserCafeLike.repository.UserCafeLikeRepository;
import com.cupfeedeal.domain.cafe.entity.Cafe;
import com.cupfeedeal.domain.cafe.repository.CafeRepository;
import com.cupfeedeal.global.common.response.CommonResponse;
import com.cupfeedeal.global.exception.ApplicationException;
import com.cupfeedeal.global.exception.ExceptionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserCafeLikeService {

    @Autowired
    private UserCafeLikeRepository userCafeLikeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CafeRepository cafeRepository;

    public String addCafeLike(Long userId, Long cafeId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(ExceptionCode.USER_NOT_FOUND));

        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(() -> new ApplicationException(ExceptionCode.NOT_FOUND_CAFE));

        //유저와 카페의 좋아요가 이미 존재하는 지 확인
        userCafeLikeRepository.findByUserAndCafe(user, cafe)
                .ifPresent(existingLike -> {
                    throw new ApplicationException(ExceptionCode.ALREADY_EXIST_LIKE);
                });

        UserCafeLike userCafeLike = new UserCafeLike();
        userCafeLike.setUser(user);
        userCafeLike.setCafe(cafe);
        userCafeLike.setCreatedAt(LocalDateTime.now());
        userCafeLike.setUpdatedAt(LocalDateTime.now());

        userCafeLikeRepository.save(userCafeLike);

        return "좋아요를 성공적으로 추가했습니다.";
    }

    public String deleteCafeLike(Long userId, Long cafeId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(ExceptionCode.USER_NOT_FOUND));

        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(() -> new ApplicationException(ExceptionCode.NOT_FOUND_CAFE));

        UserCafeLike userCafeLike = userCafeLikeRepository.findByUserAndCafe(user, cafe)
                .orElseThrow(() -> new ApplicationException(ExceptionCode.NOT_FOUND_CAFE_LIKE));

        userCafeLikeRepository.delete(userCafeLike);

        return "카페 좋아요를 성공적으로 삭제했습니다.";

    }
}