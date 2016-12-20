package com.example.module04additional;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.module04additional.entity.Matrix;

public class MatrixActivity extends AppCompatActivity {

    private Matrix mMatrixA;
    private Matrix mMatrixB;

    private RelativeLayout mLayoutSetMSizes;
    private RelativeLayout mLayoutSetValues;
    private RelativeLayout mLayoutResult;
    private GridLayout mGridMatrixA;
    private GridLayout mGridMatrixB;
    private GridLayout mGridMatrixC;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);

        Button buttonSetSize = (Button) findViewById(R.id.matrixsizesbutton_set_m_sizes);
        buttonSetSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

        Button buttonSetValues = (Button) findViewById(R.id.matrixvaluesbutton_multiply);
        buttonSetValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

        Button buttonNewCalculation = (Button) findViewById(R.id.matrixresultbutton_new_calc);
        buttonNewCalculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

        mLayoutSetMSizes = (RelativeLayout) findViewById(R.id.matrixframe_mA_mB_sizes);
        mLayoutSetValues = (RelativeLayout) findViewById(R.id.matrixframe_mA_mB_values);
        mLayoutResult = (RelativeLayout) findViewById(R.id.matrixframe_result);

        mGridMatrixA = (GridLayout) findViewById(R.id.matrixvaluesrecycler_mA_values);
        mGridMatrixB = (GridLayout) findViewById(R.id.matrixvaluesrecycler_mB_values);
        mGridMatrixC = (GridLayout) findViewById(R.id.matrixresultrecycle_mC_values);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, getString(R.string.log_out));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LoginActivity.signOut();
        startActivity(new Intent(MatrixActivity.this, LoginActivity.class));
        return super.onOptionsItemSelected(item);
    }

    private void click(View view) {
        switch (view.getId()) {
            case R.id.matrixsizesbutton_set_m_sizes:
                EditText rowsMAEditText = (EditText) findViewById(R.id.matrixsizesxedit_mA_rows);
                EditText columnsMAEditText = (EditText) findViewById(R.id.matrixsizesxedit_mA_columns);
                EditText rowsMBEditText = (EditText) findViewById(R.id.matrixsizesxedit_mB_rows);
                EditText columnsMBEditText = (EditText) findViewById(R.id.matrixsizesxedit_mB_columns);

                String rowsMAValue = rowsMAEditText.getText().toString();
                String columnsMAValue = columnsMAEditText.getText().toString();
                String rowsMBValue = rowsMBEditText.getText().toString();
                String columnsMBValue = columnsMBEditText.getText().toString();

                if (rowsMAValue.equals("") || columnsMAValue.equals("")
                        || rowsMBValue.equals("") || columnsMBValue.equals("")) {
                    rowsMAEditText.setError(getString(R.string.all_fields_should_be_field));
                    columnsMAEditText.setError(getString(R.string.all_fields_should_be_field));
                    rowsMBEditText.setError(getString(R.string.all_fields_should_be_field));
                    columnsMBEditText.setError(getString(R.string.all_fields_should_be_field));
                } else {
                    rowsMAEditText.setError(null);
                    columnsMAEditText.setError(null);
                    rowsMBEditText.setError(null);
                    columnsMBEditText.setError(null);
                }

                int rowsMACount = Integer.valueOf(rowsMAValue);
                int columnsMACount = Integer.valueOf(columnsMAValue);
                int rowsMBCount = Integer.valueOf(rowsMBValue);
                int columnsMBCount = Integer.valueOf(columnsMBValue);

                if (columnsMACount != rowsMBCount) {
                    rowsMBEditText.setError(getString(R.string.matrix_sizes_error));
                    columnsMAEditText.setError(getString(R.string.matrix_sizes_error));
                } else {
                    rowsMAEditText.setError(null);
                    columnsMBEditText.setError(null);
                }

                mMatrixA = new Matrix(rowsMACount, columnsMACount);
                mMatrixB = new Matrix(rowsMBCount, columnsMBCount);

                mGridMatrixA.setRowCount(rowsMACount);
                mGridMatrixA.setColumnCount(columnsMACount);
                mGridMatrixB.setRowCount(rowsMBCount);
                mGridMatrixB.setColumnCount(columnsMBCount);

                createViewGroup(mMatrixA, mGridMatrixA, R.layout.item_matrix_edit);
                createViewGroup(mMatrixB, mGridMatrixB, R.layout.item_matrix_edit);

                mLayoutSetMSizes.setVisibility(View.GONE);
                mLayoutSetValues.setVisibility(View.VISIBLE);
                break;

            case R.id.matrixvaluesbutton_multiply:
                boolean isMAField = fillMatrix(mMatrixA, mGridMatrixA);
                boolean isMBField = fillMatrix(mMatrixB, mGridMatrixB);

                if (!isMAField || !isMBField) {
                    break;
                }
                Matrix matrixC = matrixMultiply(mMatrixA, mMatrixB);

                mGridMatrixC.setRowCount(matrixC.rowsCount());
                mGridMatrixC.setColumnCount(matrixC.columnsCount());

                createViewGroup(matrixC, mGridMatrixC, R.layout.item_matrix_text);
                fillViewGroup(matrixC, mGridMatrixC);

                mLayoutSetValues.setVisibility(View.GONE);
                mLayoutResult.setVisibility(View.VISIBLE);
                break;

            case R.id.matrixresultbutton_new_calc:
                for (int i = 0; i < mGridMatrixC.getChildCount(); ++i) {
                    TextView nextChild = (TextView) mGridMatrixC.getChildAt(i);
                    nextChild.setText("");
                }
                mLayoutResult.setVisibility(View.GONE);
                mLayoutSetMSizes.setVisibility(View.VISIBLE);
                break;

            default:
                break;
        }
    }

    private boolean fillMatrix(Matrix matrix, GridLayout gridLayout) {
        boolean result = true;
        int viewCounter = 0;
        for (int i = 0; i < matrix.rowsCount() && viewCounter < gridLayout.getChildCount();
             i++) {
            for (int j = 0; j < matrix.columnsCount() && viewCounter < gridLayout.getChildCount();
                 j++, viewCounter++) {
                EditText child = (EditText) gridLayout.getChildAt(viewCounter);
                String input = child.getText().toString();
                if (input.equals("")) {
                    child.setError(getString(R.string.all_fields_should_be_field));
                    result = false;
                } else {
                    child.setError(null);
                    double element = Double.parseDouble(input);
                    matrix.setElement(i, j, element);
                }
            }
        }
        return result;
    }

    private void fillViewGroup(Matrix matrix, ViewGroup viewGroup) {
        int viewCounter = 0;
        for (int i = 0; i < matrix.rowsCount() && viewCounter < viewGroup.getChildCount();
             i++) {
            for (int j = 0; j < matrix.columnsCount() && viewCounter < viewGroup.getChildCount();
                 j++, viewCounter++) {
                ((TextView) viewGroup.getChildAt(viewCounter)).setText(String.valueOf(matrix.getElement(i, j)));
            }
        }
    }

    private void createViewGroup(Matrix matrix, ViewGroup viewGroup, int itemLayoutId) {
        if (viewGroup.getChildCount() > 0) {
            viewGroup.removeAllViews();
        }
        for (int i = 0; i < matrix.elementsNumber(); i++) {
            viewGroup.addView(getLayoutInflater().inflate(itemLayoutId, null, false));
        }
    }


    public static Matrix matrixMultiply(Matrix matrixA, Matrix matrixB) {
        int mARows = matrixA.rowsCount();
        int mBColumns = matrixB.columnsCount();
        int mBRows = matrixB.rowsCount();
        Matrix result = new Matrix(mARows, mBColumns);

        for (int i = 0; i < mARows; i++) {
            double[] mARow = matrixA.getRow(i);
            for (int j = 0; j < mBColumns; j++) {
                double[] mBColumn = matrixB.getColumn(j);
                for (int k = 0; k < mBRows; k++) {
                    double element = result.getElement(i, j) + (mARow[k] * mBColumn[k]);
                    result.setElement(i, j, element);
                }
            }
        }
        return result;
    }

}
