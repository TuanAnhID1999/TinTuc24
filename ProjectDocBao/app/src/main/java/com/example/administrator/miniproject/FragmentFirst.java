package com.example.administrator.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.administrator.miniproject.R.layout.activity_item;

public class FragmentFirst extends Fragment implements XMLAsync.XMLCallBack, SearchView.OnQueryTextListener, ItemViewActionCallBack  {
    private ArrayList<News> arrNews = new ArrayList<>() ;

    private RecyclerView lv_news;
    private NewsAdapter newsAdapter;

    private SearchView searchView;
    private View view;
    private int currentSelected = -1;
    private NewsSave newsSave;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_first,container,false);
        lv_news =  view.findViewById(R.id.lv_news);
        newsAdapter = new NewsAdapter(arrNews,getContext());
        lv_news.setLayoutManager(new LinearLayoutManager(getActivity()));
        lv_news.setAdapter(newsAdapter);
        newsAdapter.setCallBack(this);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  add
        //
        // Events();
    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);

        MenuItem menuItem = menu.findItem(R.id.menuSerch);
        searchView = (SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onParserResult(ArrayList<News> arrayList) {
        this.arrNews.addAll(arrayList);
        newsAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        arrNews.clear();
        XMLAsync async = new XMLAsync(this,getActivity());
        String link = "https://news.google.de/news/feeds?pz=1&cf=vi_vn&ned=vi_vn&hl=vi_vn&q=" + s;
        async.execute(link); // xử lý trên thread
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(getActivity(),"click",Toast.LENGTH_SHORT).show();
        if (position == currentSelected) {
            return;
        }
        if (currentSelected != -1) {
            arrNews.get(currentSelected).setSelected(false);
            newsAdapter.notifyItemChanged(currentSelected);
        }
        arrNews.get(position).setSelected(true);
        newsAdapter.notifyItemChanged(position);
        currentSelected = position;

        Intent intent = new Intent(getActivity(), WebActivity.class);
        intent.putExtra("link", arrNews.get(position).getLink());
        startActivity(intent);

    }

    @Override
    public void onLongClick(int positon) {
        Toast.makeText(getActivity(), "Long click ", Toast.LENGTH_LONG).show();
        if (positon == currentSelected){
            return;
        }
        if (currentSelected != -1){
            arrNews.get(currentSelected).setSelected(false);
            newsAdapter.notifyItemChanged(currentSelected);
        }
        arrNews.get(positon).setSelected(true);
        newsAdapter.notifyItemChanged(positon);
        currentSelected = positon;


        //newsSave.update();
        addData(positon);


    }

    private void addData(int position) {
        News news = arrNews.get(position);
        String title = news.getTitle();
        String desc = news.getDesc();
        String link = news.getLink();
        String image = news.getImage();
        String pubdate = news.getPubDate();

       // arrNews.add(title, desc, link, image, pubdate)


    }


//    public class FragmentFist extends AppCompatActivity{
//        private WebView webView;
//        @Override
//        protected void onCreate(@Nullable Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_first);
//
//        }
//    }
}
