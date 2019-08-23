

package com.google.samples.apps.sunflower.viewmodels;

import com.google.samples.apps.sunflower.data.GardenPlanting;
import com.google.samples.apps.sunflower.data.GardenPlantingRepository;
import com.google.samples.apps.sunflower.data.Plant;
import com.google.samples.apps.sunflower.data.PlantRepository;
import com.google.samples.apps.sunflower.utilities.AppExecutors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

/**
 * The ViewModel used in [PlantDetailFragment].
 */
public class PlantDetailViewModel extends ViewModel {
    private GardenPlantingRepository gardenPlantingRepository;

    private String plantId;

    private LiveData<Boolean> isPlanted;
    public LiveData<Plant> plant;

    PlantDetailViewModel(PlantRepository plantRepository, GardenPlantingRepository gardenPlantingRepository, String plantId) {
        super();
        this.gardenPlantingRepository = gardenPlantingRepository;
        this.plantId = plantId;

        /* The getGardenPlantingForPlant method returns a LiveData from querying the database. The
         * method can return null in two cases: when the database query is running and if no records
         * are found. In these cases isPlanted is false. If a record is found then isPlanted is
         * true. */
        LiveData<GardenPlanting> gardenPlantingForPlant = gardenPlantingRepository.getGardenPlantingForPlant(plantId);
        this.isPlanted = Transformations.map(gardenPlantingForPlant, it -> it != null);
        this.plant = plantRepository.getPlant(plantId);
    }

    public void addPlantToGarden() {
        AppExecutors.getInstance().diskIO().execute(() -> gardenPlantingRepository.createGardenPlanting(plantId));
    }

    public LiveData<Boolean> getIsPlanted() {
        return isPlanted;
    }
}
