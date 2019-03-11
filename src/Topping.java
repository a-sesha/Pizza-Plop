public abstract class Topping {
  private double xPos, yPos, xSpeed, ySpeed, speedRatio;
  private static double difficultyModifier = 1;

  public Topping(double speedRatio) {
    this.speedRatio = speedRatio;
  }

  public void setPosition(double xPos, double yPos) {
    this.xPos = xPos;
    this.yPos = yPos;
  }

  public void setSpeeds(double xSpeed, double ySpeed) {
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
  }

  public void changeSpeed(double xAccel, double yAccel) {
    this.xSpeed += xAccel/30;
    this.ySpeed += yAccel/30;
  }

  public void update() {
    this.xPos += xSpeed * speedRatio * difficultyModifier;
    this.yPos += ySpeed * speedRatio * difficultyModifier;
  }

  public abstract String getImg();

  public static Topping[] getList() {
    return new Topping[] {new Avocado(), new Bacon(), new Pineapple()};
  }
}
