package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Model implements PropertyChangeSubject
{
  private ObservableList<Vinyl> vinyls;
  private PropertyChangeSupport support;

  public Model(){

    this.vinyls = FXCollections.observableArrayList();
    this.support = new PropertyChangeSupport(this);
  }

  public void addVinyl(Vinyl vinyl) {
    vinyls.add(vinyl);
    support.firePropertyChange("Vinyls", null, vinyls);
//    support.  isnt add a reservation or a borrow action because we gonna fire the change in stat

  }

  public List<Vinyl> getVinyls()
  {
    return vinyls;
  }

  public void removeVinyl(Vinyl vinyl){

    if(vinyl.getBorrowedByID() == null && vinyl.getReservedByID() == null){

      vinyls.remove(vinyl);
      support.firePropertyChange("Vinyls", null, vinyls);
    }
    else{
     vinyl.markForRemoval(); //If it is borrowed or reserved, it will be flagged to be deleted once it is available
    }
  }

  @Override
  public void addPropertyChangeListener(PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
    listener.propertyChange(new PropertyChangeEvent(this, null, null, vinyls));
  }

  @Override
  public void addPropertyChangeListener(String name, PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
    listener.propertyChange(new PropertyChangeEvent(this, name, null, vinyls));
  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {
    support.removePropertyChangeListener(listener);
  }

  public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
    support.removePropertyChangeListener(name, listener);
  }
}
