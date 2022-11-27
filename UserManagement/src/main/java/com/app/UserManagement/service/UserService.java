package com.app.UserManagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.app.UserManagement.model.entity.User;
import com.app.UserManagement.payload.LoginRequest;

import java.util.List;

public interface UserService {

    //public void saveUser(User User);

    public boolean removeAll();

    public void removeById(Long id);

    public User findById(Long id);

	public User getDetailsByUserName(String name);

	public User updateUser(long id, User user, List<String> strList);

	public void saveUser(User user, List<String> strRoles);
    

}


