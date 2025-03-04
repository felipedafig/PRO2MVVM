package Model;

public class BorrowedState implements VinylState
{
  @Override public void onBorrow(Vinyl vinyl, Integer userID)
  {
    // Log the error
    System.out.println(
        "Error: User " + userID + " attempted to borrow Vinyl '" + vinyl.getTitle() +
            "', but it is already borrowed by User " + vinyl.getBorrowedByID() + "."
    );

    throw new IllegalStateException ("This vinyl is already borrowed by user: " + vinyl.getBorrowedByID());
  }

  @Override public void onReturn(Vinyl vinyl)
  {
    // Log action
    System.out.println("Returning Vinyl '" + vinyl.getTitle() + "'.");

    vinyl.setBorrowedByID(null); // sets borrowedByID to null to deem it unborrowed/returned

    if (!vinyl.isReserved()) {
      // Log transition to AvailableState
      System.out.println(
          "Vinyl '" + vinyl.getTitle() + "' is not reserved. Transitioning to AvailableState."
      );

      vinyl.changeToAvailableState();
    } else {
      // Log the transition to the reserved user borrowing the vinyl
      System.out.println(
          "Vinyl '" + vinyl.getTitle() + "' is reserved by User " + vinyl.getReservedByID() +
              ". The reserved user is now borrowing the vinyl."
      );

      vinyl.setBorrowedByID(vinyl.getReservedByID()); // the user that reserved now borrows the book
      vinyl.setReservedByID(null); // sets reservedByID null to deem it unreserved
      vinyl.setIsReserved(false); // since the reserver became the borrower, it is not reserved anymore
    }
  }

  @Override public void onReserve(Vinyl vinyl, Integer userID)
  {
    // Log action
    System.out.println("User " + userID + " is attempting to reserve Vinyl '" + vinyl.getTitle() + "'.");

    if (!vinyl.isReserved()) {
      vinyl.setReservedByID(userID);
      vinyl.setIsReserved(true);

      // Log result
      System.out.println(
          "Vinyl '" + vinyl.getTitle() + "' is now reserved by User " + userID + "."
      );
    } else {
      // Log error
      System.out.println(
          "Error: User " + userID + " attempted to reserve Vinyl '" + vinyl.getTitle() +
              "', but it is already reserved by User " + vinyl.getReservedByID() + "."
      );

      throw new IllegalStateException ("This vinyl is already reserved by user: " + vinyl.getReservedByID());
    }
  }

  @Override public void onCancelReservation(Vinyl vinyl)
  {
    // Logaction
    System.out.println("Canceling reservation for Vinyl '" + vinyl.getTitle() + "'.");

    vinyl.setReservedByID(null); // sets reservedByID null to unreserve, so other users can reserve
    vinyl.setIsReserved(false);

    // Log result
    System.out.println("Reservation for Vinyl '" + vinyl.getTitle() + "' has been canceled.");
  }
}