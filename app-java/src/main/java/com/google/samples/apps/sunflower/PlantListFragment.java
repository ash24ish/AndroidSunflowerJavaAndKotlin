
package com.google.samples.apps.sunflower;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.samples.apps.sunflower.adapters.PlantAdapter;
import com.google.samples.apps.sunflower.databinding.FragmentPlantListBinding;
import com.google.samples.apps.sunflower.utilities.InjectorUtils;
import com.google.samples.apps.sunflower.viewmodels.PlantListViewModel;
import com.google.samples.apps.sunflower.viewmodels.PlantListViewModelFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ListAdapter;


public class PlantListFragment extends Fragment {

    private PlantListViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentPlantListBinding binding = FragmentPlantListBinding.inflate(inflater, container, false);
        PlantListViewModelFactory factory = InjectorUtils.providePlantListViewModelFactory(getContext());
        ListAdapter adapter = new PlantAdapter();
        binding.plantList.setAdapter(adapter);
        this.viewModel = ViewModelProviders.of(this, factory).get(PlantListViewModel.class);
        subscribeUi(adapter);

        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_plant_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_zone:
                updateData();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void subscribeUi(ListAdapter adapter) {
        this.viewModel.plants.observe(getViewLifecycleOwner(), plants -> {
            if (plants != null) {
                adapter.submitList(plants);
            }
        });
    }

    private void updateData() {
        if (viewModel.isFiltered()) {
            viewModel.cleanGrowZoneNumber();
        } else {
            viewModel.setGrowZoneNumber(9);
        }
    }
}
