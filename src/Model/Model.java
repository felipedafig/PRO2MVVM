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
  }

  public List<Vinyl> getVinyls()
  {
    return vinyls;
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
