

package com.google.samples.apps.sunflower.viewmodels;

import com.google.samples.apps.sunflower.data.GardenPlanting;
import com.google.samples.apps.sunflower.data.GardenPlantingRepository;
import com.google.samples.apps.sunflower.data.PlantAndGardenPlantings;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;


public class GardenPlantingListViewModel extends ViewModel {
    public LiveData<List<GardenPlanting>> gardenPlantings;
    public LiveData<List<PlantAndGardenPlantings>> plantAndGardenPlantings;

    GardenPlantingListViewModel(@NonNull GardenPlantingRepository repository) {
        this.gardenPlantings = repository.getGardenPlantings();
        this.plantAndGardenPlantings = Transformations.map(repository.getPlantAndGardenPlantings(), items -> {
            List<PlantAndGardenPlantings> removeItems = new ArrayList<>();
            for (PlantAndGardenPlantings item:items) {
                if (item.getGardenPlantings().isEmpty()) {
                    removeItems.add(item);
                }
            }
            items.removeAll(removeItems);
            return items;
        });
    }
}
