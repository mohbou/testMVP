package com.mohbou.mvpapplication;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class,LoginModule.class})
public interface ApplicationComponent {

    void inject(LoginActivity target);

}
