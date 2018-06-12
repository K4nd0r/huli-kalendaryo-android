package com.greenfox.kalendaryo.providers;

import com.greenfox.kalendaryo.http.RetrofitClient;
import com.greenfox.kalendaryo.http.BackendApi;
import com.greenfox.kalendaryo.http.GoogleApi;
import com.greenfox.kalendaryo.services.AccountService;
import com.greenfox.kalendaryo.services.LogoutService;

import dagger.Module;
import dagger.Provides;


@Module
public class ApiProvider {

    @Provides
    BackendApi provideBackEndApi() {
        return RetrofitClient.getBackendApi();
    }

    @Provides
    GoogleApi provideGoogleApi() {
        return RetrofitClient.getGoogleApi();
    }

    @Provides
    RetrofitClient provideRetrofitClient() {
        return new RetrofitClient();
    }

    @Provides
    AccountService provideAccountService() {return new AccountService(); }

    @Provides
    LogoutService provideLogOutService() {return new LogoutService(); }
}
