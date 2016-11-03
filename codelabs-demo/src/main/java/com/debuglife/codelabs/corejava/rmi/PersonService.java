package com.debuglife.codelabs.corejava.rmi;

import java.rmi.Remote;
import java.util.List;


public interface PersonService extends Remote{
    public List<PersonEntity> getList() throws Exception;
}
