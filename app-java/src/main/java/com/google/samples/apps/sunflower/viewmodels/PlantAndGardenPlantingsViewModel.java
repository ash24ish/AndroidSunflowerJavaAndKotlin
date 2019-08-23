
package com.google.samples.apps.sunflower.viewmodels;

import com.google.common.base.Preconditions;
import com.google.samples.apps.sunflower.data.GardenPlanting;
import com.google.samples.apps.sunflower.data.Plant;
import com.google.samples.apps.sunflower.data.PlantAndGardenPlantings;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;



public class PlantAndGardenPlantingsViewModel extends ViewModel {
    public ObservableField<String> waterDateString;
    public ObservableInt wateringInterval;
    public ObservableField<String> imageUrl;
    public ObservableField<String> plantName;
    public ObservableField<String> plantDateString;

    public PlantAndGardenPlantingsViewModel(@NonNull PlantAndGardenPlantings plantings) {
        final Plant plant = Preconditions.checkNotNull(plantings.getPlant());
        final GardenPlanting gardenPlanting = plantings.getGardenPlantings().get(0);
        final DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.US);

        this.waterDateString = new ObservableField<>(dateFormat.format(gardenPlanting.getLastWateringDate().getTime()));
        this.wateringInterval = new ObservableInt(plant.getWateringInterval());
        this.imageUrl = new ObservableField<>(plant.getImageUrl());
        this.plantName = new ObservableField<>(plant.getName());
        this.plantDateString = new ObservableField<>(dateFormat.format(gardenPlanting.getPlantDate().getTime()));
    }
}
