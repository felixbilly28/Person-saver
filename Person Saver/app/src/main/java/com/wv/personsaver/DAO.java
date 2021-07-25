package com.wv.personsaver;

import java.util.ArrayList;
        import java.util.List;

public class DAO {
    private List<Person> personList = new ArrayList<>();
    private static DAO instance = new DAO();

    private DAO() {
    }

    public static DAO getInstance() {
        return instance;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public int listSize() {
        return personList.size();
    }

    public void ClearList(){
        personList.clear();
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }


}