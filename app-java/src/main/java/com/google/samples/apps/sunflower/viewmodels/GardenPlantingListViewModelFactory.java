

package com.google.samples.apps.sunflower.viewmodels;

import com.google.samples.apps.sunflower.data.GardenPlantingRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Factory for creating a [GardenPlantingListViewModel] with a constructor that takes a
 * [GardenPlantingRepository].
 */
public class GardenPlantingListViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private GardenPlantingRepository repository;

    public GardenPlantingListViewModelFactory(GardenPlantingRepository repository) {
        super();
        this.repository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GardenPlantingListViewModel(repository);
    }
}
