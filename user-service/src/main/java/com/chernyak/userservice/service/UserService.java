package com.chernyak.userservice.service;

import com.chernyak.userservice.entity.Goal;
import com.chernyak.userservice.entity.User;
import com.chernyak.userservice.entity.UserParameters;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface UserService {
    User findByLogin(String login);
    User findById(String id);
    List<User> getAll();
    List<UserParameters> getUserParametersHistory(String id, LocalDate from, LocalDate to);
    User setUserGoal(String id, Goal goal);
    User save(User user);
    User update(String id, User pUser);
    User updateUserParameters(String id, UserParameters userParameters);
    void resetPassword(User user, String newPassword);
    void delete(String id);
    User updateUserPhoto(String id, MultipartFile file) throws IOException;
}
