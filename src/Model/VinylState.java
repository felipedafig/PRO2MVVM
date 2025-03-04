package Model;

public interface VinylState
{

  void onBorrow(Vinyl vinyl, Integer userID);
  void onReturn(Vinyl vinyl);
  void onReserve(Vinyl vinyl, Integer userID);
  void onCancelReservation(Vinyl vinyl);
}
