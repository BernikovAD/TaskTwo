package com.example.test;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int[] price = new int[]{5, 100, 20, 66, 16};
        decryptData(price, 50, 1, 3);
        decryptData(price, 33, 0, 5);
        decryptData(price, 13, 3, 1);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public @Nullable
    int[] decryptData(@NonNull int[] price,
                      @IntRange(from = 1) int discount,
                      @IntRange(from = 0) int offset,
                      @IntRange(from = 1) int readLength) {

        ArrayList<Integer> newPrice = new ArrayList<>();
        for (int i = offset; i < (offset + readLength); i++) {
            newPrice.add(calculatePercentage(price[i], discount));
        }
        Integer[] arr = (newPrice.toArray(new Integer[newPrice.size()]));
        int result[] = Arrays.stream(arr).mapToInt(i -> (int) i).toArray();
        System.out.println(Arrays.toString(result));
        return result;
    }

    int calculatePercentage(int price, int discount) {
        double n = price * (100 - discount) / 100;
        return (int) n;
    }


}