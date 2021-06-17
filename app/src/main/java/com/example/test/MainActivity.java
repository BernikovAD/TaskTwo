package com.example.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_COUNT = "count";
    SharedPreferences mSettings;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        int count=0;
        count = mSettings.getInt(APP_PREFERENCES_COUNT,0);
        if (savedInstanceState == null) {
            count++;
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putInt(APP_PREFERENCES_COUNT, count);
            editor.apply();
        }
        if (count % 3 == 0) {
            Toast.makeText(this,
                    "Это третий холодный запуск приложения!", Toast.LENGTH_LONG).show();

        }
        int[] price = new int[]{5, 100, 20, 66, 16};
        decryptData(price, 50, 1, 3);
        decryptData(price, 33, 0, 5);
        decryptData(price, 13, 3, 1);
    }


    public @Nullable
    int[] decryptData(@NonNull int[] price,
                      @IntRange(from = 1) int discount,
                      @IntRange(from = 0) int offset,
                      @IntRange(from = 1) int readLength) {
        int[] newPrice = new int[readLength];
        for (int i = offset, j = 0; i < (offset + readLength); i++, j++) {
            newPrice[j] = calculatePercentage(price[i], discount);
        }
        System.out.println(Arrays.toString(newPrice));
        return newPrice;
    }

    private int calculatePercentage(int price, int discount) {
        double n = price * (100 - discount) / 100;
        return (int) n;
    }


}