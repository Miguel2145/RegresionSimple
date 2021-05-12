package examples.bookTrading;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author migue
 */

public class Funciones {
    Scanner sc =new Scanner (System.in);
    public void FracmentosBeta(double []t,double []x, int n){
         double XT = 0;double X = 0;double T=0;double T2=0;double Tt=0;double B;double aux = 0;double a;
         double PX,PT,PS; PX=0;PT=0;PS=0;
         aux=x.length; 
        //la sumatoria de los valores de X (ventas) multiplicado por el t (tiempo).
         for(int i=0;i<t.length;i++){
            XT=(XT+=(x[i]*t[i]));
        }
    System.out.println("Xt (Ventas * Tiempo)= "+XT);  
    //La sumatoria de de los valores de X (ventas)
    for(int i=0;i<t.length;i++){
        X=(X+=x[i]);
    }
    System.out.println("X (ventas)= "+X);
    //La sumatoria de t (tiempo).
    for(int i=0;i<t.length;i++){
            T=(T+=t[i]);
        }
    System.out.println("T (tiempo)= "+T);
    //La sumatoria de los valores de t (tiempo) elevados al cuadrado.
     for(int i=0;i<t.length;i++){
            T2=(T2+=Math.pow(t[i], 2));
        }
    System.out.println("T^2 (tiempo)^2= "+T2);
    //La sumatoria de de t (tiempo) elevada al cuadrado.
    Tt= Math.pow(T, 2);
    System.out.println("Tiempo total Tt^2 (tiempo total)^2= "+Tt);
    //sustitucion  a ß
    System.out.println("Ahora sacaremos ß beta\n que es ((n*XT)-(X*T))/((n*T)-(Tt^2))");
    B=((aux*XT)-(X*T))/((aux*T2)-(Tt));
    System.out.println("Beta ß es = "+B);
    
    //Media o promedio de X
        for(int i=0;i<t.length;i++){
        PX=(PX+=x[i]);
        }
        System.out.println("el Promedio de X es = "+PX/aux);
    //Media o promedio de t
        for(int i=0;i<t.length;i++){
            PT=(PT+=t[i]);
        }
         System.out.println("el Promedio de t es = "+PT/aux);
         System.out.println("ahora sustitucion de la funcion \n"
                 + "a= Promedio X-  ß* promedio t");
         a=(PX/aux)-(B*(PT/aux));
         System.out.println("a es igual = "+a);
         //Regresión lineal
            
            PS=a+(B*n);
            System.out.println("EL pronostico es "+PS);
    }
    
}
