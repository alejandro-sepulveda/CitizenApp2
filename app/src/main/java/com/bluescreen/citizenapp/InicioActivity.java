package com.bluescreen.citizenapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bluescreen.citizenapp.ui.gallery.GalleryFragment;
import com.bluescreen.citizenapp.ui.home.HomeFragment;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class InicioActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TextView nombreUsuario, emailUsuario;
    private FirebaseAuth firebaseAuth;
    private ImageView imagenUsuarioMenu;
    FirebaseStorage firebaseStorage;

    View mView;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
      mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,  R.id.nav_cerrarSesion)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //-----------------------------------------------------------------------------------------
        firebaseStorage = FirebaseStorage.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        mView = navigationView.getHeaderView(0);
        nombreUsuario = (TextView) mView.findViewById(R.id.nombreUsuarioMenu);
        emailUsuario = (TextView) mView.findViewById(R.id.emailUsuarioMenu);
        imagenUsuarioMenu = (ImageView)mView.findViewById(R.id.imagenUsuarioMenu_img);

        Glide.with(this).load(firebaseUser.getPhotoUrl()).into(imagenUsuarioMenu);
       // assert firebaseUser != null;
      nombreUsuario.setText(firebaseUser.getDisplayName());
       emailUsuario.setText(firebaseUser.getEmail());

    //   cerrarSesion_btn = (Button) mView.findViewById(R.id.nav_cerrarSesion);


     //   getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inicio, menu);

        return true;
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
