package org.vinfoil;

import org.vinfoil.thread.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;


public class OneOnTwoRepass {

    public OneOnTwoRepass() {

    }

    //obavezno moras da izvrsis porveru koja lane je veca od koje prva mora da bude najvece u suprtnom dobices sranje
    //!!!!!!!!!!!!!!
    public List<Direction1andDirection2> OneOnTwoRepassRun(FWL lane1, FWL lane2, FWL lane3, Patterns Shape, int ScanStep) {
        Discretization dis1 = new Discretization();
        MinAndMax templane1 = new MinAndMax();
        MinAndMax templane2 = new MinAndMax();
        MinAndMax templane3 = new MinAndMax();
        SetOfPatternsForALane patternslane1 = new SetOfPatternsForALane();
        SetOfPatternsForALane patternslane2 = new SetOfPatternsForALane();
        SetOfPatternsForALane patternslane3 = new SetOfPatternsForALane();
        Patterns nesto = patternslane2.PatternsForOneLane(Shape, lane2);
        int n = (int) (lane1.getWidth() - 1);
        int m = (int) (lane1.getLength() - 1);
        int X2min = (int) (templane2.TheXminOfAllPatterns(patternslane2.PatternsForOneLane(Shape, lane2)) - lane2.getStart());
        int X2max = (int) (templane2.TheXmaxOfAllPatterns(nesto) - lane2.getStart());
        int Y2min = (int) (templane2.TheYminOfAllPatterns(patternslane2.PatternsForOneLane(Shape, lane2)));
        int Y2max = (int) (templane2.TheYmaxOfAllPatterns(patternslane2.PatternsForOneLane(Shape, lane2)));
        int X1min = (int) (templane1.TheXminOfAllPatterns(patternslane1.PatternsForOneLane(Shape, lane1)) - lane1.getStart());
        int X1max = (int) (templane1.TheXmaxOfAllPatterns(patternslane1.PatternsForOneLane(Shape, lane1)) - lane1.getStart());
        int Y1min = (int) (templane1.TheYminOfAllPatterns(patternslane1.PatternsForOneLane(Shape, lane1)));
        int Y1max = (int) (templane1.TheYmaxOfAllPatterns(patternslane1.PatternsForOneLane(Shape, lane1)));
        int X3min = (int) (templane3.TheXminOfAllPatterns(patternslane3.PatternsForOneLane(Shape, lane3)) - lane3.getStart());
        int X3max = (int) (templane3.TheXmaxOfAllPatterns(patternslane3.PatternsForOneLane(Shape, lane3)) - lane3.getStart());
        int Y3min = (int) (templane3.TheYminOfAllPatterns(patternslane3.PatternsForOneLane(Shape, lane3)));
        int Y3max = (int) (templane3.TheYmaxOfAllPatterns(patternslane3.PatternsForOneLane(Shape, lane3)));
        int[][] matrixdis2 = dis1.DiscretizationOfLane(lane2, Shape);
        //the first one is fixed and to it we add 20mm
        int[][] matrixdis1 = dis1.DiscretizationOfLaneAddingScanStep(lane1, Shape, ScanStep);
        //fixing the second lane and adding ScanStep
        int[][] matrixdis3 = dis1.DiscretizationOfLaneAddingScanStep(lane2, Shape, ScanStep);
        int[][] matrixdis4 = dis1.DiscretizationOfLane(lane3, Shape);
        ArrayList<HoldingResult> HS1 = new ArrayList<HoldingResult>();
        List<HoldingResult> HS = Collections.synchronizedList(new ArrayList<HoldingResult>());
        Direction1andDirection2 HR = new Direction1andDirection2(0, 0, 0, 0, 0, 0);
        List<Direction1andDirection2> templist = Collections.synchronizedList(new ArrayList<Direction1andDirection2>());
        ThreadHoldingConstraint11 thc11 = new ThreadHoldingConstraint11(HS);
        ThreadHoldingConstraint21 thc21 = new ThreadHoldingConstraint21(HS);
        ThreadHoldingConstraint31 thc31 = new ThreadHoldingConstraint31(HS);
        ThreadHoldingConstraint41 thc41 = new ThreadHoldingConstraint41(HS);
        ThreadHoldingConstraint51 thc51 = new ThreadHoldingConstraint51(HS);
        ThreadHoldingConstraint61 thc61 = new ThreadHoldingConstraint61(HS);
        ThreadHoldingConstraint71 thc71 = new ThreadHoldingConstraint71(HS);
        ThreadHoldingConstraint81 thc81 = new ThreadHoldingConstraint81(HS);
        THC1 thc1 = new THC1();
        THC2 thc2 = new THC2();
        THC3 thc3 = new THC3();
        THC4 thc4 = new THC4();
        THC5 thc5 = new THC5();
        THC6 thc6 = new THC6();
        THC7 thc7 = new THC7();
        THC8 thc8 = new THC8();
        //sada moram da napravim nove bez embedinga
        if (X1min - ScanStep < 0)
            X1min = 0;
        else X1min = X1min - ScanStep;
        if (X1max + 20 > lane1.getWidth() - 1)
            X1max = (int) lane1.getWidth() - 1;
        else X1max = X1max + ScanStep;
        if (Y1min - ScanStep < 0)
            Y1min = 0;
        else Y1min = Y1min - ScanStep;
        if (Y1max + ScanStep > lane1.getLength() - 1)
            Y1max = (int) lane1.getLength() - 1;
        else Y1max = Y1max + ScanStep;
        thc11.setDiscreteLane1(matrixdis2);
        thc11.setDiscreteLane2(matrixdis1);
        thc11.setX1min(X1min);
        thc11.setX1max(X1max);
        thc11.setX2min(X2min);
        thc11.setX2max(X2max);
        thc11.setY1min(Y1min);
        thc11.setY1max(Y1max);
        thc11.setY2min(Y2min);
        thc11.setY2max(Y2max);
        thc11.setN1((int) lane1.getWidth());
        thc11.setN2((int) lane2.getWidth());
        thc11.setM((int) lane1.getLength());
        thc11.setScanStep(ScanStep);

        thc21.setDiscreteLane1(matrixdis2);
        thc21.setDiscreteLane2(matrixdis1);
        thc21.setX1min(X1min);
        thc21.setX1max(X1max);
        thc21.setX2min(X2min);
        thc21.setX2max(X2max);
        thc21.setY1min(Y1min);
        thc21.setY1max(Y1max);
        thc21.setY2min(Y2min);
        thc21.setY2max(Y2max);
        thc21.setN1((int) lane1.getWidth());
        thc21.setN2((int) lane2.getWidth());
        thc21.setM((int) lane1.getLength());
        thc21.setScanStep(ScanStep);

        thc31.setDiscreteLane1(matrixdis2);
        thc31.setDiscreteLane2(matrixdis1);
        thc31.setX1min(X1min);
        thc31.setX1max(X1max);
        thc31.setX2min(X2min);
        thc31.setX2max(X2max);
        thc31.setY1min(Y1min);
        thc31.setY1max(Y1max);
        thc31.setY2min(Y2min);
        thc31.setY2max(Y2max);
        thc31.setN1((int) lane1.getWidth());
        thc31.setN2((int) lane2.getWidth());
        thc31.setM((int) lane1.getLength());
        thc31.setScanStep(ScanStep);

        thc41.setDiscreteLane1(matrixdis2);
        thc41.setDiscreteLane2(matrixdis1);
        thc41.setX1min(X1min);
        thc41.setX1max(X1max);
        thc41.setX2min(X2min);
        thc41.setX2max(X2max);
        thc41.setY1min(Y1min);
        thc41.setY1max(Y1max);
        thc41.setY2min(Y2min);
        thc41.setY2max(Y2max);
        thc41.setN1((int) lane1.getWidth());
        thc41.setN2((int) lane2.getWidth());
        thc41.setM((int) lane1.getLength());
        thc41.setScanStep(ScanStep);

        thc51.setDiscreteLane1(matrixdis2);
        thc51.setDiscreteLane2(matrixdis1);
        thc51.setX1min(X1min);
        thc51.setX1max(X1max);
        thc51.setX2min(X2min);
        thc51.setX2max(X2max);
        thc51.setY1min(Y1min);
        thc51.setY1max(Y1max);
        thc51.setY2min(Y2min);
        thc51.setY2max(Y2max);
        thc51.setN1((int) lane1.getWidth());
        thc51.setN2((int) lane2.getWidth());
        thc51.setM((int) lane1.getLength());
        thc51.setScanStep(ScanStep);

        thc61.setDiscreteLane1(matrixdis2);
        thc61.setDiscreteLane2(matrixdis1);
        thc61.setX1min(X1min);
        thc61.setX1max(X1max);
        thc61.setX2min(X2min);
        thc61.setX2max(X2max);
        thc61.setY1min(Y1min);
        thc61.setY1max(Y1max);
        thc61.setY2min(Y2min);
        thc61.setY2max(Y2max);
        thc61.setN1((int) lane1.getWidth());
        thc61.setN2((int) lane2.getWidth());
        thc61.setM((int) lane1.getLength());
        thc61.setScanStep(ScanStep);

        thc71.setDiscreteLane1(matrixdis2);
        thc71.setDiscreteLane2(matrixdis1);
        thc71.setX1min(X1min);
        thc71.setX1max(X1max);
        thc71.setX2min(X2min);
        thc71.setX2max(X2max);
        thc71.setY1min(Y1min);
        thc71.setY1max(Y1max);
        thc71.setY2min(Y2min);
        thc71.setY2max(Y2max);
        thc71.setN1((int) lane1.getWidth());
        thc71.setN2((int) lane2.getWidth());
        thc71.setM((int) lane1.getLength());
        thc71.setScanStep(ScanStep);

        thc81.setDiscreteLane1(matrixdis2);
        thc81.setDiscreteLane2(matrixdis1);
        thc81.setX1min(X1min);
        thc81.setX1max(X1max);
        thc81.setX2min(X2min);
        thc81.setX2max(X2max);
        thc81.setY1min(Y1min);
        thc81.setY1max(Y1max);
        thc81.setY2min(Y2min);
        thc81.setY2max(Y2max);
        thc81.setN1((int) lane1.getWidth());
        thc81.setN2((int) lane2.getWidth());
        thc81.setM((int) lane1.getLength());
        thc81.setScanStep(ScanStep);
        //ArrayList <org.vinfoil.HoldingResult> HS=new ArrayList <org.vinfoil.HoldingResult>();

        thc11.start();
        thc21.start();
        thc31.start();
        thc41.start();
        thc51.start();
        thc61.start();
        thc71.start();
        thc81.start();

        while (thc11.isAlive()
                && thc21.isAlive()
                && thc31.isAlive()
                && thc41.isAlive()
                && thc51.isAlive()
                && thc61.isAlive()
                && thc71.isAlive()
                && thc81.isAlive()) {
            continue;
        }

        boolean t = false;
        int i = 0;
        //idemo jedan po jedan
        //direction 1
        synchronized (HS) {
            Iterator<HoldingResult> iterator1 = HS.iterator();
            while (iterator1.hasNext() && t == false) {

                //napravi formulu za racunanje novih min and max za novu lane
                int X2minTemp = X2min + thc11.getHS().get(i).getShiftX();
                int X2maxTemp = X2max + thc11.getHS().get(i).getShiftX();
                int Y2minTemp = Y2min + thc11.getHS().get(i).getShiftY();
                int Y2maxTemp = Y2max + thc11.getHS().get(i).getShiftY();
                int X1minTemp = X1min;
                int X1maxTemp = X1max;
                int Y1minTemp = Y1min;
                int Y1maxTemp = Y1max;
                if (X1minTemp >= X2minTemp)
                    X1minTemp = X2minTemp;
                if (X1maxTemp <= X2maxTemp)
                    X1maxTemp = X2maxTemp;
                if (Y1minTemp >= Y2minTemp)
                    Y1minTemp = Y2minTemp;
                if (Y1maxTemp <= Y2maxTemp)
                    Y1maxTemp = Y2maxTemp;
                //pozovi objdeinjavanje iz discretization
                int[][] matrixdis5 = dis1.UnitingResults(matrixdis1, matrixdis3, HS.get(i).getShiftX(), HS.get(i).getShiftY(), HS.get(i).getDirection(), n + 1, m + 1);
                //sa novom matricom, novim min i max pozovi opet sve threadove, stim sto ces da izadjes prvi prvom rezultatu
                thc1.setDiscreteLane1(matrixdis5);
                thc1.setDiscreteLane2(matrixdis4);
                thc1.setX1min(X1minTemp);
                thc1.setX1max(X1maxTemp);
                thc1.setX2min(X3min);
                thc1.setX2max(X3max);
                thc1.setY1min(Y1minTemp);
                thc1.setY1max(Y1maxTemp);
                thc1.setY2min(Y3min);
                thc1.setY2max(Y3max);
                thc1.setDirection1(HS.get(i).getDirection());
                thc1.setShiftXForDirection1(HS.get(i).getShiftX());
                thc1.setShiftYForDirection1(HS.get(i).getShiftY());
                thc1.setN1((int) lane1.getWidth());
                thc1.setN2((int) lane3.getWidth());
                thc1.setM((int) lane1.getLength());
                thc1.setScanStep(ScanStep);

                thc1.run();
                templist.addAll(thc1.getHS());

                i++;
                if (templist.size() > 0) {
                    t = true;

                }

            }
        }
        //direction 2
        i = 0;
        synchronized (HS) {
            Iterator<HoldingResult> iterator1 = HS.iterator();
            while (iterator1.hasNext() && t == false) {
                //napravi formulu za racunanje novih min and max za novu lane
                int X2minTemp = X2min + thc11.getHS().get(i).getShiftX();
                int X2maxTemp = X2max + thc11.getHS().get(i).getShiftX();
                int Y2minTemp = Y2min + thc11.getHS().get(i).getShiftY();
                int Y2maxTemp = Y2max + thc11.getHS().get(i).getShiftY();
                int X1minTemp = X1min;
                int X1maxTemp = X1max;
                int Y1minTemp = Y1min;
                int Y1maxTemp = Y1max;
                if (X1minTemp >= X2minTemp)
                    X1minTemp = X2minTemp;
                if (X1maxTemp <= X2maxTemp)
                    X1maxTemp = X2maxTemp;
                if (Y1minTemp >= Y2minTemp)
                    Y1minTemp = Y2minTemp;
                if (Y1maxTemp <= Y2maxTemp)
                    Y1maxTemp = Y2maxTemp;
                //pozovi objdeinjavanje iz discretization
                int[][] matrixdis5 = dis1.UnitingResults(matrixdis1, matrixdis3, HS.get(i).getShiftX(), HS.get(i).getShiftY(), HS.get(i).getDirection(), n + 1, m + 1);
                //sa novom matricom, novim min i max pozovi opet sve threadove, stim sto ces da izadjes prvi prvom rezultatu

                thc2.setDiscreteLane1(matrixdis5);
                thc2.setDiscreteLane2(matrixdis4);
                thc2.setX1min(X1minTemp);
                thc2.setX1max(X1maxTemp);
                thc2.setX2min(X3min);
                thc2.setX2max(X3max);
                thc2.setY1min(Y1minTemp);
                thc2.setY1max(Y1maxTemp);
                thc2.setY2min(Y3min);
                thc2.setY2max(Y3max);
                thc2.setN1((int) lane1.getWidth());
                thc2.setN2((int) lane3.getWidth());
                thc2.setM((int) lane1.getLength());
                thc2.setScanStep(ScanStep);
                thc2.setDirection1(HS.get(i).getDirection());
                thc2.setShiftXForDirection1(HS.get(i).getShiftX());
                thc2.setShiftYForDirection1(HS.get(i).getShiftY());

                thc2.start();
                templist.addAll(thc2.getHS());

                i++;
                if (templist.size() > 0) {
                    t = true;

                }
            }
        }
        //direction3
        i = 0;
        synchronized (HS) {
            Iterator<HoldingResult> iterator1 = HS.iterator();
            while (iterator1.hasNext() && t == false) {
                //napravi formulu za racunanje novih min and max za novu lane
                int X2minTemp = X2min + thc11.getHS().get(i).getShiftX();
                int X2maxTemp = X2max + thc11.getHS().get(i).getShiftX();
                int Y2minTemp = Y2min + thc11.getHS().get(i).getShiftY();
                int Y2maxTemp = Y2max + thc11.getHS().get(i).getShiftY();
                int X1minTemp = X1min;
                int X1maxTemp = X1max;
                int Y1minTemp = Y1min;
                int Y1maxTemp = Y1max;
                if (X1minTemp >= X2minTemp)
                    X1minTemp = X2minTemp;
                if (X1maxTemp <= X2maxTemp)
                    X1maxTemp = X2maxTemp;
                if (Y1minTemp >= Y2minTemp)
                    Y1minTemp = Y2minTemp;
                if (Y1maxTemp <= Y2maxTemp)
                    Y1maxTemp = Y2maxTemp;
                //pozovi objdeinjavanje iz discretization
                int[][] matrixdis5 = dis1.UnitingResults(matrixdis1, matrixdis3, HS.get(i).getShiftX(), HS.get(i).getShiftY(), HS.get(i).getDirection(), n + 1, m + 1);
                //sa novom matricom, novim min i max pozovi opet sve threadove, stim sto ces da izadjes prvi prvom rezultatu

                thc3.setDiscreteLane1(matrixdis5);
                thc3.setDiscreteLane2(matrixdis4);
                thc3.setX1min(X1minTemp);
                thc3.setX1max(X1maxTemp);
                thc3.setX2min(X3min);
                thc3.setX2max(X3max);
                thc3.setY1min(Y1minTemp);
                thc3.setY1max(Y1maxTemp);
                thc3.setY2min(Y3min);
                thc3.setY2max(Y3max);
                thc3.setN1((int) lane1.getWidth());
                thc3.setN2((int) lane3.getWidth());
                thc3.setM((int) lane1.getLength());
                thc3.setScanStep(ScanStep);
                thc3.setDirection1(HS.get(i).getDirection());
                thc3.setShiftXForDirection1(HS.get(i).getShiftX());
                thc3.setShiftYForDirection1(HS.get(i).getShiftY());

                thc3.run();
                templist.addAll(thc3.getHS());

                i++;
                if (templist.size() > 0) {
                    t = true;

                }
            }
        }
        //direction4
        i = 0;
        synchronized (HS) {
            Iterator<HoldingResult> iterator1 = HS.iterator();
            while (iterator1.hasNext() && t == false) {
                //napravi formulu za racunanje novih min and max za novu lane
                int X2minTemp = X2min + thc11.getHS().get(i).getShiftX();
                int X2maxTemp = X2max + thc11.getHS().get(i).getShiftX();
                int Y2minTemp = Y2min + thc11.getHS().get(i).getShiftY();
                int Y2maxTemp = Y2max + thc11.getHS().get(i).getShiftY();
                int X1minTemp = X1min;
                int X1maxTemp = X1max;
                int Y1minTemp = Y1min;
                int Y1maxTemp = Y1max;
                if (X1minTemp >= X2minTemp)
                    X1minTemp = X2minTemp;
                if (X1maxTemp <= X2maxTemp)
                    X1maxTemp = X2maxTemp;
                if (Y1minTemp >= Y2minTemp)
                    Y1minTemp = Y2minTemp;
                if (Y1maxTemp <= Y2maxTemp)
                    Y1maxTemp = Y2maxTemp;
                //pozovi objdeinjavanje iz discretization
                int[][] matrixdis5 = dis1.UnitingResults(matrixdis1, matrixdis3, HS.get(i).getShiftX(), HS.get(i).getShiftY(), HS.get(i).getDirection(), n + 1, m + 1);
                //sa novom matricom, novim min i max pozovi opet sve threadove, stim sto ces da izadjes prvi prvom rezultatu

                thc4.setDiscreteLane1(matrixdis5);
                thc4.setDiscreteLane2(matrixdis4);
                thc4.setX1min(X1minTemp);
                thc4.setX1max(X1maxTemp);
                thc4.setX2min(X3min);
                thc4.setX2max(X3max);
                thc4.setY1min(Y1minTemp);
                thc4.setY1max(Y1maxTemp);
                thc4.setY2min(Y3min);
                thc4.setY2max(Y3max);
                thc4.setN1((int) lane1.getWidth());
                thc4.setN2((int) lane3.getWidth());
                thc4.setM((int) lane1.getLength());
                thc4.setScanStep(ScanStep);
                thc4.setDirection1(HS.get(i).getDirection());
                thc4.setShiftXForDirection1(HS.get(i).getShiftX());
                thc4.setShiftYForDirection1(HS.get(i).getShiftY());

                thc4.run();
                templist.addAll(thc4.getHS());

                i++;
                if (templist.size() > 0) {
                    t = true;
                }
            }
        }
        //direction5
        i = 0;
        synchronized (HS) {
            Iterator<HoldingResult> iterator1 = HS.iterator();
            while (iterator1.hasNext() && t == false) {
                //napravi formulu za racunanje novih min and max za novu lane
                int X2minTemp = X2min + thc11.getHS().get(i).getShiftX();
                int X2maxTemp = X2max + thc11.getHS().get(i).getShiftX();
                int Y2minTemp = Y2min + thc11.getHS().get(i).getShiftY();
                int Y2maxTemp = Y2max + thc11.getHS().get(i).getShiftY();
                int X1minTemp = X1min;
                int X1maxTemp = X1max;
                int Y1minTemp = Y1min;
                int Y1maxTemp = Y1max;
                if (X1minTemp >= X2minTemp)
                    X1minTemp = X2minTemp;
                if (X1maxTemp <= X2maxTemp)
                    X1maxTemp = X2maxTemp;
                if (Y1minTemp >= Y2minTemp)
                    Y1minTemp = Y2minTemp;
                if (Y1maxTemp <= Y2maxTemp)
                    Y1maxTemp = Y2maxTemp;
                //pozovi objdeinjavanje iz discretization
                int[][] matrixdis5 = dis1.UnitingResults(matrixdis1, matrixdis3, HS.get(i).getShiftX(), HS.get(i).getShiftY(), HS.get(i).getDirection(), n + 1, m + 1);
                //sa novom matricom, novim min i max pozovi opet sve threadove, stim sto ces da izadjes prvi prvom rezultatu

                thc5.setDiscreteLane1(matrixdis5);
                thc5.setDiscreteLane2(matrixdis4);
                thc5.setX1min(X1minTemp);
                thc5.setX1max(X1maxTemp);
                thc5.setX2min(X3min);
                thc5.setX2max(X3max);
                thc5.setY1min(Y1minTemp);
                thc5.setY1max(Y1maxTemp);
                thc5.setY2min(Y3min);
                thc5.setY2max(Y3max);
                thc5.setN1((int) lane1.getWidth());
                thc5.setN2((int) lane3.getWidth());
                thc5.setM((int) lane1.getLength());
                thc5.setScanStep(ScanStep);
                thc5.setDirection1(HS.get(i).getDirection());
                thc5.setShiftXForDirection1(HS.get(i).getShiftX());
                thc5.setShiftYForDirection1(HS.get(i).getShiftY());

                thc5.run();
                templist.addAll(thc5.getHS());

                i++;
                if (templist.size() > 0) {
                    t = true;
                }
            }
        }
        //direction6
        i = 0;
        synchronized (HS) {
            Iterator<HoldingResult> iterator1 = HS.iterator();
            while (iterator1.hasNext() && t == false) {
                //napravi formulu za racunanje novih min and max za novu lane
                int X2minTemp = X2min + thc11.getHS().get(i).getShiftX();
                int X2maxTemp = X2max + thc11.getHS().get(i).getShiftX();
                int Y2minTemp = Y2min + thc11.getHS().get(i).getShiftY();
                int Y2maxTemp = Y2max + thc11.getHS().get(i).getShiftY();
                int X1minTemp = X1min;
                int X1maxTemp = X1max;
                int Y1minTemp = Y1min;
                int Y1maxTemp = Y1max;
                if (X1minTemp >= X2minTemp)
                    X1minTemp = X2minTemp;
                if (X1maxTemp <= X2maxTemp)
                    X1maxTemp = X2maxTemp;
                if (Y1minTemp >= Y2minTemp)
                    Y1minTemp = Y2minTemp;
                if (Y1maxTemp <= Y2maxTemp)
                    Y1maxTemp = Y2maxTemp;
                //pozovi objdeinjavanje iz discretization
                int[][] matrixdis5 = dis1.UnitingResults(matrixdis1, matrixdis3, HS.get(i).getShiftX(), HS.get(i).getShiftY(), HS.get(i).getDirection(), n + 1, m + 1);
                //sa novom matricom, novim min i max pozovi opet sve threadove, stim sto ces da izadjes prvi prvom rezultatu

                thc6.setDiscreteLane1(matrixdis5);
                thc6.setDiscreteLane2(matrixdis4);
                thc6.setX1min(X1minTemp);
                thc6.setX1max(X1maxTemp);
                thc6.setX2min(X3min);
                thc6.setX2max(X3max);
                thc6.setY1min(Y1minTemp);
                thc6.setY1max(Y1maxTemp);
                thc6.setY2min(Y3min);
                thc6.setY2max(Y3max);
                thc6.setN1((int) lane1.getWidth());
                thc6.setN2((int) lane3.getWidth());
                thc6.setM((int) lane1.getLength());
                thc6.setScanStep(ScanStep);
                thc6.setDirection1(HS.get(i).getDirection());
                thc6.setShiftXForDirection1(HS.get(i).getShiftX());
                thc6.setShiftYForDirection1(HS.get(i).getShiftY());

                thc6.run();
                templist.addAll(thc6.getHS());

                i++;
                if (templist.size() > 0) {
                    t = true;

                }
            }
        }
        //direction7
        i = 0;
        synchronized (HS) {
            Iterator<HoldingResult> iterator1 = HS.iterator();
            while (iterator1.hasNext() && t == false) {

                //napravi formulu za racunanje novih min and max za novu lane
                int X2minTemp = X2min + thc11.getHS().get(i).getShiftX();
                int X2maxTemp = X2max + thc11.getHS().get(i).getShiftX();
                int Y2minTemp = Y2min + thc11.getHS().get(i).getShiftY();
                int Y2maxTemp = Y2max + thc11.getHS().get(i).getShiftY();
                int X1minTemp = X1min;
                int X1maxTemp = X1max;
                int Y1minTemp = Y1min;
                int Y1maxTemp = Y1max;
                if (X1minTemp >= X2minTemp)
                    X1minTemp = X2minTemp;
                if (X1maxTemp <= X2maxTemp)
                    X1maxTemp = X2maxTemp;
                if (Y1minTemp >= Y2minTemp)
                    Y1minTemp = Y2minTemp;
                if (Y1maxTemp <= Y2maxTemp)
                    Y1maxTemp = Y2maxTemp;
                //pozovi objdeinjavanje iz discretization
                int[][] matrixdis5 = dis1.UnitingResults(matrixdis1, matrixdis3, HS.get(i).getShiftX(), HS.get(i).getShiftY(), HS.get(i).getDirection(), n + 1, m + 1);
                //sa novom matricom, novim min i max pozovi opet sve threadove, stim sto ces da izadjes prvi prvom rezultatu

                thc7.setDiscreteLane1(matrixdis5);
                thc7.setDiscreteLane2(matrixdis4);
                thc7.setX1min(X1minTemp);
                thc7.setX1max(X1maxTemp);
                thc7.setX2min(X3min);
                thc7.setX2max(X3max);
                thc7.setY1min(Y1minTemp);
                thc7.setY1max(Y1maxTemp);
                thc7.setY2min(Y3min);
                thc7.setY2max(Y3max);
                thc7.setN1((int) lane1.getWidth());
                thc7.setN2((int) lane3.getWidth());
                thc7.setM((int) lane1.getLength());
                thc7.setScanStep(ScanStep);
                thc7.setDirection1(HS.get(i).getDirection());
                thc7.setShiftXForDirection1(HS.get(i).getShiftX());
                thc7.setShiftYForDirection1(HS.get(i).getShiftY());

                thc7.start();
                templist.addAll(thc7.getHS());

                i++;
                if (templist.size() > 0) {
                    t = true;
                    ;
                }
            }
        }
        //direction8
        i = 0;
        synchronized (HS) {
            Iterator<HoldingResult> iterator1 = HS.iterator();
            while (iterator1.hasNext() && t == false) {
                //napravi formulu za racunanje novih min and max za novu lane
                int X2minTemp = X2min + thc11.getHS().get(i).getShiftX();
                int X2maxTemp = X2max + thc11.getHS().get(i).getShiftX();
                int Y2minTemp = Y2min + thc11.getHS().get(i).getShiftY();
                int Y2maxTemp = Y2max + thc11.getHS().get(i).getShiftY();
                int X1minTemp = X1min;
                int X1maxTemp = X1max;
                int Y1minTemp = Y1min;
                int Y1maxTemp = Y1max;
                if (X1minTemp >= X2minTemp)
                    X1minTemp = X2minTemp;
                if (X1maxTemp <= X2maxTemp)
                    X1maxTemp = X2maxTemp;
                if (Y1minTemp >= Y2minTemp)
                    Y1minTemp = Y2minTemp;
                if (Y1maxTemp <= Y2maxTemp)
                    Y1maxTemp = Y2maxTemp;
                //pozovi objdeinjavanje iz discretization
                int[][] matrixdis5 = dis1.UnitingResults(matrixdis1, matrixdis3, HS.get(i).getShiftX(), HS.get(i).getShiftY(), HS.get(i).getDirection(), n + 1, m + 1);
                //sa novom matricom, novim min i max pozovi opet sve threadove, stim sto ces da izadjes prvi prvom rezultatu

                thc8.setDiscreteLane1(matrixdis5);
                thc8.setDiscreteLane2(matrixdis4);
                thc8.setX1min(X1minTemp);
                thc8.setX1max(X1maxTemp);
                thc8.setX2min(X3min);
                thc8.setX2max(X3max);
                thc8.setY1min(Y1minTemp);
                thc8.setY1max(Y1maxTemp);
                thc8.setY2min(Y3min);
                thc8.setY2max(Y3max);
                thc8.setN1((int) lane1.getWidth());
                thc8.setN2((int) lane3.getWidth());
                thc8.setM((int) lane1.getLength());
                thc8.setScanStep(ScanStep);
                thc8.setDirection1(HS.get(i).getDirection());
                thc8.setShiftXForDirection1(HS.get(i).getShiftX());
                thc8.setShiftYForDirection1(HS.get(i).getShiftY());

                thc8.start();
                templist.addAll(thc8.getHS());
                i++;
                if (templist.size() > 0) {
                    t = true;

                }
            }
        }

        System.out.println("resenje prvi pravac" + templist.get(0).getDirection1());
        System.out.println("resenje drugi pravac " + templist.get(0).getDirection2());
        System.out.println("shiftx1 " + templist.get(0).getShiftX1());
        System.out.println("shiftx2 " + templist.get(0).getShiftX2());
        System.out.println("shifty1 " + templist.get(0).getShiftY1());
        System.out.println("shifty2 " + templist.get(0).getShiftY2());
        //Using drawing to show results
        DrawPanels2 panel1 = new DrawPanels2(lane1, lane2, lane3);
        panel1.allPatterns = Shape;
        //panel1.Direction=res.getDirection();
        panel1.Direction1 = templist.get(0).getDirection1();
        panel1.Direction2 = templist.get(0).getDirection2();
        panel1.myFWL1 = lane1;
        panel1.myFWL2 = lane2;
        panel1.myFWL3 = lane3;
        //panel1.ShiftX=res.getShiftX();
        panel1.ShiftX1 = templist.get(0).getShiftX1();
        panel1.ShiftX2 = templist.get(0).getShiftX2();
        //panel1.ShiftY=res.getShiftY();
        panel1.ShiftY1 = templist.get(0).getShiftY1();
        panel1.ShiftY2 = templist.get(0).getShiftY2();
        // window for drawing
        //    // window for drawing
        System.out.println(panel1.myFWL1.getLength());
        JFrame application1 = new JFrame();
        application1.setTitle("Repassing Results");

        application1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // set frame to exit
        // when it is closed
        application1.add(panel1);

        application1.setExtendedState(JFrame.MAXIMIZED_BOTH);
        application1.setVisible(true);

        return templist;
    }
}
