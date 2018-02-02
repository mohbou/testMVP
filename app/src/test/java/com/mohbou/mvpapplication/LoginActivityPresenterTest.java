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

    @Test
    public void shouldShowErrorMessageWhenUserIsNull() {
        when(mockLoginModel.getUser()).thenReturn(null);

        presenter.getCurrentUser();

        verify(mockLoginModel,times(1)).getUser();

        verify(mockView,never()).setFirstName("Fox");
        verify(mockView,never()).setLastName("Mulder");
        verify(mockView,times(1)).showUserNotAvailable();
    }

    @Test
    public void shouldCreateErrorMessageIfFieldAreEmpty() {
        when(mockView.getFirstName()).thenReturn(""); // empty String

        presenter.loginButtonClicked();

        verify(mockView,times(1)).getFirstName();
        verify(mockView,never()).getLastName();
        verify(mockView,times(1)).showInputError();

        //Now tell mockView to return a value for first name and an empty Last name
        when(mockView.getFirstName()).thenReturn("Dana");
        when(mockView.getLastName()).thenReturn("");

        presenter.loginButtonClicked();

        verify(mockView,times(2)).getFirstName();
        verify(mockView,times(1)).getLastName();
        verify(mockView,times(2)).showInputError();

    }

    @Test
    public void shouldBeAbletoSaveAValidUser(){
        when(mockView.getFirstName()).thenReturn("Dana");
        when(mockView.getLastName()).thenReturn("Scully");

        presenter.loginButtonClicked();

        verify(mockView,times(2)).getFirstName();
        verify(mockView,times(2)).getLastName();
        verify(mockLoginModel,times(1)).createUser("Dana","Scully");
        verify(mockView,times(1)).showUserSavedMessage();
    }
}