

package com.google.samples.apps.sunflower;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.samples.apps.sunflower.adapters.GardenPlantingAdapter;
import com.google.samples.apps.sunflower.databinding.FragmentGardenBinding;
import com.google.samples.apps.sunflower.utilities.InjectorUtils;
import com.google.samples.apps.sunflower.viewmodels.GardenPlantingListViewModel;
import com.google.samples.apps.sunflower.viewmodels.GardenPlantingListViewModelFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ListAdapter;


public class GardenFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentGardenBinding binding = FragmentGardenBinding.inflate(inflater, container, false);
        GardenPlantingAdapter adapter = new GardenPlantingAdapter();
        binding.gardenList.setAdapter(adapter);
        subScribeUi(adapter, binding);
        return binding.getRoot();
    }

    private void subScribeUi(@NonNull GardenPlantingAdapter adapter, @NonNull FragmentGardenBinding binding) {
        GardenPlantingListViewModelFactory factory =
                InjectorUtils.provideGardenPlantingListViewModelFactory(requireContext());
        GardenPlantingListViewModel viewModel =
                ViewModelProviders.of(this, factory).get(GardenPlantingListViewModel.class);

        viewModel.gardenPlantings.observe(getViewLifecycleOwner(), gardenPlantings ->
                binding.setHasPlantings(gardenPlantings != null && !gardenPlantings.isEmpty()));

        viewModel.plantAndGardenPlantings.observe(getViewLifecycleOwner(), plantAndGardenPlantings -> {
            if (plantAndGardenPlantings != null && !plantAndGardenPlantings.isEmpty()) {
                adapter.submitList(plantAndGardenPlantings);
            }
        });
    }
}
