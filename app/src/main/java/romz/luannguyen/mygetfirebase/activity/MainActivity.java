package romz.luannguyen.mygetfirebase.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import romz.luannguyen.mygetfirebase.R;
import romz.luannguyen.mygetfirebase.adapter.CustomAdapter;
import romz.luannguyen.mygetfirebase.model.Dulieu;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Firebase root;
    private ArrayList<Dulieu> mDulieu = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Firebase.setAndroidContext(this);

        mRecyclerView= (RecyclerView)findViewById(R.id.my_recycler_view);


        root = new Firebase("https://kitestquocte.firebaseio.com/2016/6");
        if (isNetworkAvailable(this)) {
            // code here
            Toast.makeText(MainActivity.this, "Connect", Toast.LENGTH_SHORT).show();
        } else {
            // code
            Toast.makeText(MainActivity.this, "NotConnect", Toast.LENGTH_SHORT).show();

        }
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("Tag", "onChildAdded: "+dataSnapshot.getKey().toString());
                /*DataObject post = dataSnapshot.getChildren(DataObject.class);
                mang.add(post);
                customAdapter = new CustomAdapter(getApplicationContext(),mang);
                lvDs.setAdapter(customAdapter);*/

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.d("child", "onDataChange: "+child.getValue().toString());
                    /*  DataObject post = dataSnapshot.getValue(DataObject.class);
                       mang.add(post);*/
                    Dulieu post=new Dulieu();
                    post.setContent(child.getValue(Dulieu.class).getContent());
                    post.setTitle(child.getValue(Dulieu.class).getTitle());
                    post.setUrl(child.getValue(Dulieu.class).getUrl());
                    mDulieu.add(post);
                }
                customAdapter = new CustomAdapter(getApplicationContext(),mDulieu);
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),1);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        addControl();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void addControl() {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

}
