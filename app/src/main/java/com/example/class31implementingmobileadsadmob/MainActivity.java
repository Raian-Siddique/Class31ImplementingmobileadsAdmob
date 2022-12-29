package com.example.class31implementingmobileadsadmob;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class MainActivity extends AppCompatActivity {


    AdView mAdView;
    Button button1,button2;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = findViewById(R.id.adView);
        button1= findViewById(R.id.button1);
        button2=findViewById(R.id.button2);





        //================== AdMob initialization ====================

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
          });

       //====================== Setting adds ======================

        loadFullScreenAd();  // This method is created by us -------

        //====== Loading Banner ad============
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //==================== Loading Interstatial ad start =================







        //==================== Loading Interstatial ad end=================




        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //=========== show full screen add Start============
                if (mInterstitialAd != null) {
                  mInterstitialAd.show(MainActivity.this);
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                }
                //=========== show full screen add Start============
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //=========== show full screen add Start============
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                }
                //=========== show full screen add Start============
            }
        });

    }

    //============ Creating our own load add private Method===============

     private void loadFullScreenAd(){
        // ------- Creating an interstatial ad------

         AdRequest adRequest = new AdRequest.Builder().build();

         InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest,
                 new InterstitialAdLoadCallback() {
                     @Override
                     public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                         // The mInterstitialAd reference will be null until
                         // an ad is loaded.
                         mInterstitialAd = interstitialAd;
//                         Log.i(TAG, "onAdLoaded");

                            // ----call back function Start------------
                         mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                             @Override
                             public void onAdClicked() {
                                 // Called when a click is recorded for an ad.
                                 Log.d(TAG, "Ad was clicked.");
                             }

                             @Override
                             public void onAdDismissedFullScreenContent() {
                                 // Called when ad is dismissed.
                                 // Set the ad reference to null so you don't show the ad a second time.
                                 Log.d(TAG, "Ad dismissed fullscreen content.");
//                                 mInterstitialAd = null;
                                 loadFullScreenAd();
                             }

                             @Override
                             public void onAdFailedToShowFullScreenContent(AdError adError) {
                                 // Called when ad fails to show.
                                 Log.e(TAG, "Ad failed to show fullscreen content.");
                                 mInterstitialAd = null;
                             }

                             @Override
                             public void onAdImpression() {
                                 // Called when an impression is recorded for an ad.
                                 Log.d(TAG, "Ad recorded an impression.");
                             }

                             @Override
                             public void onAdShowedFullScreenContent() {
                                 // Called when ad is shown.
                                 Log.d(TAG, "Ad showed fullscreen content.");
                             }
                         });
                         //      ---- Callback Function finished----------

                     }

                     @Override
                     public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                         // Handle the error
//                         Log.d(TAG, loadAdError.toString());
                         mInterstitialAd = null;
                     }
                 });
     }
         // --------- Our own private Method finished -------------



}