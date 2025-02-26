package ViewModel;

import Model.Model;
import Model.Vinyl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.util.List;


public class VinylListViewModel // Manages the list of users and provides data to the View
{
  private Model model;
  private ObservableList<Vinyl> vinyls;

  public VinylListViewModel(Model model)
  {
    this.model = model;
    vinyls = FXCollections.observableArrayList();
    refresh();
    model.addPropertyChangeListener("VinylAdded", this::update);
  }

  private void update(PropertyChangeEvent propertyChangeEvent)
  {
    List<Vinyl> newVinyls = (List<Vinyl>) propertyChangeEvent.getNewValue();
    vinyls.clear();
    vinyls.addAll(newVinyls);
  }

  public void refresh() // This method might be useless
  {
    vinyls.clear();
    vinyls.addAll(model.getVinyls());
  }

  public ObservableList<Vinyl> getVinyls()
  {
    return vinyls;
  }
}
