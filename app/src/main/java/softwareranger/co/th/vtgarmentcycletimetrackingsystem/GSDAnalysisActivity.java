package softwareranger.co.th.vtgarmentcycletimetrackingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GSDAnalysisActivity extends AppCompatActivity {

    private Button buttonLineBalancing, buttonGSDAnalysis, buttonOKNGCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gsdanalysis);

        buttonLineBalancing = (Button) findViewById(R.id.button_line_balancing);
        buttonOKNGCard = (Button) findViewById(R.id.button_ok_ng_card);
        //buttonGSDAnalysis = (Button) findViewById(R.id.button_gsd_analysis);

        buttonLineBalancing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GSDAnalysisActivity.this, LineBalancingActivity.class));
                finish();
            }
        });

        buttonOKNGCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GSDAnalysisActivity.this, OKNGCardActivity.class));
                finish();
            }
        });
    }
}
