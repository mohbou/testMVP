package com.mohbou.mvpapplication;

import org.junit.Before;
import org.junit.Test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;


public class LoginActivityPresenterTest {


    LoginActivityMVP.Model mockLoginModel;
    LoginActivityMVP.View mockView;
    LoginActivityMVP.Presenter presenter;
    User user;

    @Before
    public void setup() {
        mockLoginModel = mock(LoginActivityMVP.Model.class);

        user = new User("Fox","Mulder");

        when(mockLoginModel.getUser()).thenReturn(user);

        mockView = mock(LoginActivityMVP.View.class);

        presenter =  new LoginActivityPresenter(mockLoginModel);

        presenter.setView(mockView);
    }

    @Test
    public void noInteractionWithView() {
        presenter.getCurrentUser();

        verifyZeroInteractions(mockView);
    }
}