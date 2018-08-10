package com.yaratech.yaratube.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yaratech.yaratube.R;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private DrawerLayout drawerLayout;
    private TextView tvProfile, tvAboutUs, tvContactUs;
    private BottomNavigationView bottomNavigationView;
    private Interaction.goTo iaGoTo;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iaGoTo = (Interaction.goTo) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        drawerLayout = view.findViewById(R.id.fragment_home_dl_layout);
        bottomNavigationView = view.findViewById(R.id.home_bottom_navigation);
        tvProfile = view.findViewById(R.id.hamburger_menu_tv_profile);
        tvAboutUs = view.findViewById(R.id.hamburger_menu_tv_about_us);
        tvContactUs = view.findViewById(R.id.hamburger_menu_tv_contact_us);
        tvProfile.setOnClickListener(this);
        tvAboutUs.setOnClickListener(this);
        tvContactUs.setOnClickListener(this);
        setValueBottomNavigation();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerLayout.isDrawerOpen(Gravity.END))
            drawerLayout.closeDrawer(Gravity.END);
        else
            drawerLayout.openDrawer(Gravity.END);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        drawerLayout.closeDrawer(Gravity.END);
        switch (view.getId()) {
            case R.id.hamburger_menu_tv_profile:
                iaGoTo.goToProfile();
                break;
            case R.id.hamburger_menu_tv_about_us:
                iaGoTo.goToAboutUs();
                break;
            case R.id.hamburger_menu_tv_contact_us:
                iaGoTo.goToContactUs();
                break;
        }
    }

    private void setValueBottomNavigation() {
        bottomNavigationView.setSelectedItemId(R.id.home_bottom_navigation_main_page);
        iaGoTo.goToMainPage();
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.home_bottom_navigation_main_page:
                                iaGoTo.goToMainPage();
                                break;
                            case R.id.home_bottom_navigation_categories:
                                iaGoTo.goToCategories();
                                break;
                        }
                        return true;
                    }
                });
    }

    public interface Interaction {

        interface goTo {

            void goToProfile();

            void goToAboutUs();

            void goToContactUs();

            void goToMainPage();

            void goToCategories();
        }
    }
}