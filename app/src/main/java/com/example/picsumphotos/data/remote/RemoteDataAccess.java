package com.example.picsumphotos.data.remote;

public class RemoteDataAccess {

    //singleton
    private static final RemoteDataAccess remoteDataInstance = new RemoteDataAccess();

    //private constructor
    private RemoteDataAccess(){

    }

    public static RemoteDataAccess getRemoteDataInstance(){
        return remoteDataInstance;
    }

    public PicturesItemDAO getPicturesItemDAOFromRemoteDataAccess(){
        return RetrofitClient.getRetrofitInstance().create(PicturesItemDAO.class);
    }
}
