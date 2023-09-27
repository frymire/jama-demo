package org.example;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;

public class JamaDemo {

  public static void main(String[] args) {

    double[][] matrixData = {
            {1., 2., 3},
            {4., 5., 6.},
            {7., 8., 10.}
    };

    Matrix A = new Matrix(matrixData);
    System.out.println(matrix2string(A));

    Matrix invA = A.inverse();
    System.out.println(matrix2string(invA));

    System.out.println("Check inverse\n" + matrix2string(A.times(invA)));

    Matrix Sigma = A.times(A.transpose());
    System.out.println("Rank of Sigma = " + Sigma.rank());

    EigenvalueDecomposition USUt = new EigenvalueDecomposition(Sigma);
    double[] diagS = USUt.getRealEigenvalues();
    Matrix diagD = USUt.getD();
    System.out.println("Diag(D) =\n" + matrix2string(diagD));
    Matrix U = USUt.getV();
    System.out.println("U =\n" + matrix2string((U)));

    Matrix b = Matrix.random(3, 1);
    System.out.println(matrix2string(b));

    Matrix x = A.solve(b);
    System.out.println(matrix2string(x));

    Matrix Residual = A.times(x).minus(b);
    System.out.println(Residual.normInf());
  }

  public static String matrix2string(Matrix A) {

    int rows = A.getRowDimension();
    int cols = A.getColumnDimension();

    StringBuilder sb = new StringBuilder();

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        sb.append(A.get(row, col));
        sb.append("\t");
      }
      sb.append("\n");
    }

    return sb.toString();
  }
}
