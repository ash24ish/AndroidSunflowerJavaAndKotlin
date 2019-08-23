

package com.google.samples.apps.sunflower.data;

import com.google.samples.apps.sunflower.utilities.AppExecutors;

import java.util.List;

import androidx.lifecycle.LiveData;


public class GardenPlantingRepository {
    private static GardenPlantingRepository instance;
    private GardenPlantingDao gardenPlantingDao;

    private GardenPlantingRepository(GardenPlantingDao gardenPlantingDao) {
        this.gardenPlantingDao = gardenPlantingDao;
    }

    public static GardenPlantingRepository getInstance(GardenPlantingDao gardenPlantingDao) {
        if (instance == null) {
            synchronized (GardenPlantingRepository.class) {
                if (instance == null) {
                    instance = new GardenPlantingRepository(gardenPlantingDao);
                }
            }
        }
        return instance;
    }

    public void createGardenPlanting(String plantId) {
        AppExecutors.getInstance().diskIO().execute(() ->
                gardenPlantingDao.insertGardenPlanting(new GardenPlanting(plantId, null, null)));
    }

    public void removeGardenPlanting(GardenPlanting gardenPlanting) {
        AppExecutors.getInstance().diskIO().execute(() ->
                gardenPlantingDao.deleteGardenPlanting(gardenPlanting));
    }

    public LiveData<GardenPlanting> getGardenPlantingForPlant(String plantId) {
        return gardenPlantingDao.getGardenPlantingForPlant(plantId);
    }

    public LiveData<List<GardenPlanting>> getGardenPlantings() {
        return gardenPlantingDao.getGardenPlantings();
    }

    public LiveData<List<PlantAndGardenPlantings>> getPlantAndGardenPlantings() {
        return gardenPlantingDao.getPlantAndGardenPlantings();
    }
}
