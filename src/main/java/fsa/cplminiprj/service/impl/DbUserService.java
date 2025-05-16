package fsa.cplminiprj.service.impl;

import fsa.cplminiprj.service.UserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class DbUserService implements UserService {

    @Override
    public int getUserId(String username) {
        return 0;
    }
}
