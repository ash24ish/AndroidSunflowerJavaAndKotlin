

package com.google.samples.apps.sunflower.viewmodels;

import com.google.samples.apps.sunflower.data.PlantRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class PlantListViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private PlantRepository repository;

    public PlantListViewModelFactory(@NonNull PlantRepository repository) {
        super();
        this.repository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PlantListViewModel(repository);
    }
}
