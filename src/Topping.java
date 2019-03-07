public abstract class Topping {
  private double xPos, yPos, xSpeed, ySpeed;

  public void setPosition(double xPos, double yPos) {
    this.xPos = xPos;
    this.yPos = yPos;
  }

  public void setSpeeds(double xSpeed, double ySpeed) {
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
  }
  public void update() {
    this.xPos += xSpeed;
    this.yPos += ySpeed;
  }
}
