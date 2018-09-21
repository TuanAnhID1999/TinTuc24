package com.example.administrator.miniproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XMLAsync extends AsyncTask<String, Void, ArrayList<News>> {
    private XMLCallBack callBack;
    private ProgressDialog progressDialog;

    public XMLAsync(XMLCallBack callBack, Context context) {
        this.callBack = callBack;
        progressDialog = new ProgressDialog(context);
    }
    @Override
    protected ArrayList doInBackground(String... strings) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLParser xmlParser = new XMLParser();
            String link = strings[0];
            parser.parse(link, xmlParser);
            return xmlParser.getArrNews();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        progressDialog.setCancelable(false);
        progressDialog.setMessage("loading");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(ArrayList<News> arrayList) {
        super.onPostExecute(arrayList);
        callBack.onParserResult(arrayList);
        progressDialog.dismiss();
    }
    public interface XMLCallBack {
        void onParserResult(ArrayList<News> arrayList);
    }
}
