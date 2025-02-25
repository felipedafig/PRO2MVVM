package Model;

public class AvailableState implements VinylState
{
  @Override public void onBorrow(Vinyl vinyl, int userID)
  {
    vinyl.changeToBorrowedState();
    vinyl.setBorrowedByID(userID); //attach user to the vinyl
  }

  @Override public void onReturn(Vinyl vinyl)
  {
    throw new IllegalStateException ("Vinyl is not borrowed");
  }

  @Override public void onReserve(Vinyl vinyl, int userID)
  {
    vinyl.changeToReservedState();
    vinyl.setReservedByID(userID); //attach user to the vinyl
  }

  @Override public void onCancelReservation(Vinyl vinyl)
  {
    throw new IllegalStateException ("Vinyl is not reserved");
  }

  @Override public void onRemove(Vinyl vinyl)
  {

    if(vinyl.getReservedByID()==null && vinyl.getBorrowedByID()==null){
      vinlys.
    }



  }

}
