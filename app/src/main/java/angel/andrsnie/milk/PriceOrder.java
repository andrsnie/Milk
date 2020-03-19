/*
 *  Created by andrSnie on 17.03.20 2:54
 *  Copyright (c) 2020. All rights reserved.
 */

package angel.andrsnie.milk;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

public class PriceOrder extends AppCompatActivity {

    private Context mContext;
    private int oneCupPrice = 5;

    PriceOrder(Context context)
    {
        mContext = context;
    }

    public int calculatePrice(boolean addPickle, boolean addSaltedHerring, int order)
    {
        if(addPickle && addSaltedHerring)
        {
            oneCupPrice += 3;
        }
        else if(addSaltedHerring)
        {
            oneCupPrice += 2;
        }
        else if(addPickle)
        {
            oneCupPrice += 1;
        }
        else
        {
            return oneCupPrice * order;
        }

        return oneCupPrice * order;
    }

    public String createOrderSummary(String name, boolean pickle, boolean herring, int quantityOfCups, int allPrice)
    {
        String pckl;
        String hrrng;

        if (pickle)
        {
            pckl = mContext.getString(R.string.yes);
        }
        else
        {
            pckl = mContext.getString(R.string.no);
        }

        if (herring)
        {
            hrrng = mContext.getString(R.string.yes);
        }
        else
        {
            hrrng = mContext.getString(R.string.no);
        }

        if (name.equals(""))
        {
            return mContext.getString(R.string.wrong_name) + "\n" + mContext.getString(R.string.add_pickle) + " " + pckl + "\n" + mContext.getString(R.string.add_herring) + " " + hrrng + "\n" + mContext.getString(R.string.quantity) + " " + mContext.getString(R.string.cups) + " " + quantityOfCups + "\n" + mContext.getString(R.string.total) + " " + allPrice + " " + mContext.getString(R.string.sign) + "\n" + mContext.getString(R.string.thank_you);
        }
        else
        {
            return mContext.getString(R.string.order_summary_name, name) + "\n" + mContext.getString(R.string.add_pickle) + " " + pckl + "\n" + mContext.getString(R.string.add_herring) + " " + hrrng + "\n" + mContext.getString(R.string.quantity) + " " + mContext.getString(R.string.cups) + " " + quantityOfCups + "\n" + mContext.getString(R.string.total) + " " + allPrice + " " + mContext.getString(R.string.sign) + "\n" + mContext.getString(R.string.thank_you);
        }
    }
}