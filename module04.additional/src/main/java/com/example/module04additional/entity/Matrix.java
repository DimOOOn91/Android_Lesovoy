package com.example.module04additional.entity;

import java.util.Arrays;

public class Matrix {

    private double[][] mMatrix;

    public Matrix(int rows, int columns) {
        this.mMatrix = new double[rows][columns];
    }

    public double[][] getMatrix() {
        return mMatrix;
    }

    public double getElement(int row, int column) {
        return mMatrix[row][column];
    }

    public void setElement(int row, int column, double element) {
        mMatrix[row][column] = element;
    }

    public double[] getRow(int row) {
        return mMatrix[row];
    }

    public double[] getColumn(int column) {
        double[] result = new double[columnsCount()];
        for (int i = 0; i < columnsCount(); i++) {
            result[i] = getElement(i, column);
        }
        return result;
    }

    public int rowsCount() {
        return mMatrix.length;
    }

    public int columnsCount() {
        return mMatrix[0].length;
    }

    public int elementsNumber() {
        return rowsCount() * columnsCount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrix matrix = (Matrix) o;

        return Arrays.deepEquals(mMatrix, matrix.mMatrix);

    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(mMatrix);
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "mMatrix=" + Arrays.toString(mMatrix) +
                '}';
    }
}
