package com.aurora.user.services;

import com.aurora.user.dtos.UserDetailsDTO;
import com.aurora.user.entities.UserDetails;
import com.aurora.user.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    /**
     * Save the user and return the saved user details.
     */
    @Transactional
    public UserDetailsDTO saveUpdate(UserDetailsDTO inputUser) {
        try {
            UserDetails userDetails = new UserDetails();
            userDetails.setName(inputUser.getName());
            userDetails.setAge(Integer.parseInt(inputUser.getAge()));
            return getUserDetailsDto(repository.save(userDetails));
        } catch (Exception e) {
            LOGGER.warn("/******* Exception in UserService -> saveUpdate(): " + e);
        }
        return null;
    }

    public UserDetailsDTO getById(Long id) {
        return getUserDetailsDto(repository.getOne(id));
    }

    public List<UserDetailsDTO> getAll(){
        List<UserDetails> userDetailsList = repository.findAll();
        if (CollectionUtils.isEmpty(userDetailsList)) {
            return null;
        }
        return userDetailsList
                .stream()
                .map(this::getUserDetailsDto)
                .collect(Collectors.toList());
    }

    public List<UserDetailsDTO> getByName(String name) {
        List<UserDetails> userDetailsList = repository.findUserByName(name);
        if (CollectionUtils.isEmpty(userDetailsList)) {
            return null;
        }
        return userDetailsList
                .stream()
                .map(this::getUserDetailsDto)
                .collect(Collectors.toList());
    }


    public UserDetailsDTO getUserDetailsDto(UserDetails userDetails) {
        return new UserDetailsDTO(
                userDetails.getId().toString(),
                userDetails.getName(),
                userDetails.getAge().toString()
        );
    }

}
