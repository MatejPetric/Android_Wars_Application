package matej.petric.androidwarsapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RngActivity extends AppCompatActivity {
    int random_i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rng);
        TextView name = findViewById(R.id.rgb_string);
        Button rand = findViewById(R.id.generate_button);

//random
        rand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rand();
                Toast.makeText(RngActivity.this, "....." + random_i, Toast.LENGTH_SHORT).show();
            }
        });

//to MainActivity
        Button mReturn = (Button) findViewById(R.id.return_button);
        mReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String exit = String.valueOf(random_i);
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.putExtra("key", exit);
                startActivity(intent);
            }
        });

// intent from MainActivity
        String unos = getIntent().getStringExtra("keyname");
        name.setText(unos);

    }

    private void rand() {
        int min = 10;
        int max = 99;

        random_i = (int) (Math.random() * (max - min + 1) + min);

        Toast.makeText(RngActivity.this, "....." + random_i, Toast.LENGTH_SHORT).show();
    }
}
