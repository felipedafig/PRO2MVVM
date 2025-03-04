package ViewModel;

import Shared.Session;
import javafx.beans.property.*;
import Model.Model;

public class AddVinylViewModel {

  private final Model model;
  private final StringProperty title;
  private final StringProperty artistName;
  private final IntegerProperty releaseYear;
  private final BooleanProperty shouldSubmitBeDisabled;

  public AddVinylViewModel(Model model) {
    this.model = model;
    this.title = new SimpleStringProperty();
    this.artistName = new SimpleStringProperty();
    this.releaseYear = new SimpleIntegerProperty();
    this.shouldSubmitBeDisabled = new SimpleBooleanProperty(true);
  }

  public void addVinyl(String title, String artistName, int releaseYear) {
    model.addVinyl(title, artistName, releaseYear);
  }

  public StringProperty titleProperty() {
    return title;
  }

  public StringProperty artistNameProperty() {
    return artistName;
  }

  public IntegerProperty releaseYearProperty() {
    return releaseYear;
  }

  public BooleanProperty shouldSubmitBeDisabledProperty() {
    return shouldSubmitBeDisabled;
  }

  public void validateForm(boolean isReleaseYearValid) {
    boolean isTitleFilled = title.get() != null && !title.get().trim().isEmpty();
    boolean isArtistFilled = artistName.get() != null && !artistName.get().trim().isEmpty();

    //disable add button if any field is invalid
    shouldSubmitBeDisabled.set(!(isTitleFilled && isArtistFilled && isReleaseYearValid));
  }



}
