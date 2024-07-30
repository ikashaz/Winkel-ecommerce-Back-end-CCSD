package com.clothes.ecommerce.service;

import com.clothes.ecommerce.Exception.userAlreadyExistException;
import com.clothes.ecommerce.Model.LoginBody;
import com.clothes.ecommerce.Model.RegistrationBody;
import com.clothes.ecommerce.dao.LocalUserDAO;
import com.clothes.ecommerce.dao.UserRepository;
import com.clothes.ecommerce.entity.LocalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private LocalUserDAO localUserDAO;
    private EncryptionService encryptionService;
    private JWTService jwtService;

    public UserService(LocalUserDAO localUserDAO, EncryptionService encryptionService, JWTService jwtService) {
        this.localUserDAO = localUserDAO;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }

    public LocalUser registerUser(RegistrationBody registrationBody) throws userAlreadyExistException {

        if(localUserDAO.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
                || localUserDAO.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()){
            throw new userAlreadyExistException();
        }

        LocalUser user = new LocalUser();
        user.setEmail(registrationBody.getEmail());
        user.setUsername(registrationBody.getUsername());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());


        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));



        return localUserDAO.save(user);


    }

    public String loginUser(LoginBody loginBody) {
        Optional<LocalUser> opUser = localUserDAO.findByUsernameIgnoreCase(loginBody.getUsername());
        if (opUser.isPresent()) {
            LocalUser user = opUser.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }

    public Optional<LocalUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

}