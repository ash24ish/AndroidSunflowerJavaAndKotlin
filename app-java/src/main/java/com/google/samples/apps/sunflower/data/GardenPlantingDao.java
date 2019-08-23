

package com.google.samples.apps.sunflower.data;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface GardenPlantingDao {
    @Query("SELECT * FROM garden_plantings")
    LiveData<List<GardenPlanting>> getGardenPlantings();

    @Query("SELECT * FROM garden_plantings WHERE id = :gardenPlantingId")
    LiveData<GardenPlanting> getGardenPlanting(long gardenPlantingId);

    @Query("SELECT * FROM garden_plantings WHERE plant_id = :plantId")
    LiveData<GardenPlanting> getGardenPlantingForPlant(@NonNull String plantId);

    /**
     * This query will tell Room to query both the [Plant] and [GardenPlanting] tables and handle
     * the object mapping.
     */
    @Transaction
    @Query("SELECT * FROM plants")
    LiveData<List<PlantAndGardenPlantings>> getPlantAndGardenPlantings();

    @Insert
    long insertGardenPlanting(@NonNull GardenPlanting gardenPlanting);

    @Delete
    void deleteGardenPlanting(@NonNull GardenPlanting gardenPlanting);
}
