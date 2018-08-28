package com.yaratech.yaratube.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.data.source.local.db.database.AppDataBase;
import com.yaratech.yaratube.ui.main.OnBackPressed;
import com.yaratech.yaratube.R;

public class HomeFragment extends Fragment
        implements NavigationView.OnNavigationItemSelectedListener, OnBackPressed {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private Interaction interaction;
    private Toolbar toolbar;
    private AppDataBase appDataBase;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            interaction = (Interaction) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Context Is Not Instance Of Interaction");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appDataBase = AppDataBase.newInstance(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        drawerLayout = view.findViewById(R.id.home_fragment_dl_layout);
        toolbar = view.findViewById(R.id.home_tool_bar);
        bottomNavigationView = view.findViewById(R.id.home_bottom_navigation);
        navigationView = view.findViewById(R.id.home_fragment_nv_navigation);
        navigationView.setNavigationItemSelectedListener(this);
        setValueBottomNavigation();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setToolbar();
    }

    private void setToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setHomeAsUpIndicator(R.drawable.icon_menu_rounded);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.RIGHT);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setValueBottomNavigation() {

        bottomNavigationView.setSelectedItemId(R.id.home_bottom_navigation_main_page);
        interaction.goToMainPage();

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.home_bottom_navigation_main_page:
                                interaction.goToMainPage();
                                break;
                            case R.id.home_bottom_navigation_categories:
                                interaction.goToCategories();
                                break;
                        }
                        return true;
                    }
                });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_hamburger_profile:
                if (appDataBase.userDao().getToken() != null) {
                    interaction.goToProfile();
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                    interaction.goToLogin();
                }
                return true;
            case R.id.home_hamburger_about_us:
                interaction.goToAboutUs();
                drawerLayout.closeDrawer(Gravity.RIGHT);
                return true;
            case R.id.home_hamburger_contact_us:
                interaction.goToContactUs();
                drawerLayout.closeDrawer(Gravity.RIGHT);
                return true;
        }
        return false;
    }

    @Override
    public boolean onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout.closeDrawer(Gravity.RIGHT);
            return false;
        } else {
            return true;
        }
    }

    public interface Interaction {

        void goToLogin();

        void goToProfile();

        void goToAboutUs();

        void goToContactUs();

        void goToMainPage();

        void goToCategories();
    }
}