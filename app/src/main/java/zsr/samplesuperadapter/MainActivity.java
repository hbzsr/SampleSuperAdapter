package zsr.samplesuperadapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void simplelist(View View){
        startActivity(new Intent(this,SimpleTypeListViewActivity.class));
    }
    public void mutillist(View View){
        startActivity(new Intent(this,MutilTypeListViewActivity.class));
    }
    public void simplerecycler(View View){
        startActivity(new Intent(this,SimpleTypeRecyclerViewActivity.class));
    }
    public void mutilrecycler(View View){
        startActivity(new Intent(this,MutilTypeRecyclerViewActivity.class));
    }
}
