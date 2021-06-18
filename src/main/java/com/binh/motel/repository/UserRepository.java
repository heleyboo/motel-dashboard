package com.binh.motel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.binh.motel.entity.Role;
import com.binh.motel.entity.User;

import javassist.NotFoundException;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    @Modifying
    @Query("update User u set u.password = :password where u.id = :id")
    void updatePassword(@Param("password") String password, @Param("id") int id);
	Optional<User> findByEmail(String email) throws NotFoundException;
    Optional<User> findByUserName(String userName) throws UsernameNotFoundException;
    Optional<User> findByUserNameOrEmail(String userName, String email) throws UsernameNotFoundException;
    public boolean existsByEmail(String email);
    public boolean existsByUserName(String userName);
    public List<User> findByRoles(Role role);
}
