package com.josehernandez.meli.utils;


import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;


//https://es.wikipedia.org/wiki/Trilateraci%C3%B3n
public class LocationTrilaterationImpl implements LocationTrilateration {

    @Override
    public Point getLocation(final float distanceKenobi, final float distanceSkywalker, final float distanceSato) throws Exception {
        Point kenobiP = new Point(-500, -200);
        Point skywalkerP = new Point(100, -100);
        Point satoP = new Point(500, 100);
        double[][] positions = new double[][]{{kenobiP.getX(), kenobiP.getY()}, {skywalkerP.getX(), skywalkerP.getY()}, {satoP.getX(), satoP.getY()}};
        double[] distances = new double[]{distanceKenobi, distanceSkywalker, distanceSato};
        return getLocation(distances, positions);
    }

    public Point getLocation(final double[] distances, final double[][] positions) throws Exception {
        //https://github.com/lemmingapex/Trilateration
        if (positions.length != distances.length) {
            throw new IllegalArgumentException("numero de elementos difiere entre posiciones y distancias");
        }
        TrilaterationFunction trilaterationFunction = new TrilaterationFunction(positions, distances);
        NonLinearLeastSquaresSolver nlSolver = new NonLinearLeastSquaresSolver(trilaterationFunction, new LevenbergMarquardtOptimizer());
        LeastSquaresOptimizer.Optimum optimum = nlSolver.solve();
        double[] calculatedPosition = optimum.getPoint().toArray();
        // error and geometry information (https://stackoverflow.com/questions/30336278/multi-point-trilateration-algorithm-in-java)
        try {
            if (calculatedPosition == null || calculatedPosition.length != 2)
                throw new Exception();
            optimum.getSigma(0);
            optimum.getCovariances(0);
        } catch (Exception e) {
            throw new Exception("Imposible calcular la posicion de la nave");
        }
        return new Point((float) calculatedPosition[0], (float) calculatedPosition[1]);
    }

    /*
        algoritmo de TULIP
        https://confluence.slac.stanford.edu/display/IEPM/TULIP+Algorithm+Alternative+Trilateration+Method

            float d1= distanceKenobi;
            float d2= distanceSkyWalker;
            float d3= distanceSato;
            float i1=P1.getX();
            float i2=P2.getX();
            float i3=P3.getX();
            float j1=P1.getY();
            float j2=P2.getY();
            float j3=P3.getY();
            float x =( ( ( ((int) Math.pow(d1,2)-(int) Math.pow(d2,2)) + ((int) Math.pow(i2,2)-(int) Math.pow(i1,2)) + ((int) Math.pow(j2,2)-(int) Math.pow(j1,2)) ) * (2*j3-2*j2) - ( ((int) Math.pow(d2,2)-(int) Math.pow(d3,2)) + ((int) Math.pow(i3,2)-(int) Math.pow(i2,2)) + ((int) Math.pow(j3,2)-(int) Math.pow(j2,2)) ) *(2*j2-2*j1) ) /
            ( (2*i2-2*i3)*(2*j2-2*j1)-(2*i1-2*i2)*(2*j3-2*j2 ) ));
            float y = ( ((int) Math.pow(d1,2)-(int) Math.pow(d2,2)) + ((int) Math.pow(i2,2)-(int) Math.pow(i1,2)) + ((int) Math.pow(j2,2)-(int) Math.pow(j1,2)) + x*(2*i1-2*i2)) / (2*j2-2*j1);
            return new Point((int)x,(int)y);
         */

}
