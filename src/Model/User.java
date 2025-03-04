package Model;

public class User
{

  private Integer userID;

  public User(Integer userID){

    this.userID = userID;
  }

  public String toString(){

    return "User: " + userID;
  }
}
