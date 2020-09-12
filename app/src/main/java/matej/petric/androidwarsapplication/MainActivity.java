package matej.petric.androidwarsapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public Editable str;
    Button language_dialog, login_button, rng_button;
    boolean lang_selected;
    Context context;
    EditText mEditText;
    Resources resources;
    int count = 0;
    int count_a = 0;
    int count_A = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.string_text);

//language change
        language_dialog = (Button) findViewById(R.id.button2);
        login_button = (Button) findViewById(R.id.button1);
        rng_button = (Button) findViewById(R.id.rgb_button);

        language_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] Language = {"English", "Croatian"};
                final int checkedItem;
                if (lang_selected) {
                    checkedItem = 0;
                } else {
                    checkedItem = 1;
                }
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select a Language...")
                        .setSingleChoiceItems(Language, checkedItem, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                language_dialog.setText(Language[which]);
                                lang_selected = Language[which].equals("English");
                                //if user select prefered language as English then
                                if (Language[which].equals("English")) {
                                    context = LocaleHelper.setLocale(MainActivity.this, "en");
                                    resources = context.getResources();
                                    language_dialog.setText(resources.getString(R.string.language));
                                    login_button.setText(resources.getString(R.string.login));
                                    rng_button.setText(resources.getString(R.string.rng_button));
                                    mEditText.setHint(resources.getString(R.string.enter_string));
                                    Toast.makeText(context, "Language updated", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                                //if user select prefered language as Croatian then
                                if (Language[which].equals("Croatian")) {
                                    context = LocaleHelper.setLocale(MainActivity.this, "hr");
                                    resources = context.getResources();
                                    language_dialog.setText(resources.getString(R.string.language));
                                    login_button.setText(resources.getString(R.string.login));
                                    rng_button.setText(resources.getString(R.string.rng_button));
                                    mEditText.setHint(resources.getString(R.string.enter_string));
                                    Toast.makeText(context, "Language updated", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            }

                        });
                builder.create().show();

            }

        });

// intent form ScreenActivity
        String exit = getIntent().getStringExtra("key");
        mEditText.setText(exit);

// intent to RngActivity
        str = mEditText.getText();

        Button mButton3 = findViewById(R.id.rgb_button);
        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String someInput = str.toString();

                Intent intent = new Intent(v.getContext(), RngActivity.class);
                intent.putExtra("keyname", someInput);
                startActivity(intent);
            }
        });

// validation and intent for ScreenActivity
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('7', 'a', 'A');
                String numberOfSeven = String.valueOf(count);
                String someInput = str.toString();
                int countofAa = count_A + count_a;
                Calculate_b(someInput);

                if ((someInput.length() > 4) && (someInput.length() < 14) && (!someInput.contains("?"))
                        && (numberOfSeven.equals("1"))) {
                    if ((countofAa >= 2 && Calculate_b(someInput))) {

                        Intent intent = new Intent(v.getContext(), ScreenActivity.class);
                        startActivity(intent);
                    }

                } else {

                    Toast.makeText(MainActivity.this, "bad validation", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //method for validation
    private void calculate(char character, char character_a, char character_A) {

        String someString = str.toString();

        for (int i = 0; i < someString.length(); i++) {
            if (someString.charAt(i) == character) {
                count++;
            }
        }
        for (int i = 0; i < someString.length(); i++) {
            if (someString.charAt(i) == character_a) {
                count_a++;
            }
        }
        for (int i = 0; i < someString.length(); i++) {
            if (someString.charAt(i) == character_A) {
                count_A++;
            }
        }
    }

    //Method for validation
    public static boolean Calculate_b(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'b' && i != 0) {
                switch (str.charAt(i - 1)) {
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        return false;
                }
            }
        }
        return true;
    }
}