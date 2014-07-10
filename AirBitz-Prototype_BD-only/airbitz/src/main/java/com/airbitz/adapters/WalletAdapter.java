package com.airbitz.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.airbitz.R;
import com.airbitz.fragments.BusinessDirectoryFragment;
import com.airbitz.models.Wallet;

import java.util.HashMap;
import java.util.List;

/**
 * Created on 2/12/14.
 */
public class WalletAdapter extends ArrayAdapter<Wallet> {

    final int INVALID_ID = -1;

    private Context mContext;
    private List<Wallet> mWalletList;
    private int selectedViewPos = -1;
    private boolean hoverFirstHeader = false;
    private boolean hoverSecondHeader = false;
    private int nextId = 0;

    private boolean closeAfterArchive = false;
    private int  archivePos;


    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
    HashMap<String, Integer> mArchivedIdMap = new HashMap<String, Integer>();

    public WalletAdapter(Context context, List<Wallet> walletList){
        super(context, R.layout.item_listview_wallets, walletList);
        mContext = context;
        mWalletList = walletList;
        for(Wallet wallet: mWalletList){
            if(wallet.getName() == "SDCMMLlsdkmsdclmLSsmcwencJSSKDWlmckeLSDlnnsAMd"){
                archivePos = mWalletList.indexOf(wallet);
            }
            addWallet(wallet);
        }
    }

    public void setFirstHeaderHover(boolean status){ hoverFirstHeader = status;}

    public void setSecondHeaderHover(boolean status){ hoverSecondHeader = status;}

    public void setSelectedViewPos(int position){
        selectedViewPos = position;
    }

    public void addWallet(Wallet wallet){
        if(mArchivedIdMap.containsKey(wallet.getName())){
            mIdMap.put(wallet.getName(),mArchivedIdMap.get(wallet));
            mArchivedIdMap.remove(wallet.getName());
        }else {
            mIdMap.put(wallet.getName(), nextId);
            nextId++;
        }
        //mWalletList.add(wallet);
    }

    public void swapWallets() {
        archivePos++;
        for (int i = 0; i < mWalletList.size(); ++i) {
            if(mWalletList.get(i).getName() == "SDCMMLlsdkmsdclmLSsmcwencJSSKDWlmckeLSDlnnsAMd"){
                archivePos = i;
            }
            if(!mIdMap.containsKey(mWalletList.get(i).getName())){
                mIdMap.put(mWalletList.get(i).getName(), nextId);
                nextId++;
            }
        }
    }

    public void updateArchive(){
        for (int i = 0; i < mWalletList.size(); ++i) {
            if(mWalletList.get(i).getName() == "SDCMMLlsdkmsdclmLSsmcwencJSSKDWlmckeLSDlnnsAMd"){
                archivePos = i;
            }
        }
    }


    public void removeWallet(Wallet wallet){
        mArchivedIdMap.put(wallet.getName(),mIdMap.get(wallet));
        mIdMap.remove(wallet.getName());
    }

    public void switchCloseAfterArchive(int pos){
        archivePos = pos;
        closeAfterArchive = !closeAfterArchive;
    }

    @Override
    public Wallet getItem(int position) {
        return super.getItem(position);
    }

    public List<Wallet> getList(){
        return mWalletList;
    }

    public int getArchivePos(){ return archivePos; }

    public void incArchivePos(){ archivePos++;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(mWalletList.get(position).getName()=="SDCMMLlsdkmsdclmLSsmcwencJSSKDWlmckeLSDlnnsAMd" || mWalletList.get(position).getName()=="xkmODCMdsokmKOSDnvOSDvnoMSDMSsdcslkmdcwlksmdcL"){//TODO ALERT
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_listview_wallets_header, parent, false);
            if(mWalletList.get(position).getName()=="SDCMMLlsdkmsdclmLSsmcwencJSSKDWlmckeLSDlnnsAMd") {
                ((TextView) convertView).setText("ARCHIVE");
                archivePos = position;
                Drawable img = mContext.getResources().getDrawable(R.drawable.collapse_up);
                img.setBounds(0,0,(int)mContext.getResources().getDimension(R.dimen.three_mm),(int)mContext.getResources().getDimension(R.dimen.three_mm));
                ((TextView) convertView).setCompoundDrawables(null,null,img,null);
                convertView.setPadding((int)(mContext.getResources().getDimension(R.dimen.two_mm)+mContext.getResources().getDimension(R.dimen.three_mm)),0,(int)mContext.getResources().getDimension(R.dimen.two_mm),0);
                if(hoverSecondHeader){
                    convertView.setVisibility(View.INVISIBLE);
                }else {
                    convertView.setVisibility(View.VISIBLE);
                }
            }else{
                ((TextView) convertView).setText("WALLETS");
                if(hoverFirstHeader){
                    convertView.setVisibility(View.INVISIBLE);
                }else {
                    convertView.setVisibility(View.VISIBLE);
                }
            }
        }else {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_listview_wallets, parent, false);
            TextView titleTextView = (TextView) convertView.findViewById(R.id.textview_title);
            TextView amountTextView = (TextView) convertView.findViewById(R.id.textview_amount);
            titleTextView.setTypeface(BusinessDirectoryFragment.montserratRegularTypeFace);
            amountTextView.setTypeface(BusinessDirectoryFragment.montserratRegularTypeFace, Typeface.ITALIC);
            titleTextView.setText(mWalletList.get(position).getName());
            amountTextView.setText(mWalletList.get(position).getBalanceFormatted()
                    + mContext.getResources().getString(R.string.no_break_space_character));
            if(1 == position){
                if(2 == archivePos){
                    convertView.setBackground(mContext.getResources().getDrawable(R.drawable.wallet_list_solo));
                }else{
                    convertView.setBackground(mContext.getResources().getDrawable(R.drawable.wallet_list_top));
                }
            }else if(position == archivePos-1){
                convertView.setBackground(mContext.getResources().getDrawable(R.drawable.wallet_list_bottom));
            }else if(position == archivePos+1){
                if(position == mWalletList.size()-1){
                    convertView.setBackground(mContext.getResources().getDrawable(R.drawable.wallet_list_solo));
                }else{
                    convertView.setBackground(mContext.getResources().getDrawable(R.drawable.wallet_list_top));
                }
            }else if(position == mWalletList.size()-1){
                convertView.setBackground(mContext.getResources().getDrawable(R.drawable.wallet_list_bottom));
            }else{
                convertView.setBackground(mContext.getResources().getDrawable(R.drawable.wallet_list_standard));
            }
            convertView.setPadding((int)mContext.getResources().getDimension(R.dimen.two_mm),0,0,0);
        }
        if(archivePos < position && closeAfterArchive){
            convertView.setVisibility(View.GONE);
        }else if(selectedViewPos == position){
            convertView.setVisibility(View.INVISIBLE);
        }else{
            convertView.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    public int getMapSize(){ return mIdMap.size();}

    @Override
    public long getItemId(int position) {
        if (position < 0 || position >= mIdMap.size() || mWalletList.size()==0) {
            return INVALID_ID;
        }
        Wallet item = mWalletList.get(position);
        return mIdMap.get(item.getName());
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
