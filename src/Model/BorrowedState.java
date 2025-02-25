package Model;

public class BorrowedState implements VinylState
{
  @Override public void onBorrow(Vinyl vinyl, int userID)
  {
    throw new IllegalStateException ("This vinyl is already borrowed");
  }

  @Override public void onReturn(Vinyl vinyl)
  {
    vinyl.changeToAvailableState();


  }

  @Override public void onReserve(Vinyl vinyl, int userID)
  {

  }

  @Override public void onCancelReservation(Vinyl vinyl)
  {

  }

  @Override public void onRemove(Vinyl vinyl)
  {

  }

}
