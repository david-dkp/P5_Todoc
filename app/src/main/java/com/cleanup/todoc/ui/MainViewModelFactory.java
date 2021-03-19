package com.cleanup.todoc.ui;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    public TodocRepository todocRepository;

    public MainViewModelFactory(TodocRepository todocRepository) {
        this.todocRepository = todocRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(this.todocRepository);
        }

        throw new IllegalArgumentException("Unkown modelclass / wrong factory");
    }
}
