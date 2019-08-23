

package com.google.samples.apps.sunflower.utilities;

import android.content.Context;

import com.google.samples.apps.sunflower.data.AppDatabase;
import com.google.samples.apps.sunflower.data.GardenPlantingRepository;
import com.google.samples.apps.sunflower.data.PlantRepository;
import com.google.samples.apps.sunflower.viewmodels.GardenPlantingListViewModelFactory;
import com.google.samples.apps.sunflower.viewmodels.PlantDetailViewModelFactory;
import com.google.samples.apps.sunflower.viewmodels.PlantListViewModelFactory;

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
public class InjectorUtils {

    private static PlantRepository getPlantRepository(Context context) {
        return PlantRepository.getInstance(AppDatabase.getInstance(context.getApplicationContext()).getPlantDao());
    }

    private static GardenPlantingRepository getGardenPlantingRepository(Context context) {
        return GardenPlantingRepository.getInstance(
                AppDatabase.getInstance(context.getApplicationContext()).getGardenPlantingDao());
    }

    public static GardenPlantingListViewModelFactory provideGardenPlantingListViewModelFactory(Context context) {
        return new GardenPlantingListViewModelFactory(getGardenPlantingRepository(context));
    }

    public static PlantListViewModelFactory providePlantListViewModelFactory(Context context) {
        return new PlantListViewModelFactory(getPlantRepository(context));
    }

    public static PlantDetailViewModelFactory providerPlantDetailViewModelFactory(Context context, String plantId) {
        return new PlantDetailViewModelFactory(getPlantRepository(context), getGardenPlantingRepository(context), plantId);
    }
}
