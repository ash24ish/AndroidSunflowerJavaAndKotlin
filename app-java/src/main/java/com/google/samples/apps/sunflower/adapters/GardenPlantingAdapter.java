

package com.google.samples.apps.sunflower.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.samples.apps.sunflower.GardenFragmentDirections;
import com.google.samples.apps.sunflower.R;
import com.google.samples.apps.sunflower.data.PlantAndGardenPlantings;
import com.google.samples.apps.sunflower.databinding.ListItemGardenPlantingBinding;
import com.google.samples.apps.sunflower.viewmodels.PlantAndGardenPlantingsViewModel;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


public class GardenPlantingAdapter extends ListAdapter<PlantAndGardenPlantings, GardenPlantingAdapter.ViewHolder> {

    public GardenPlantingAdapter() {
        super(new GardenPlantDiffCallback());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item_garden_planting, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlantAndGardenPlantings plantings = getItem(position);
        holder.itemView.setTag(plantings);
        holder.bind(createOnClickListener(plantings.getPlant().getPlantId()), plantings);
    }

    private View.OnClickListener createOnClickListener(String plantId) {
        return v -> Navigation.findNavController(v).navigate(
                GardenFragmentDirections.actionGardenFragmentToPlantDetailFragment(plantId));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ListItemGardenPlantingBinding binding;

        public ViewHolder(@NonNull ListItemGardenPlantingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(View.OnClickListener listener, PlantAndGardenPlantings plantings) {
            this.binding.setClickListener(listener);
            this.binding.setViewModel(new PlantAndGardenPlantingsViewModel(plantings));
            this.binding.executePendingBindings();
        }
    }

    static class GardenPlantDiffCallback extends DiffUtil.ItemCallback<PlantAndGardenPlantings> {

        @Override
        public boolean areItemsTheSame(@NonNull PlantAndGardenPlantings oldItem,
                                       @NonNull PlantAndGardenPlantings newItem) {
            return oldItem.getPlant().getPlantId().equals(newItem.getPlant().getPlantId());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull PlantAndGardenPlantings oldItem,
                                          @NonNull PlantAndGardenPlantings newItem) {
            return oldItem.equals(newItem);
        }
    }
}
