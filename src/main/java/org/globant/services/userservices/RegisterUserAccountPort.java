package org.globant.services.userservices;

import org.globant.model.user.User;

public interface RegisterUserAccountPort {
    User userRegister (int id, String name, String email, String password);
}
