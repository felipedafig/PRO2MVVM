package Model;

public class User
{

  private int userID;


  public User(int userID){

    this.userID = userID;
  }


  public String toString(){

    return "User: " + userID;
  }
}
