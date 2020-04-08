package com.example.android3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    String url = "https://my-json-server.typicode.com/MoldovanG/JsonServer/users?fbclid=IwAR0xox1Vxnr1UYBZcZtR4_T_Xy-_zE2Y4Gb1QCh-bVRi_TCTbwGKxlkwrPk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerFragment recyclerFragment = new RecyclerFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frameLayout, recyclerFragment);

        fragmentTransaction.commit();
    }

}
