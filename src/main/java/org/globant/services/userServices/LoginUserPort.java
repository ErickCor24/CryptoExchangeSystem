package org.globant.services.userServices;

import org.globant.model.user.User;

public interface LoginUserPort {
    User searchUserAccount(String email, String password);

    Integer searchIdByUser (User user);

    void enterUserSystem (int id, User user);
}
