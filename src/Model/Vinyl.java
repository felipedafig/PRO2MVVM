package Model;

import java.util.Date;

public class Vinyl
{

  private String title;
  private String artistName;
  private int releaseYear;

  private Integer borrowedByID; // Integer because int is primitive and cant be null
  private Integer reservedByID;

  private boolean isReserved;

  private VinylState currentState;
  private VinylState availableState, borrowedState;
  private boolean markedForRemoval;

  public Vinyl(String title, String artistName, int releaseYear)
  {
    this.title = title;
    this.artistName = artistName;
    this.releaseYear = releaseYear;

    borrowedByID = null;
    reservedByID = null;

    availableState = new AvailableState();
    borrowedState = new BorrowedState();

    this.currentState = availableState;
    this.markedForRemoval = false;
    this.isReserved = false;
  }

  public void setArtistName(String artistName)
  {
    this.artistName = artistName;
  }

  public void setCurrentState(VinylState currentState)
  {
    this.currentState = currentState;
  }

  public void setReserved(boolean reserved)
  {
    isReserved = reserved;
  }

  public void setMarkedForRemoval(boolean markedForRemoval)
  {
    this.markedForRemoval = markedForRemoval;
  }

  public void setReleaseYear(int releaseYear)
  {
    this.releaseYear = releaseYear;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public VinylState getCurrentState()
  {
    return currentState;
  }

  public String getArtistName()
  {
    return artistName;
  }

  public boolean isMarkedForRemoval()
  {
    return markedForRemoval;
  }

  public int getReleaseYear()
  {
    return releaseYear;
  }

  public String getTitle()
  {
    return title;
  }

  public void setReservedByID(Integer reservedByID)
  {
    this.reservedByID = reservedByID;
    currentState.onReserve(this, reservedByID);
  }

  public void returnVinyl(Integer borrowedByID){
    
    currentState.onReserve(this, borrowedByID);
}

  public void setBorrowedByID(Integer borrowedByID)
  {
    this.borrowedByID = borrowedByID;
    currentState.onBorrow(this, borrowedByID);
  }

  public void cancelReservation(Integer reservedByID){

    this.reservedByID = reservedByID;
    currentState.onCancelReservation(this);
  }

  public Integer getBorrowedByID()
  {
    return borrowedByID;
  }

  public Integer getReservedByID()
  {
    return reservedByID;
  }

  public boolean isReserved(){
    return isReserved;
  }

  public void setIsReserved(boolean reserved){
    this.isReserved = reserved;
  }

  public void markForRemoval(){

    markedForRemoval = true;
  }

  public void unmarkForRemoval(){

    markedForRemoval = false;
  }

  public boolean isItMarkedForRemoval(){return markedForRemoval;}


  public void changeToBorrowedState(){

    currentState = borrowedState;
  }

  public void changeToAvailableState(){

    currentState = availableState;
  }

  public String toString(){
    return ""; //... prob gonna use in state collumn...String Builder
  };



}
