

package com.google.samples.apps.sunflower.data;

import java.util.Calendar;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "plants")
public final class Plant {
    private static final int DEFAULT_WATERING_INTERVAL = 7;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private final String plantId;

    @NonNull
    private final String name;

    @NonNull
    private final String description;

    private final int growZoneNumber;

    private final int wateringInterval; // how often the plant should be watered, in days

    @NonNull
    private final String imageUrl;

    public Plant(@NonNull String plantId, @NonNull String name, @NonNull String description,
                 int growZoneNumber, int wateringInterval, @NonNull String imageUrl) {
        this.plantId = plantId;
        this.name = name;
        this.description = description;
        this.growZoneNumber = growZoneNumber;
        this.wateringInterval = wateringInterval > 0 ? wateringInterval : DEFAULT_WATERING_INTERVAL;
        this.imageUrl = imageUrl;
    }

    /**
     * Determines if the plant should be watered.  Returns true if [since]'s date > date of last
     * watering + watering Interval; false otherwise.
     */
    public boolean shouldBeWatered(Calendar since, Calendar lastWateringDate) {
        lastWateringDate.add(Calendar.DAY_OF_YEAR, wateringInterval);
        return since.compareTo(lastWateringDate) > 0;
    }

    @NonNull
    public String getPlantId() {
        return plantId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public int getGrowZoneNumber() {
        return growZoneNumber;
    }

    public int getWateringInterval() {
        return wateringInterval;
    }

    @NonNull
    public String getImageUrl() {
        return imageUrl;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    /**
     * As [Plant.kt] is declared as [Data class], {@link Object#equals(Object)} implicit implemented.
     * So we explicit implemented {@link Object#equals(Object)} in [Plant.java]
     * see: https://kotlinlang.org/docs/reference/data-classes.html
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof Plant
                && this.plantId.equals(((Plant) obj).plantId);
    }

    /**
     * As [Plant.kt] is declared as [Data class], {@link Object#hashCode()} implicit implemented.
     * So we explicit implemented {@link Object#hashCode()} in [Plant.java]
     * see: https://kotlinlang.org/docs/reference/data-classes.html
     */
    @Override
    public int hashCode() {
        return Objects.hash(plantId);
    }

    /**
     * As [Plant.kt] is declared as [Data class], {copy()} implicit implemented.
     * So we explicit implemented {@link Object#clone()} in [Plant.java]
     * see: https://kotlinlang.org/docs/reference/data-classes.html
     */
    @Override
    protected Object clone() {
        return new Plant(plantId, name, description, growZoneNumber, wateringInterval, imageUrl);
    }
}