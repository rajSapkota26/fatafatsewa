package com.fatafatsewa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fatafatsewa.activity.FAQSActivity;
import com.fatafatsewa.activity.OrdersActivity;
import com.fatafatsewa.activity.QRActivity;
import com.fatafatsewa.activity.SupportsActivity;
import com.fatafatsewa.databinding.ActivityFatafatBinding;
import com.fatafatsewa.fragments.CartFragment;
import com.fatafatsewa.fragments.CategoryFragment;
import com.fatafatsewa.fragments.DealsFragment;
import com.fatafatsewa.fragments.HomeFragment;
import com.fatafatsewa.fragments.ProfileFragment;
import com.fatafatsewa.loginorregister.LoginActivity;
import com.fatafatsewa.loginorregister.RegisterActivity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import me.ibrahimsn.lib.OnItemSelectedListener;

public class FataFatActivity extends AppCompatActivity {
    private ActivityFatafatBinding binding;
    private static final float END_SCALE = 0.7f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFatafatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        requestPermissions();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        //navigation view setting
        naviagtionDrawer();
        //bottom navigation view
        bottomNavigationView();
        binding.menuQrscanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FataFatActivity.this, QRActivity.class));
            }
        });

    }


    //navigation view drawer methods
    private void naviagtionDrawer() {
        binding.mainNavigationView.bringToFront();
        binding.mainNavigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.nav_orders:
                    startActivity(new Intent(FataFatActivity.this, OrdersActivity.class));

                    break;
                case R.id.nav_insurence:
                    Toast.makeText(FataFatActivity.this, "clickedd...", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_cashpoint:
                    Toast.makeText(FataFatActivity.this, "clickedd...", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_statement:
                    Toast.makeText(FataFatActivity.this, "clickedd...", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_followed_shop:
                    Toast.makeText(FataFatActivity.this, "clickedd...", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_wishpot:
                    Toast.makeText(FataFatActivity.this, "clickedd...", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.nav_share_share:
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT, "Share this app");
                    intent.setType("text/plain");
                    startActivity(Intent.createChooser(intent, "Send To"));
                    break;
                case R.id.nav_share_rateus:
                    Toast.makeText(FataFatActivity.this, "clickedd...", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_support:
                    startActivity(new Intent(FataFatActivity.this, SupportsActivity.class));
                    break;
                case R.id.nav_faqs:
                    startActivity(new Intent(FataFatActivity.this, FAQSActivity.class));
                    break;
            }
            return false;
        });
        binding.mainNavigationView.setCheckedItem(R.id.nav_home);
        binding.menuIcon.setOnClickListener(view -> {
            if (binding.mainDrawerLayout.isDrawerVisible(GravityCompat.START))
                binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
            else binding.mainDrawerLayout.openDrawer(GravityCompat.START);
        });
        binding.mainNavigationView.getHeaderView(0).findViewById(R.id.nav_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FataFatActivity.this, LoginActivity.class));

            }
        });
        binding.mainNavigationView.getHeaderView(0).findViewById(R.id.nav_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FataFatActivity.this, RegisterActivity.class));

            }
        });
        animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {
        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();Color.TRANSPARENT
        binding.mainDrawerLayout.setScrimColor(getResources().getColor(R.color.slider));
        binding.mainDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                binding.contentView.setScaleX(offsetScale);
                binding.contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = binding.contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                binding.contentView.setTranslationX(xTranslation);
            }
        });
    }

    private void requestPermissions() {
        // below line is use to request  permission in the current activity.
        Dexter.withContext(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {/* ... */}

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
        }).check();
    }

    private void bottomNavigationView() {

        binding.bottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                switch (i) {
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CategoryFragment()).commit();

                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DealsFragment()).commit();

                        break;
                    case 3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CartFragment()).commit();

                        break;
                    case 4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();

                        break;
                }

                return false;
            }
        });
    }


}