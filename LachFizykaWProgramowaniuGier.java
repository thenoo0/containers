/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lach.fizyka.w.programowaniu.gier;

import java.util.Scanner;

/**
 *
 * @author Mateusz L
 */
public class LachFizykaWProgramowaniuGier {
static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        
        Calculation calculation = new Calculation(80, 0.2);
        System.out.println(calculation.calculateAcceleration());

    }
    
}

class Container {
        private double mass;            //masa obiektu
        private double nForward;        //sila naciagu nici z przodu
        private double nBackward;       //sila naciagu nici z tylu

        
        
        public Container (double mass) {
            this.mass = mass;

        }
        
        public void setMass(double mass){
            this.mass = mass;
        }
        
        public double getMass(){return this.mass;}
    
    
}//class Container


class Calculation {
        static Scanner in = new Scanner(System.in);
        final double g = 9.81;          //stala grawitacji
        private double frictionRate;               //wspolczynnik tarcia
        private double force;           //sila ciagnaca ciala do przodu
        public Container [] containers = new Container[3]; // tablica zawierajaca 3 pojemniki
        private double [] friction = new double [3];   //tablica zwierajaca tarcie dla 3ech pojemnikow
        private double [] tension = new double [2];
       
        
        
        public Calculation(double f,double u){
            this.force = f;
            this.frictionRate = u;
            for (int i = 0; i<3; i++ ){
                System.out.println("Podaj mase dla "+ i + " pojemnika");
                containers[i] = new Container (in.nextInt());
                in.nextLine();
                friction[i] = calculateFriction(containers[i]);
            }
            
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
            return x;
        }
        
        private double calculateFriction(Container C){
            double x;
            x = frictionRate * C.getMass() * g;
            return x;
        }
        


        
    }


