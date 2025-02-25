package Model;

import java.util.Date;

public class Vinyl
{

  private String title;
  private String artistName;
  private int releaseYear;

  private Integer borrowedByID;
  private Integer reservedByID;

  private VinylState currentState;
  private boolean markedForRemoval;

  public Vinyl(String title, String artistName, int releaseYear)
  {
    this.title = title;
    this.artistName = artistName;
    this.releaseYear = releaseYear;

    borrowedByID = null;
    reservedByID = null;

    this.currentState = new AvailableState();
    this.markedForRemoval = false;
  }

  public void setReservedByID(Integer reservedByID)
  {
    this.reservedByID = reservedByID;
  }

  public void setBorrowedByID(Integer borrowedByID)
  {
    this.borrowedByID = borrowedByID;
  }

  public Integer getBorrowedByID()
  {
    return borrowedByID;
  }

  public Integer getReservedByID()
  {
    return reservedByID;
  }

  public

  public void markForRemoval(){

    markedForRemoval = true;
  }

  public void unmarkForRemoval(){

    markedForRemoval = false;
  }

  public boolean isItMarkedForRemoval(){return markedForRemoval;}


  //Change btwn states:
  public void changeToBorrowedState(){

    currentState = new BorrowedState();
  }

  public void changeToReservedState(){

    currentState = new ReservedState();
  }

  public void changeToAvailableState(){

    currentState = new AvailableState();
  }



}
