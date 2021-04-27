/**
 * ***************************************************************
 * JADE - Java Agent DEvelopment Framework is a framework to develop
 * multi-agent systems in compliance with the FIPA specifications.
 * Copyright (C) 2000 CSELT S.p.A.
 * 
 * GNU Lesser General Public License
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation,
 * version 2.1 of the License.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA  02111-1307, USA.
 * **************************************************************
 */

package examples.behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import java.util.Scanner;
/**
 * This example shows the basic usage of JADE behaviours.<br>
 * More in details this agent executes a <code>CyclicBehaviour</code> that shows
 * a printout at each round and a generic behaviour that performs four successive
 * "dummy" operations. The second operation in particular involves adding a
 * <code>OneShotBehaviour</code>. When the generic behaviour completes the
 * agent terminates.
 * @author Giovanni Caire - TILAB
 */
public class SimpleAgent extends Agent {
    static double[] x = new double[] {1,2,3,4,5,6,7,8,9};
      	static double[] y = new double[] {23,26,30,34,43,48,52,57,58};
public static double b1;
	public static double b0;
  protected void setup() {
    System.out.println("Agent "+getLocalName()+" started.");

    // Add the CyclicBehaviour
    addBehaviour(new CyclicBehaviour(this) {
      public void action() {
        System.out.println("Cycling");
      } 
    });

    // Add the generic behaviour
    addBehaviour(new FourStepBehaviour());
  } 

  /**
   * Inner class FourStepBehaviour
   */
  private class FourStepBehaviour extends Behaviour {
    private int step = 1;

    public void action() {
      switch (step) {
      case 1:
        // Perform operation 1: print out a message
        System.out.println("Operation 1");
        break;
      case 2:
        // Perform operation 2: Add a OneShotBehaviour
        System.out.println("Operation 2. Adding one-shot behaviour");
        myAgent.addBehaviour(new OneShotBehaviour(myAgent) {
          public void action() {
            System.out.println("One-shot");
          } 
        });
        break;
      case 3:
        // Perform operation 3: print out a message
        int n;
		double SUMX=0,SUMY=0,SUMXY=0,SUMX2=0;
		double[] x=getx();
		double[] y=gety();
		n=x.length;
		for(int i=0; i < n; i++) {
			SUMX= SUMX + x[i];
			SUMY= SUMY + y[i];
			SUMXY= SUMXY + x[i]*y[i];
			SUMX2= SUMX2 + x[i]*x[i];
		}
		SimpleAgent Dataset1= new SimpleAgent();
		Dataset1.calculation(SUMX, SUMY, SUMXY, SUMX2, n);
        break;
      case 4:
        // Perform operation 3: print out a message
        System.out.println("Operation 4");
        break;
      }
      step++;
    } 

    public boolean done() {
      return step == 5;
    } 

    public int onEnd() {
      myAgent.doDelete();
      return super.onEnd();
    } 
  }    // END of inner class FourStepBehaviour
public void calculation(double Year,double SUMY,double adversing,double Data2,int n) {
		
		double B1, B0;
		B1= (n*adversing-Year*SUMY)/(n*Data2-Year*Year);
		B0= (SUMY*Data2-Year*adversing)/(n*Data2-Year*Year);
		setB1(B1);
		setB0(B0);
		String ValorN0 = (String) String.format("%.2f", B1);
		Double newValue = Double.parseDouble(ValorN0);
		String ValorN1 = (String) String.format("%.2f", B0);
		Double newValue2 = Double.parseDouble(ValorN1);
		result(newValue2,newValue);
		
		double P, prediction=0;
		Scanner read = new Scanner(System.in);
		System.out.print("\n\n Prediction value x: ");
		P=read.nextDouble();
		prediction=B0+B1*P;
		System.out.println("\n"+prediction);
	}

	public void result(double B0,double B1) {
		System.out.println("Y de hat = "+B0+" + "+B1+" x ");
		SimpleAgent.setB0(B0);
		SimpleAgent.setB1(B1);
	}
	
	public static void setB0(double b0) {
		SimpleAgent.b0 = b0;
	}
	
	public static void setB1(double b1) {
		SimpleAgent.b1 = b1;
	}
        public static double[] getx() {
		return x.clone();
	}
	public static double[] gety() {
		return y.clone();
	}
}

