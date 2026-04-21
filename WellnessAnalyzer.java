import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WellnessAnalyzer {
   public static void main(String[] args) throws FileNotFoundException {

       ArrayList<Person> people = new ArrayList<>();
       //TODO - fill in the following, to read data from a file, use the data to construct
       // specific Person objects (Student, Teacher, and Staff), and store in a List of Person (polymorphism)
       Scanner file = new Scanner(new File("wellness_data.csv"));  //construct Scanner object using appropriate filename (such as wellness_data.csv)
       file.nextLine(); // skip header line.

       while (file.hasNextLine()) { //add appropriate while condition here.
           String line = file.nextLine();// code to read the next line from the .csv file
           String[] parts = line.split(",");// code to split the line into an array of String, using commas as delimiters (separators)

           String role = parts[0]; // store the first item in a variable
           String name = parts[1];// store the second item in a variable
           int step =  Integer.parseInt(parts[2]);// store the third item in a variable
           double sleep = Double.parseDouble(parts[3]);//store the fourth item in a variable - need to do math on this one, so parse as a double
           double water = Double.parseDouble(parts[4]);//store the fifth  item in a variable - need to do math on this one, so parse as a double
            Person p = null;
           if (role.equals("student")) {
                p = new Student(name, step, sleep, water);
               //construct Student object, and add to list of Persons.  This is polymorphism.
           } else if (role.equals("teacher")) {
                p = new Teacher(name, step, sleep, water);
               //construct Teacher object, and add to list of Persons.  This is polymorphism.
           } else if (role.equals("staff")) {
                p = new Staff(name, step, sleep, water);
               //construct Saff object, and add to list of Persons.  This is polymorphism.
           }

           if (p != null){
                double wellnessScore = p.calculateWellnessScore();
                int index = 0;
                if (people.size() == 0 || wellnessScore <= people.get(0).calculateWellnessScore())
                    people.add(0,p);
                else if (wellnessScore >= people.get(people.size() - 1).calculateWellnessScore()){
                    people.add(0,p);
                }
                else
                    for (int i = 0; i<people.size(); i++){
                        if(people.get(i).calculateWellnessScore() > wellnessScore){
                            index = i;
                            people.add(index,p);
                            break;
                        }
                    }
           }
       }
           file.close();
        

           if (people.size() > 0){
                
                double total = 0; 
                double avg = 0;
                double avgStudent = 0;
                double avgTeacher = 0;
                double avgStaff = 0;
                double max = Double.MAX_VALUE;
                double min = Double.MIN_VALUE;
                double studentTotalScore = 0;
                double teacherTotalScore = 0;
                double staffTotalScore = 0;
                double studentTotal = 0;
                double teacherTotal = 0;
                double staffTotal = 0;

                for (Person p: people){
                    total += p.calculateWellnessScore();
                    if (p.calculateWellnessScore() > max){
                        max = p.calculateWellnessScore();
                    }
                    if (p.calculateWellnessScore() < min){
                        min = p.calculateWellnessScore();
                    }
                    if (p.getRole().equals("student")){
                        studentTotal += p.calculateWellnessScore();
                        studentTotalScore++;
                    }
                    if (p.getRole().equals("teacher")){
                        teacherTotal += p.calculateWellnessScore();
                        teacherTotalScore++;
                    }
                    if (p.getRole().equals("staff")){
                        staffTotal += p.calculateWellnessScore();
                        staffTotalScore++;
                    }

                }
                avg = total / people.size();
                avgStudent = studentTotalScore / studentTotal;
                avgTeacher = teacherTotalScore / teacherTotal;
                avgStaff = staffTotalScore / staffTotal;

                System.out.println(avg);
                System.out.println(min);
                System.out.println(max);
                System.out.println(avgStudent);
                System.out.println(avgTeacher);
                System.out.println(avgStaff);
       }


       // TODO:
       // - compute statistics
       // - print results
    }
}

