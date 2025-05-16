package fsa.cplminiprj.service.impl;

import fsa.cplminiprj.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class InMemUserService implements UserService {
    Map<String, Integer> userMap = new HashMap<>();

    public InMemUserService() {
        userMap.put("user01", 1);
        userMap.put("user02", 2);
    }

    @Override
    public int getUserId(String username) {
        return 0;
    }
}
