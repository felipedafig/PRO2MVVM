package Model;

public interface VinylState
{

  void onBorrow(Vinyl vinyl, int userID);
  void onReturn(Vinyl vinyl);
  void onReserve(Vinyl vinyl, int userID);
  void onCancelReservation(Vinyl vinyl);
  void onRemove(Vinyl vinyl);

}
