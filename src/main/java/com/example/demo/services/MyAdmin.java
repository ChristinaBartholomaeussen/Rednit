package com.example.demo.services;
import com.example.demo.models.User;

import java.util.ArrayList;
import java.util.List;

public class MyAdmin {

    public List<User> blacklist = new ArrayList<User>();


    public void addToBlacklist(User user){
        blacklist.add(user);
    }

    public void restoreUser(User user){
        //TODO
        //INDSÆT KODE
    }

    public List getBlacklist(){
        return blacklist;
    }

    public void deleteUser(User user){
        //TODO
        //INDSÆT KODE
    }

}
