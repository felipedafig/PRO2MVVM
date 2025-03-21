package ViewModel;

import Model.DataModel;
import Model.Vinyl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;

public class ViewModel
{

  private DataModel model;
  private ObservableList<Vinyl> vinyls;

  public ViewModel(DataModel model) {
    this.model = model;
    this.vinyls = FXCollections.observableArrayList();
    refresh();
    model.addPropertyChangeListener("VinylUpdated", this::update);
  }

  private void update(PropertyChangeEvent propertyChangeEvent) {
    ObservableList<Vinyl> newVinyls = (ObservableList<Vinyl>) propertyChangeEvent.getNewValue();
    vinyls.clear();
    vinyls.addAll(newVinyls);
  }

  public void refresh() {
    vinyls.clear();
    vinyls.addAll(model.getVinyls());
  }

  public ObservableList<Vinyl> getVinyls() {
    return vinyls;
  }

  public void borrowVinyl(Vinyl vinyl, int userID) {
    if (vinyl != null) {
      model.borrowVinyl(vinyl, userID);
    }
  }

  public void returnVinyl(Vinyl vinyl, int userID) {
      model.returnVinyl(vinyl, userID);
  }

  public void reserveVinyl(Vinyl vinyl, int userID) {
    if (vinyl != null) {
      model.reserveVinyl(vinyl, userID);
    }
  }
  public void remove(Vinyl vinyl) {
    if (vinyl != null && vinyl.getState().equals("Available")) {
      model.removeVinyl(vinyl);
    }
    else
    {
        vinyl.flagForRemoval();
    }
  }

}
