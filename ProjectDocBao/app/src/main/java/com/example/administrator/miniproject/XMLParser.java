package com.example.administrator.miniproject;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class XMLParser extends DefaultHandler {
    private ArrayList<News> arrNews = new ArrayList<>();
    private News news;
    private String value;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if(qName.equals((XMLTaq.ITEM))){
            news = new News();
        }
        value = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        value += new String(ch,start,length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if(news==null){
            return;
        }
        switch (qName){
            case XMLTaq.TITLE:{
                news.setTitle(value);
                break;
            }
            case XMLTaq.DESCRIPTION:{
                if (news.getTitle().equals("This RSS feed URL is deprecated")||value.equals("Google Tin tức")) {
                } else {
                    String src = "src=";
                    int iSrc = value.indexOf(src) + src.length() + 1;
                    value = value.substring(iSrc);
                   String img = value.substring(0,value.indexOf("alt=")-2);
                    String imgLink = "https:"+img;
                    news.setImage(imgLink);
                    news.setDesc(news.getTitle());
                }
                break;
            }
            case XMLTaq.PUB_DATE:{
                news.setPubDate(value);
                break;
            }
            case XMLTaq.LINK:{
                if (news.getTitle().equals("This RSS feed URL is deprecated")) {

                } else {
                    String url = "url=";
                    int iUrl = value.indexOf(url) + url.length();
                    String link = value.substring(iUrl);
                    news.setLink(link);
                }
                break;
            }
            case XMLTaq.ITEM:{
                if (news.getTitle().equals("This RSS feed URL is deprecated")) {

                } else
                    arrNews.add(news);
                break;
            }
        }
    }

    public ArrayList<News> getArrNews() {
        return arrNews;
    }
}
