package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DataModel implements PropertyChangeSubject
{
  private ObservableList<Vinyl> vinyls;
  private PropertyChangeSupport support;

  public DataModel(){

    this.vinyls = FXCollections.observableArrayList();
    vinyls.add(new Vinyl("Casa da Maria Joana", "Meu Pai", 2000));
    vinyls.add(new Vinyl("La playa", "Bruno", 2005));
    vinyls.add(new Vinyl("Brazil", "Maria", 2011));
    vinyls.add(new Vinyl("Pernanbuco", "Joao", 1900));
    vinyls.add(new Vinyl("La Casa", "Felipe", 2006));
    vinyls.add(new Vinyl("De Papel", "Lucas", 2005));
    vinyls.add(new Vinyl("Carai", "Felps", 2000));
    vinyls.add(new Vinyl("Oxente", "Leticia", 2015));
    vinyls.add(new Vinyl("Pirangueiro", "Felipao", 2017));
    this.support = new PropertyChangeSupport(this);

  }

  public synchronized void borrowVinyl(Vinyl vinyl, int userID) {

    vinyl.onBorrow(userID);
    support.firePropertyChange("VinylUpdated", null, vinyls);
  }

public synchronized void returnVinyl(Vinyl vinyl, int userID) {

  boolean isSuccessful = vinyl.onReturn(userID);
  if(isSuccessful && vinyl.getFlagged())
  {
    removeVinyl(vinyl);
  }
  support.firePropertyChange("VinylUpdated", null, vinyls);
}


public synchronized void reserveVinyl(Vinyl vinyl, int userID) {

  vinyl.onReserve(userID);
  support.firePropertyChange("VinylUpdated", null, vinyls);
}


public void removeVinyl(Vinyl vinyl){
    Object removed = vinyls.remove(vinyl);
    support.firePropertyChange("VinylUpdated", null, vinyls);
}

public ObservableList<Vinyl> getVinyls()
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
