package actpracticarecepcion.app.com.usingthreads;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    Handler handler = new Handler() {
        public void handleMessage(Message message) {
            TextView textView = (TextView) findViewById(R.id.txt_1);
            textView.setText("Fin de la simulación");
        }
    };

    public void MakeOperation (View view) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                long actualTime = System.currentTimeMillis();
                long finalTime = actualTime + 20000;
                while(System.currentTimeMillis() < finalTime) {
                    synchronized (this) {
                        try {
                            wait(finalTime - System.currentTimeMillis());
                        }catch (Exception e) {

                        }
                    }
                }
                handler.sendEmptyMessage(0);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
