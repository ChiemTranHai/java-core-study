package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class AccountClass {
    private List<String> loginIds = new ArrayList<>(List.of("loginId1", "loginId2"));
    private String name;
    private String loginId;

    public synchronized boolean isExistsAccount(String loginId) {
        return this.loginIds.contains(loginId);
    }

    public boolean insertAccount(String loginId) {
        boolean isInserted;
        synchronized (this) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            isInserted = this.add(loginId);
        }
        return isInserted;
    }

    public boolean add(String loginId) {
        return this.loginIds.add(loginId);
    }

    public List<String> getLoginIds() {
        return this.loginIds;
    }
}
