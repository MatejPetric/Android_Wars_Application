package matej.petric.androidwarsapplication;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import static matej.petric.androidwarsapplication.ScreenActivity.EXTRA_FILMS;
import static matej.petric.androidwarsapplication.ScreenActivity.EXTRA_GENDER;
import static matej.petric.androidwarsapplication.ScreenActivity.EXTRA_HEIGHT;
import static matej.petric.androidwarsapplication.ScreenActivity.EXTRA_MASS;
import static matej.petric.androidwarsapplication.ScreenActivity.EXTRA_NAME;


public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String name = intent.getStringExtra(EXTRA_NAME);
        int mass = intent.getIntExtra(EXTRA_MASS, 0);
        int height = intent.getIntExtra(EXTRA_HEIGHT, 0);
        String gender = intent.getStringExtra(EXTRA_GENDER);
        String films = intent.getStringExtra(EXTRA_FILMS);

        TextView textViewCreator = findViewById(R.id.text_view_creator_detail);
        TextView textViewMass = findViewById(R.id.text_view_mass_detail);
        TextView textViewHeight = findViewById(R.id.text_view_height_detail);
        TextView textViewGender = findViewById(R.id.text_view_gender_detail);
        TextView textViewFilms = findViewById(R.id.text_view_films_detail);

        textViewCreator.setText(name);
        textViewMass.setText("" + mass + " kg");
        textViewHeight.setText("" + height + " cm");
        textViewGender.setText("gender: " + gender);
        textViewFilms.setText(films);

    }
}