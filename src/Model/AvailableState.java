package Model;

public class AvailableState implements VinylState
{
  @Override public void onBorrow(Vinyl vinyl, Integer userID)
  {
    // Logaction
    System.out.println("User " + userID + " is borrowing Vinyl '" + vinyl.getTitle() + "'.");

    vinyl.changeToBorrowedState();
    vinyl.setBorrowedByID(userID); //attach user to the vinyl

    // Log result
    System.out.println("Vinyl '" + vinyl.getTitle() + "' is now borrowed by User " + userID + ".");
  }

  @Override public void onReturn(Vinyl vinyl)
  {
    // Logerror
    System.out.println("Error: Attempted to return Vinyl '" + vinyl.getTitle() + "', but it is not borrowed.");

    throw new IllegalStateException("Vinyl is not borrowed");
  }

  @Override public void onReserve(Vinyl vinyl, Integer userID)
  {
    // Log action
    System.out.println("User " + userID + " is attempting to reserve Vinyl '" + vinyl.getTitle() + "'.");

    vinyl.changeToBorrowedState();
    vinyl.setBorrowedByID(userID); // Since we are in the Available state, there is no borrow therefore anyone tries to reserve immediately borrows it.

    // Log result
    System.out.println("Vinyl " + vinyl.getTitle() + " borrowed (tried to reserve, but it was available) by User " + userID);
  }

  @Override public void onCancelReservation(Vinyl vinyl)
  {
    // Log error
    System.out.println("Error: Attempted to cancel reservation for Vinyl '" + vinyl.getTitle() + "', but it is not reserved.");

    throw new IllegalStateException("Vinyl is not reserved");
  }
}