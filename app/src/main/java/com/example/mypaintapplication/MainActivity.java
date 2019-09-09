package com.example.mypaintapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RectangleColourView rcvColour;
    private CanvasView cvCanvas;
    private CircleColourView ccvColour;
    private Button btnClearCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cvCanvas=(CanvasView)findViewById(R.id.cvCanvas);
        rcvColour=(RectangleColourView)findViewById(R.id.rcvColour);
        ccvColour=(CircleColourView)findViewById(R.id.ccvColour);

        btnClearCanvas=(Button)findViewById(R.id.btnClearCanvas);
        btnClearCanvas.setOnClickListener(this);

        ccvColour.setOnColourClickListener(new CircleColourView.OnColourClickListener() {
            @Override
            public void changeColour(int colour) {
                cvCanvas.setBackgroundColour(colour);
            }
        });

        rcvColour.setOnColourClickListener(new RectangleColourView.OnColourClickListener() {
            @Override
            public void changeColour(int colour) {
                cvCanvas.setCanvasTextColour(colour);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnClearCanvas:
                cvCanvas.clearCanvas();
                break;
        }

    }
}
