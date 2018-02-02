package com.mohbou.mvpapplication;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

//        when(mockLoginModel.getUser()).thenReturn(user);

        mockView = mock(LoginActivityMVP.View.class);

        presenter =  new LoginActivityPresenter(mockLoginModel);

        presenter.setView(mockView);
    }

    @Ignore
    @Test
    public void noInteractionWithView() {
        presenter.getCurrentUser();

        verifyZeroInteractions(mockView);
    }

    @Test
    public void loadTheUserFromTheRepositoryWhenValidUserIsPresent() {

        when(mockLoginModel.getUser()).thenReturn(user);

        presenter.getCurrentUser();

        //verify model interactions
        verify(mockLoginModel,times(1)).getUser();

        //verify view interactions
        verify(mockView,times(1)).setFirstName("Fox");
        verify(mockView,times(1)).setLastName("Mulder");
        verify(mockView,never()).showUserNotAvailable();


    }
}