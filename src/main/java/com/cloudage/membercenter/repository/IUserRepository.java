package com.cloudage.membercenter.repository;

import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface IUserRepository extends PagingAndSortingRepository<User, Integer>{

        @Query("select u from User u where u.account=?1")
        public User finUserByAccount(String account);
        
        @Query("select u from User u where u.email = ?1")
        public User findUserByEmail(String email);
        
        @Query("select count(*) from User u where u.account=?1")
    	int checkIsUser(String account);
        
        @Query("select count(*) from User u where u.email=?1")
    	int checkIsEmail(String email);
        
        @Query("select count(*) from User u where u.account=?1 and u.passwordHash=?2")
    	int checkIsMatch(String account,String passwordHash);
}
