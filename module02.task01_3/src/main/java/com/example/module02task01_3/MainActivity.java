package com.example.module02task01_3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 18. Создать класс для единицы товара на складе
 * (поля: товар, производитель, количество, дата изготовления, срок годности,  поставщик,
 * тел. поставщика, тел. производителя, цена за 1 ед.).
 */
public class MainActivity extends AppCompatActivity {

    private static List<Good> sStorage = new ArrayList<>();
    private static int sPositionInStorage = 0;

    private LinearLayout mFields;
    private EditText mProduct;
    private EditText mProductQuantity;
    private EditText mProductionDate;
    private EditText mShelflife;
    private EditText mPrice;
    private EditText mManufacturerName;
    private EditText mManufacturerContacts;
    private EditText mProviderName;
    private EditText mProviderContacts;
    private TextView mAnswer;
    private Button mShowNextProduct;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFields.getVisibility() == View.VISIBLE) {

                    String productName = mProduct.getText().toString();
                    String manufacturerName = mManufacturerName.getText().toString();
                    String manufacturerContacts = mManufacturerContacts.getText().toString();
                    int quantity = Integer.parseInt(mProductQuantity.getText().toString());
                    String productionDate = mProductionDate.getText().toString();
                    int shelfLife = Integer.parseInt(mShelflife.getText().toString());
                    String providerName = mProviderName.getText().toString();
                    String providerContacts = mProviderContacts.getText().toString();
                    int price = Integer.parseInt(mPrice.getText().toString());


                    sStorage.add(new Good(productName, manufacturerName, manufacturerContacts, quantity,
                            productionDate, shelfLife, providerName, providerContacts, price));

                    clearFields();

                } else {
                    Toast.makeText(MainActivity.this, "Please back to the form", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final Button showStorage = (Button) findViewById(R.id.show_storage);
        showStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFields.getVisibility() == View.VISIBLE) {
                    mFields.setVisibility(View.GONE);
                    mAnswer.setVisibility(View.VISIBLE);
                    mShowNextProduct.setVisibility(View.VISIBLE);
                    mShowNextProduct.callOnClick();
                    showStorage.setText(R.string.show_form);
                    sPositionInStorage = 0;
                } else {
                    mFields.setVisibility(View.VISIBLE);
                    mAnswer.setVisibility(View.GONE);
                    mShowNextProduct.setVisibility(View.GONE);
                    showStorage.setText(R.string.show_storage);
                }
            }
        });

        mShowNextProduct = (Button) findViewById(R.id.show_next_product);
        mShowNextProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sStorage.size() > 0) {
                    mAnswer.setText(sStorage.get(sPositionInStorage).toString());
                    if (sPositionInStorage < sStorage.size()-1) {
                        sPositionInStorage++;
                    } else {
                        sPositionInStorage = 0;
                    }
                }
            }
        });

        mFields = (LinearLayout) findViewById(R.id.fields);
        mProduct = (EditText) findViewById(R.id.product);
        mProductQuantity = (EditText) findViewById(R.id.quantity);
        mProductionDate = (EditText) findViewById(R.id.production_date);
        mShelflife = (EditText) findViewById(R.id.shelfLife);
        mPrice = (EditText) findViewById(R.id.price);
        mManufacturerName = (EditText) findViewById(R.id.manufacturer_name);
        mManufacturerContacts = (EditText) findViewById(R.id.manufacturer_contacts);
        mProviderName = (EditText) findViewById(R.id.provider_name);
        mProviderContacts = (EditText) findViewById(R.id.provider_contacts);
        mAnswer = (TextView) findViewById(R.id.answer);

    }

    private void clearFields() {
        mProduct.setText("");
        mProductQuantity.setText("");
        mProductionDate.setText("");
        mShelflife.setText("");
        mPrice.setText("");
        mManufacturerName.setText("");
        mManufacturerContacts.setText("");
        mProviderName.setText("");
        mProviderContacts.setText("");
        mAnswer.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
