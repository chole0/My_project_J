package Text_work;
public class Text_class_Animal_voice{
     private interface Animal{
         void cry();
         String getAnimalName();
    }

    private static class Dog implements Animal{

         public void cry(){
             System.out.println("wow");
         }

          public String getAnimalName(){
             return "dog";
         }
    }

   private static class Cat implements Animal{

        public void cry(){
            System.out.println("miu~");
        }

        public String getAnimalName(){
            return "cat";
        }
    }

     private static class Simulator{
         void playsound(Animal animal){
             System.out.printf("该动物是: %s\n",animal.getAnimalName());
             System.out.printf("它的叫声是：");
             animal .cry();
         }
    }
    public  static void main(String []args){
         Simulator simulator = new Simulator();
         simulator.playsound(new Dog());
         simulator.playsound(new Cat());


    }


  }