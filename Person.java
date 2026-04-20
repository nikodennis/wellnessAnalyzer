public abstract class Person {
   private String name;
   private int steps;
   private double sleep;
   private double water;

   public Person(String name, int steps, double sleep, double water) {
       this.name = name;
       this.steps = steps;
       this.sleep = sleep;
       this.water = water;
   }

   public String getName() {
       return name;
   }

   public int getSteps() {
       return steps;
   }

   public double getSleep() {
       return sleep;
   }

   public double getWater() {
       return water;
   }

   public abstract double calculateWellnessScore();

   public abstract String getRole();
}
