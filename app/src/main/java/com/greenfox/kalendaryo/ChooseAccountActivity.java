package com.greenfox.kalendaryo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.greenfox.kalendaryo.adapter.AccountAdapter;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.http.RetrofitClient;
import com.greenfox.kalendaryo.models.KalMerged;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.MergedKalendarResponse;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChooseAccountActivity extends AppCompatActivity {

    RecyclerView accountNamesView;
    KalPref kalpref;
    Button sendToBackend;
    BackendApi backendApi;
    KalMerged kalMerged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String infoToPass = "kalMerged";

        kalMerged = (KalMerged) getIntent().getSerializableExtra(infoToPass);

        setContentView(R.layout.activity_choose_account);
        kalpref = new KalPref(this.getApplicationContext());
        sendToBackend = findViewById(R.id.sendtobackend);
        backendApi = RetrofitClient.getBackendApi();

        String clientToken = kalpref.clientToken();

        String[] array = new String[kalMerged.getInputCalendarIds().size()];
        for (int j = 0; j < kalMerged.getInputCalendarIds().size(); j++) {
            array[j] = kalMerged.getInputCalendarIds().get(j).getId();
        }

        sendToBackend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backendApi.postCalendar(clientToken, kalMerged).enqueue(new Callback<MergedKalendarResponse>() {
                    @Override
                    public void onResponse(Call<MergedKalendarResponse> call, Response<MergedKalendarResponse> response) {
                        MergedKalendarResponse mergedKalendarResponse = response.body();

                    }

                    @Override
                    public void onFailure(Call<MergedKalendarResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                Intent i = new Intent(ChooseAccountActivity.this, MainActivity.class);
                startActivity(i);

            }
        });

        accountNamesView = findViewById(R.id.accountNames);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        accountNamesView.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(accountNamesView.getContext(),
                        recyclerLayoutManager.getOrientation());
        accountNamesView.addItemDecoration(dividerItemDecoration);
        AccountAdapter accountAdapter = new
                AccountAdapter(kalpref.getKalAuths(),this);

        accountAdapter.setEmailChange(new AccountAdapter.EmailChange() {
            @Override
            public void emailChanged(String email) {
                kalMerged.setOutputCalendarId(email);
            }
        });
        accountNamesView.setAdapter(accountAdapter);
    }
}
