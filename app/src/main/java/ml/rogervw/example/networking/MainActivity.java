package ml.rogervw.example.networking;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity
       extends AppCompatActivity
       implements LoaderManager.LoaderCallbacks<List<News>> {
     private NewsAdapter adapter;
     private static int LOADER_ID = 0;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_view);
          adapter = new NewsAdapter(this);
          RecyclerView.Adapter Adapter = null;
          recyclerView.setAdapter(Adapter);
          recyclerView.setOnClickListener(new AdapterView.OnClickListener() {
               @Override
               public void onClick(View v) {
                    News news = adapter.getItem;
                    String url = news.url;
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    MainActivity.this.startActivity(intent);
               }
          });
          getSupportLoaderManager().initLoader(LOADER_ID, null, this);
     }

     @Override
     public Loader<List<News>> onCreateLoader(int id, Bundle args) {
          return new Loader<List<News>>(this);
     }

     @Override
     public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
          if (data != null) {
               adapter.setNotifyOnChange(false);
               adapter.clear();
               adapter.setNotifyOnChange(true);
               adapter.addAll(data);
          }
     }

     @Override
     public void onLoaderReset(Loader<List<News>> loader) {

     }
}