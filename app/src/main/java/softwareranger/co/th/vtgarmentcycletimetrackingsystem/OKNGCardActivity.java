package softwareranger.co.th.vtgarmentcycletimetrackingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OKNGCardActivity extends AppCompatActivity {

    private Button buttonLineBalancing, buttonGSDAnalysis, buttonOKNGCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okngcard);

        buttonLineBalancing = (Button) findViewById(R.id.button_line_balancing);
        buttonGSDAnalysis = (Button) findViewById(R.id.button_gsd_analysis);

        buttonLineBalancing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OKNGCardActivity.this, LineBalancingActivity.class));
                finish();
            }
        });

        buttonGSDAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OKNGCardActivity.this, GSDAnalysisActivity.class));
                finish();
            }
        });
    }
}
