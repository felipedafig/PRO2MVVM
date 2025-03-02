package ViewModel;

import Model.Model;
import Model.Vinyl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class VinylListViewModel {
  private Model model;
  private ObservableList<Vinyl> vinyls;

  public VinylListViewModel(Model model) {
    this.model = model;
    this.vinyls = FXCollections.observableArrayList();
    refresh(); // Load initial data from the Model
    model.addPropertyChangeListener("VinylAdded", this::update); // Listen for updates
  }

  private void update(PropertyChangeEvent propertyChangeEvent) {
    ObservableList<Vinyl> newVinyls = (ObservableList<Vinyl>) propertyChangeEvent.getNewValue();
    vinyls.clear();
    vinyls.addAll(newVinyls);
  }

  public void refresh() {
    vinyls.clear();
    vinyls.addAll(model.getVinyls()); // Get vinyls from the Model
  }

  public ObservableList<Vinyl> getVinyls() {
    return vinyls;
  }

  // Delegate button actions to the Model
  public void borrowVinyl(Vinyl vinyl, int userId) {
    if (vinyl != null) {
      model.borrowVinyl(vinyl, userId);
    }
  }

  public void returnVinyl(Vinyl vinyl, int userId) {
    if (vinyl != null) {
      model.returnVinyl(vinyl, userId);
    }
  }

  public void reserveVinyl(Vinyl vinyl, int userId) {
    if (vinyl != null) {
      model.reserveVinyl(vinyl, userId);
    }
  }

  public void cancelReservation(Vinyl vinyl) {
    if (vinyl != null) {
      model.cancelReservationVinyl(vinyl);
    }
  }

  public void markForRemoval(Vinyl vinyl) {
    if (vinyl != null) {
      model.removeVinyl(vinyl);
    }
  }

  public void unMarkForRemoval(Vinyl vinyl) {
    if (vinyl != null) {
      model.unMarkForRemovalVinyl(vinyl);
    }
  }

//  public void addNewVinyl() {
//    model.addVinyl("New Vinyl", "New Artist", 2023); // Add a new vinyl with default values
//  }

}