/*
 *  Created by andrSnie on 17.03.20 2:54
 *  Copyright (c) 2020. All rights reserved.
 */

package angel.andrsnie.milk;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


/**
 * This app displays an order form to order milk.
 */
public class MainActivity extends AppCompatActivity {

    private String yourName;
    private boolean hasPickle;
    private boolean hasHerring;
    private int quantity = 2;

    private static final String Q_TITY = "quantityCups";
    private static final String CUCUMBER = "pickle";
    private static final String HERRING = "herring";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            quantity = savedInstanceState.getInt(Q_TITY);
            hasPickle = savedInstanceState.getBoolean(CUCUMBER);
            hasHerring = savedInstanceState.getBoolean(HERRING);
        }

        setContentView(R.layout.activity_main);
        display(quantity);
    }

    private  void getName()
    {
        TextView textName = (TextView) findViewById(R.id.your_name_view);
        yourName = textName.getText().toString();
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        String text = "" + number;
        quantityTextView.setText(text);
    }

    public void increment(View view)
    {
        if (quantity == 10)
        {
            Toast.makeText(getApplicationContext(), getString(R.string.more_than), Toast.LENGTH_LONG).show();
            return;
        }
        quantity++;
        display(quantity);
    }

    public void decrement(View view)
    {
        if (quantity == 1)
        {
            Toast.makeText(this, getString(R.string.less_than), Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        display(quantity);
    }

    public void onCheckboxPickleClicked(View view)
    {
        CheckBox check = (CheckBox) findViewById(R.id.checkBox_pickle);
        hasPickle = check.isChecked();
    }

    public void onCheckboxHerringClicked(View view)
    {
        CheckBox check = (CheckBox) findViewById(R.id.checkBox_herring);
        hasHerring = check.isChecked();
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        PriceOrder priceOrder = new PriceOrder(this);
        int price = priceOrder.calculatePrice(hasPickle, hasHerring, quantity);
        getName();

        String[] addresses = {getString(R.string.george), getString(R.string.paul)};
        String[] addressCC = {getString(R.string.yoko)};
        String[] addressBCC = {getString(R.string.john)};
        String subject = getResources().getString(R.string.order_for, yourName);
        String priceMessage = priceOrder.createOrderSummary(yourName, hasPickle, hasHerring, quantity, price);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse(getString(R.string.ringo))); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_CC, addressCC);
        intent.putExtra(Intent.EXTRA_BCC, addressBCC);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Q_TITY, quantity);
        outState.putBoolean(CUCUMBER, hasPickle);
        outState.putBoolean(HERRING, hasHerring);
    }
}
