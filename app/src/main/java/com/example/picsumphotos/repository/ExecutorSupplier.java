package com.example.picsumphotos.repository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorSupplier {

    private static final ExecutorService instance = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    //constructor
    private  ExecutorSupplier() {
    }

    public static ExecutorService getInstance(){
        return instance;
    }
}

//executor limits the number of threads to 4.
