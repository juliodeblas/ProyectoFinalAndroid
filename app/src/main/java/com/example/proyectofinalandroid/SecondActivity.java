package com.example.proyectofinalandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinalandroid.adaptadores.AdaptadorFragments;
import com.example.proyectofinalandroid.fragments.FragmentDos;
import com.example.proyectofinalandroid.fragments.FragmentTres;
import com.example.proyectofinalandroid.fragments.FragmentUno;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    AdaptadorFragments adaptadorFragments;
    ArrayList<Fragment> listaFragments;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        instancias();
        iniciarPager();
        personalizarHeader();
        acciones();
    }

    private void personalizarHeader() {
        Bundle bundle = this.getIntent().getExtras();
        String nombre_recuperado = bundle.getString("nombre");
        String sexo_recuperado = bundle.getString("sexo");
        textView.setText(nombre_recuperado);
        if (sexo_recuperado.equals("hombre")) {
            imageView.setImageResource(R.drawable.hombre);
        } else {
            imageView.setImageResource(R.drawable.mujer);
        }
    }

    private void personalizarPestanias() {
        for (int i = 0; i <= 0; i++) {
            Fragment fragment = adaptadorFragments.getItem(i);
            System.out.println(fragment.getClass().getSimpleName());
            System.out.println(fragment.getView().getScrollX());

            Drawable drawable = fragment.getView().findViewById(R.id.fondo).getBackground();
            TabLayout.Tab seleccionada = tabLayout.getTabAt(i);
            seleccionada.view.setBackground(drawable);
            tabLayout.selectTab(tabLayout.getTabAt(1));

        }
    }

    private void acciones() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.v("scroll", String.valueOf(position));
                Fragment fragment = adaptadorFragments.getItem(position);
                Drawable drawable = fragment.getView().findViewById(R.id.fondo).getBackground();
                tabLayout.setBackground(drawable);
                //TabLayout.Tab seleccionada = tabLayout.getTabAt(position);
                //seleccionada.view.setBackground(drawable);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.getCurrentItem();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.insertar:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.eliminar:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.mostrar:
                        viewPager.setCurrentItem(2);
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void iniciarPager() {
        listaFragments = new ArrayList();
        listaFragments.add(new FragmentUno());
        listaFragments.add(new FragmentDos());
        listaFragments.add(new FragmentTres());
        adaptadorFragments = new AdaptadorFragments(getSupportFragmentManager(), 0, listaFragments);
        viewPager.setAdapter(adaptadorFragments);
    }

    private void instancias() {
        viewPager = findViewById(R.id.view_pager);
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tab_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Proyecto Final Android");
        tabLayout.setupWithViewPager(viewPager);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation);
        textView = navigationView.getHeaderView(0).findViewById(R.id.texto_header);
        imageView = navigationView.getHeaderView(0).findViewById(R.id.image_header);
        drawerToggle = new ActionBarDrawerToggle(SecondActivity.this, drawerLayout, toolbar, R.string.open, R.string.close);
    }
}
