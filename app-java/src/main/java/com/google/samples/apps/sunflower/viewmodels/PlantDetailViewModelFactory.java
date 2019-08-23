

package com.google.samples.apps.sunflower.viewmodels;

import com.google.samples.apps.sunflower.data.GardenPlantingRepository;
import com.google.samples.apps.sunflower.data.PlantRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class PlantDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private String plantId;
    private PlantRepository plantRepository;
    private GardenPlantingRepository gardenPlantingRepository;

    public PlantDetailViewModelFactory(PlantRepository plantRepository,
                                       GardenPlantingRepository gardenPlantingRepository,
                                       String plantId) {
        this.plantId = plantId;
        this.plantRepository = plantRepository;
        this.gardenPlantingRepository = gardenPlantingRepository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PlantDetailViewModel(plantRepository, gardenPlantingRepository, plantId);
    }
}
