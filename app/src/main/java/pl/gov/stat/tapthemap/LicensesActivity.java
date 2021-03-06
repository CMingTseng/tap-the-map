package pl.gov.stat.tapthemap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Activity for displaying the licenses.
 */
public class LicensesActivity extends AppCompatActivity {

    @BindView(R.id.licenses)
    TextView mLicensesTextView;

    /**
     * Set up the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licenses);
        ButterKnife.bind(this);

        mLicensesTextView.setText(loadLicenses(this));
    }

    /**
     * Load licenses from resources.
     *
     * @param context Context of the application
     * @return String containing all licenses
     */
    private String loadLicenses(@NonNull Context context) {
        try {
            try (ByteArrayOutputStream result = new ByteArrayOutputStream()) {
                InputStream inputStream = context.getResources().openRawResource(R.raw.license);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) != -1) {
                    result.write(buffer, 0, length);
                }

                return result.toString("UTF-8");
            }
        } catch (IOException e) {
            return context.getResources().getString(R.string.error_loading_licenses);
        }
    }

    /**
     * When pressed system back button.
     */
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, AboutActivity.class));
        finish();
    }
}
