package com.airbitz.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.airbitz.R;
import com.airbitz.activities.LandingActivity;
import com.airbitz.activities.OfflineWalletActivity;
import com.airbitz.activities.RequestActivity;
import com.airbitz.activities.TransactionActivity;
import com.airbitz.adapters.WalletAdapter;
import com.airbitz.models.Transaction;
import com.airbitz.utils.Common;
import com.airbitz.utils.ListViewUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2/12/14.
 */
public class WalletFragment extends Fragment {

    private Button mBitCoinBalanceButton;
    private Button mDollarBalanceButton;

    private ListView mLatestWalletListView;
    private ListView mArchivedWalletListView;

    private ImageButton mHelpButton;
    private ImageButton mAddButton;

    private TextView mTitleTextView;

    private RelativeLayout mParentLayout;
    private ScrollView mScrollLayout;

    private WalletAdapter mLatestWalletAdapter;
    private WalletAdapter mArchiveWalletAdapter;

    private Intent mIntent;
    private Bundle mExtras = null;

    private String mRequestClass = null;

    private boolean mSwitchWordOne = true;
    private boolean mOnBitcoinMode = true;

    private List<Transaction> mLatestTransactionList;
    private List<Transaction> mArchivedTransactionList;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null)
            mRequestClass = savedInstanceState.getString(RequestActivity.CLASSNAME, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_wallet, container, false);

        mLatestTransactionList = new ArrayList<Transaction>();
        mLatestTransactionList.add(new Transaction("Baseball Team", "B15.000"));
        mLatestTransactionList.add(new Transaction("Fantasy Football", "B10.000"));

        mArchivedTransactionList = new ArrayList<Transaction>();
        mArchivedTransactionList.add(new Transaction("Shared", "B0.000"));
        mArchivedTransactionList.add(new Transaction("Mexico", "B0.000"));
        mArchivedTransactionList.add(new Transaction("Other", "B0.000"));

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mLatestWalletAdapter = new WalletAdapter(getActivity(), mLatestTransactionList);
        mArchiveWalletAdapter = new WalletAdapter(getActivity(), mArchivedTransactionList);

        mParentLayout = (RelativeLayout) view.findViewById(R.id.layout_parent);
        mScrollLayout = (ScrollView) view.findViewById(R.id.layout_scroll);

        mBitCoinBalanceButton = (Button) view.findViewById(R.id.button_bitcoinbalance);
        mDollarBalanceButton = (Button) view.findViewById(R.id.button_dollarbalance);

        mHelpButton = (ImageButton) view.findViewById(R.id.button_help);
        mAddButton = (ImageButton) view.findViewById(R.id.button_add);


//        mScrollLayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                return mGestureDetector.onTouchEvent(motionEvent);
//            }
//        });

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogWalletType();
            }
        });

        mHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.showHelpInfo(getActivity(), "Wallet Info", "Wallet info description");
            }
        });

        mBitCoinBalanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBitCoinBalanceButton.setBackgroundResource(R.drawable.btn_green);
                mDollarBalanceButton.setBackgroundResource(android.R.color.transparent);

                mBitCoinBalanceButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ico_coin_btc, 0, R.drawable.ico_btc, 0);
                mDollarBalanceButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ico_coin_usd, 0, R.drawable.ico_usd, 0);

                mBitCoinBalanceButton.setTextColor(getResources().getColor(android.R.color.white));
                mDollarBalanceButton.setTextColor(getResources().getColor(android.R.color.black));

                mBitCoinBalanceButton.setTypeface(LandingActivity.montserratBoldTypeFace);
                mDollarBalanceButton.setTypeface(LandingActivity.helveticaNeueTypeFace);

                mBitCoinBalanceButton.setPadding(15,10,15,10);
                mDollarBalanceButton.setPadding(15,10,15,10);


                mLatestWalletListView.setAdapter(mLatestWalletAdapter);
                mArchivedWalletListView.setAdapter(mArchiveWalletAdapter);

                if(!mOnBitcoinMode){

                    double conv = 8.7544;
                    for(Transaction trans: mLatestTransactionList){
                        try{
                            double item = Double.parseDouble(trans.getmAmount().substring(1))*conv;
                            String amount = String.format("B%.3f", item);
                            trans.setAmount(amount);
                        } catch (Exception e){
                            trans.setAmount("0");
                            e.printStackTrace();
                        }
                    }

                    mLatestWalletAdapter.notifyDataSetChanged();


                    for(Transaction trans: mArchivedTransactionList){
                        try{
                            double item = Double.parseDouble(trans.getmAmount().substring(1))*conv;
                            String amount = String.format("B%.3f", item);
                            trans.setAmount(amount);
                        } catch (Exception e){
                            trans.setAmount("0");
                            e.printStackTrace();
                        }
                    }

                    mArchiveWalletAdapter.notifyDataSetChanged();
                }
                mOnBitcoinMode = true;

            }
        });
        mDollarBalanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mBitCoinBalanceButton.setBackgroundResource(android.R.color.transparent);
                mDollarBalanceButton.setBackgroundResource(R.drawable.btn_green);

                mBitCoinBalanceButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ico_coin_btc_dark, 0, R.drawable.ico_btc, 0);
                mDollarBalanceButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ico_coin_dollar_white, 0, R.drawable.ico_usd_white, 0);

                mBitCoinBalanceButton.setTextColor(getResources().getColor(android.R.color.black));
                mDollarBalanceButton.setTextColor(getResources().getColor(android.R.color.white));

                mBitCoinBalanceButton.setTypeface(LandingActivity.helveticaNeueTypeFace);
                mDollarBalanceButton.setTypeface(LandingActivity.montserratBoldTypeFace);

                mBitCoinBalanceButton.setPadding(15,10,15,10);
                mDollarBalanceButton.setPadding(15,10,15,10);

                mLatestWalletListView.setAdapter(mLatestWalletAdapter);
                mArchivedWalletListView.setAdapter(mArchiveWalletAdapter);

                if(mOnBitcoinMode){

                    double conv = 0.1145;
                    for(Transaction trans: mLatestTransactionList){
                        try{
                            double item = Double.parseDouble(trans.getmAmount().substring(1))*conv;
                            String amount = String.format("$%.3f", item);
                            trans.setAmount(amount);
                        } catch (Exception e){
                            trans.setAmount("0");
                            e.printStackTrace();
                        }
                    }

                    mLatestWalletAdapter.notifyDataSetChanged();

                    for(Transaction trans: mArchivedTransactionList){
                        try{
                            double item = Double.parseDouble(trans.getmAmount().substring(1))*conv;
                            String amount = String.format("$%.3f", item);
                            trans.setAmount(amount);
                        } catch (Exception e){
                            trans.setAmount("0");
                            e.printStackTrace();
                        }
                    }

                    mArchiveWalletAdapter.notifyDataSetChanged();
                }
                mOnBitcoinMode = false;

            }
        });

        mOnBitcoinMode = true;

        mBitCoinBalanceButton.setBackgroundResource(R.drawable.btn_green);
        mDollarBalanceButton.setBackgroundResource(android.R.color.transparent);

        mBitCoinBalanceButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ico_coin_btc, 0, R.drawable.ico_btc, 0);
        mDollarBalanceButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ico_coin_usd, 0, R.drawable.ico_usd, 0);

        mBitCoinBalanceButton.setTextColor(getResources().getColor(android.R.color.white));
        mDollarBalanceButton.setTextColor(getResources().getColor(android.R.color.black));

        mBitCoinBalanceButton.setTypeface(LandingActivity.montserratBoldTypeFace);
        mDollarBalanceButton.setTypeface(LandingActivity.helveticaNeueTypeFace);

        mBitCoinBalanceButton.setPadding(15, 10, 15, 10);
        mDollarBalanceButton.setPadding(15, 10, 15, 10);

        mTitleTextView = (TextView) view.findViewById(R.id.textview_title);

        mArchivedWalletListView = (ListView) view.findViewById(R.id.listview_archive);
        mLatestWalletListView = (ListView) view.findViewById(R.id.listview_latest);

        mLatestWalletListView.setAdapter(mLatestWalletAdapter);
        ListViewUtility.setWalletListViewHeightBasedOnChildren(mLatestWalletListView, mLatestTransactionList.size(), getActivity());

        mArchivedWalletListView.setAdapter(mArchiveWalletAdapter);
        ListViewUtility.setWalletListViewHeightBasedOnChildren(mArchivedWalletListView, mArchivedTransactionList.size(),getActivity());

        mTitleTextView.setTypeface(LandingActivity.montserratBoldTypeFace);
        mBitCoinBalanceButton.setTypeface(LandingActivity.montserratBoldTypeFace);
        mDollarBalanceButton.setTypeface(LandingActivity.helveticaNeueTypeFace);

        mArchivedWalletListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mIntent = new Intent(getActivity(), TransactionActivity.class);
                startActivity(mIntent);
            }
        });

        mLatestWalletListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mIntent = new Intent(getActivity(), TransactionActivity.class);
                startActivity(mIntent);
            }
        });

        return view;
    }

//    @Override
//    public void onBackPressed() {
//        if(mRequestClass == null){
//            finish();
//        }
//        else{
//            super.onBackPressed();
//        }
//
//    }
//

    public void addItemToLatestTransactionList(String name, String amount, List<Transaction> mTransactionList){

        if(mOnBitcoinMode){
            double conv = 8.7544;
            double item = Double.parseDouble(amount.substring(1))*conv;
            amount = String.format("B%.3f", item);
        }
        else{
            double conv = 0.1145;
            double item = Double.parseDouble(amount.substring(1))*conv;
            amount = String.format("$%.3f", item);
        }

        mLatestWalletListView.setAdapter(mLatestWalletAdapter);
        mTransactionList.add(new Transaction(name, amount));
        mLatestWalletAdapter.notifyDataSetChanged();
        ListViewUtility.setWalletListViewHeightBasedOnChildren(mLatestWalletListView, mLatestTransactionList.size(),getActivity());

    }


    public void showDialogWalletType(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder.setTitle("Wallet Type");

        alertDialogBuilder
                .setMessage("Do you want to create online or offline wallet?")
                .setCancelable(false)
                .setPositiveButton("Online",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        if(mSwitchWordOne){
                            addItemToLatestTransactionList("Baseball Team", "B15.000", mLatestTransactionList);
                            mSwitchWordOne = false;
                        }
                        else{
                            addItemToLatestTransactionList("Fantasy Football", "B10.000", mLatestTransactionList);
                            mSwitchWordOne = true;
                        }

                        dialog.cancel();
                    }
                })
                .setNegativeButton("Offline",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {


                        mIntent = new Intent(getActivity(), OfflineWalletActivity.class);
                        startActivity(mIntent);
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}