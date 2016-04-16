package bh.edu.ahlia.chap.nationalanthem;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.net.Uri;
import android.view.View;


public class MainActivity extends Activity {

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mp = MediaPlayer.create(this, R.raw.national_anthem);
    }

    public void openWebpage(View v) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(getResources().getString(R.string.website_page)));
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mp.start();
    }

    @Override
    protected void onPause() {
        mp.pause();
        super.onPause();
    }

}
