package com.movies.hussein.services;

import com.movies.hussein.models.UsersEntity;
import com.movies.hussein.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public String createAccount(String username, String email, String password) throws NoSuchAlgorithmException {

        if (username == null || username.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return "Please don't leave any fields empty!";
        }
        if (userRepository.existsByUsername(username)) {
            return "Username Taken!";
        }
        if(userRepository.existsByEmail(email)){
            return "Email Taken!";
        }

        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUsername(username);
        usersEntity.setEmail(email);
        usersEntity.setSalt(generateSalt());
        String hashedPassword = hash(password+usersEntity.getSalt());
        usersEntity.setPassword(hashedPassword);
        userRepository.save(usersEntity);
        return "Account created!";
    }

    public boolean login(String username, String password) throws NoSuchAlgorithmException {
        if(!userRepository.existsByUsername(username)){
            return false;
        }
        UsersEntity user = userRepository.findByUsername(username);
        String hashedPassword = hashAndSaltPassword(password,user.getSalt());
        if(!user.getPassword().equals(hashedPassword)){
            return false;
        }
        return true;
    }

    public UsersEntity findByUserId(int userId){
        return userRepository.findByUserId(userId);
    }


    public String hash(String password)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.reset();
        md.update(password.getBytes());
        byte[] mdArray = md.digest();
        StringBuilder sb = new StringBuilder(mdArray.length * 2);
        for (byte b : mdArray) {
            int v = b & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }

    private String generateSalt(){
        Random r = new SecureRandom();
        byte[] saltBytes = new byte[32];
        r.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    public String hashAndSaltPassword(String password, String salt)
            throws NoSuchAlgorithmException {
        return hash(password + salt);
    }
}
