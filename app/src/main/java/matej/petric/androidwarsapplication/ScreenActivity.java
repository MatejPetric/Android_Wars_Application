package matej.petric.androidwarsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class ScreenActivity extends AppCompatActivity implements WarsAdapter.OnItemClickListener {
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_MASS = "mass";
    public static final String EXTRA_HEIGHT = "height";
    public static final String EXTRA_GENDER = "gender";
    public static final String EXTRA_FILMS = "films";

    private RecyclerView mRecyclerView;
    private WarsAdapter mWarsAdapter;
    private ArrayList<WarsItem> mWarsList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mWarsList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJson();

    }

    private void parseJson() {
        String url = "https://swapi.dev/api/people/";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject results = jsonArray.getJSONObject(i);

                                String nameCount = results.getString("name");
                                int massCount = results.getInt("mass");
                                int heightCount = results.getInt("height");
                                String gender = results.getString("gender");
                                String films = results.getString("films");
                                mWarsList.add(new WarsItem(nameCount, massCount, heightCount, gender, films));

                            }

                            mWarsAdapter = new WarsAdapter(ScreenActivity.this, mWarsList);
                            mRecyclerView.setAdapter(mWarsAdapter);
                            mWarsAdapter.setOnItemClickListener(ScreenActivity.this);

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        mRequestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        WarsItem clickedItem = mWarsList.get(position);

        detailIntent.putExtra(EXTRA_NAME, clickedItem.getName());
        detailIntent.putExtra(EXTRA_MASS, clickedItem.getMass());
        detailIntent.putExtra(EXTRA_HEIGHT, clickedItem.getHeight());
        detailIntent.putExtra(EXTRA_GENDER, clickedItem.getGender());
        detailIntent.putExtra(EXTRA_FILMS, clickedItem.getFilms());

        startActivity(detailIntent);

    }
}