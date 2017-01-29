/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lach.fizyka.w.programowaniu.gier;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author Mateusz L
 */
public class LachFizykaWProgramowaniuGier {
static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        
        double force, frictionRate ;
        int x = 0;
        System.out.println("Wprowadz sile oddzialujaca na uklad");
        force = in.nextDouble();in.nextLine();
        System.out.println("Okresl wspolczynnik tarcia");
        frictionRate = in.nextDouble();in.nextLine();
        
        new Calculation(force, frictionRate);
       
        while (true){
            System.out.println("Wybierz pojemnik ktory chcesz zobaczyc - dostepne opcje 1-3");
            do{   
                try{
                    x=Integer.parseInt(readData());
                }catch(InputMismatchException ime){
                     System.err.println("Podano bledne dane "+ime);
                     in.nextLine();
                }catch(NumberFormatException nfe){
                    System.err.println("Blad formatu liczby");
                }catch(Exception e){
                    System.err.println("inny wyjatek "+e.getMessage());
                    
                }
            }while(x == 0);
            switch(x){
                case 1:
                    Calculation.containers[0].present();
                    break;
                case 2:
                    Calculation.containers[1].present();
                    break;
                case 3:
                    Calculation.containers[2].present();
                    break;
                default:
                    System.err.println("Podano zla wartosc");
            }
        }
        
    }//main
    
    public static String readData(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    
}

class Container {
        private static int idCounter = 0;
        private int id;
        private double mass;            //masa obiektu
        private double nForward;        //sila naciagu nici z przodu
        private double nBackward;       //sila naciagu nici z tylu
        private double friction;        //tarcie
        
        
        public Container (double mass) {
            this.mass = mass;
            id=++idCounter;
        }
        
        public void setMass(double mass){this.mass = mass;}
        public void setNForward(double n){this.nForward = n; }
        public void setNBackward(double n){this.nBackward = n;}
        public void setFriction(double n){this.friction = n;}
        public double getMass(){return this.mass;}
        public double getFriction(){return this.friction;}
    
        public void present(){
            System.out.println("\nID: " + this.id);
            System.out.println("Masa: " + this.mass + "kg");
            System.out.println("Tarcie: " + this.friction);
            System.out.print(this.nBackward + " N");
            System.out.print("<--[   ]-->");
            System.out.println(this.nForward + " N" );
        }
        
}//class Container


class Calculation {
        static Scanner in = new Scanner(System.in);
        final double g = 9.8;          //stala grawitacji
        private double frictionRate;               //wspolczynnik tarcia
        private double force;           //sila ciagnaca ciala do przodu
        private double acceleration;    //przyspieszenie
        public static Container [] containers = new Container[3]; // tablica zawierajaca 3 pojemniki
       
        
        
        public Calculation(double f,double u){
            this.force = f;
            this.frictionRate = u;
            
            /*containers[0] = new Container (1);
            containers[1] = new Container (5);
            containers[2] = new Container (2);*/
            
            for (int i = 0; i<3; i++ ){
                System.out.println("Podaj mase dla "+ (i+1) + " pojemnika");
                containers[i] = new Container (in.nextInt());
                in.nextLine();
                containers[i].setFriction(calculateFriction(containers[i]));
            }
            calculateAcceleration();
            double n2 = calculateTension(0,0);
            double n1 = -(calculateTension(this.force,2)); //na ostatnie cialo oddzialowuje sila
            
            containers[0].setNForward(n2);
            containers[1].setNBackward(n2);
            containers[1].setNForward(n1);
            containers[2].setNBackward(n1);
           // System.out.println ("N1 wynosi: " + n1 + " N2 wynosi: "+ n2);
        }
        
        public void setForce(double f){
            this.force = f;
        }
        
        public void setFrictionRate(double u){
            this.frictionRate = u;
        }
        public double calculateAcceleration(){
            double x;
            double totalMass = 0;
            for (int i = 0; i<3; i++ )
                totalMass += containers[i].getMass();
            
           x = (force - frictionRate * g * totalMass)/totalMass;
           this.acceleration = x;
           if(x>0)
            System.out.println("Przyspieszenie ukladu wynosi: " + x + "m/s^");
           else
            System.out.println("Na uklad dziala zbyt mala sila by go poruszyc");
           return x;
        }
        
        private double calculateFriction(Container C){
            return frictionRate * C.getMass() * g;  
        }
        
        private double calculateTension (double force, int i){ //oblicza sile naciagu nici
            return acceleration * containers[i].getMass() - force + containers[i].getFriction();
        }

        
    }


