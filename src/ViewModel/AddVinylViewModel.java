package ViewModel;

import javafx.beans.property.*;
import Model.Model;

public class AddVinylViewModel // Handles the creation of new users, uses model add method
{

  private Model model;
  private StringProperty title, artistName;
  private IntegerProperty releaseYear;
  private BooleanProperty shouldSubmitBeDisabled = new SimpleBooleanProperty(true);

  public AddVinylViewModel(Model model){

    this.model = model;
    title = new SimpleStringProperty();
    artistName = new SimpleStringProperty();
    releaseYear = new SimpleIntegerProperty();
    shouldSubmitBeDisabled = new SimpleBooleanProperty();

  }

  public void addVinyl(){

    model.addVinyl(title.get(), artistName.get(), releaseYear.get());
  }

  public String getTitle()
  {
    return title.get();
  }

  public StringProperty titleProperty()
  {
    return title;
  }

  public String getArtistName()
  {
    return artistName.get();
  }

  public StringProperty artistNameProperty()
  {
    return artistName;
  }

  public int getReleaseYear()
  {
    return releaseYear.get();
  }

  public IntegerProperty releaseYearProperty()
  {
    return releaseYear;
  }

  public boolean isShouldSubmitBeDisabled()
  {
    return shouldSubmitBeDisabled.get();
  }

  public BooleanProperty shouldSubmitBeDisabledProperty()
  {
    return shouldSubmitBeDisabled;
  }
}
