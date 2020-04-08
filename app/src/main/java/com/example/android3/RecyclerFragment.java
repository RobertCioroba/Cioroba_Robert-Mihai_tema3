package com.example.android3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecyclerFragment extends Fragment {
    private RecyclerView recyclerView;
    String url = "https://my-json-server.typicode.com/MoldovanG/JsonServer/users?fbclid=IwAR2BMuJtt0MGTIPzsbZsqPHjlO8Uyd39aZDnrLgIhkXqQMjUcW5QyHGFlMI";
    private UserAdaptor adaptor = new UserAdaptor();
    private ArrayList<User> users = new ArrayList<User>();

    public RecyclerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getData();

        return view;
    }

    private void getData() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        User user = new User();
                        user.setId(jsonObject.getInt("id"));
                        user.setName(jsonObject.getString("name"));
                        user.setUsername(jsonObject.getString("username"));
                        user.setEmail(jsonObject.getString("email"));
                        users.add(user);
                    }
                } catch (JSONException e) {
                    Toast.makeText(getContext(), "Error 1", Toast.LENGTH_SHORT).show();
                }

                adaptor.setData(users);
                recyclerView.setAdapter(adaptor);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.wtf(error.getMessage(), "utf-8");

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }
}
