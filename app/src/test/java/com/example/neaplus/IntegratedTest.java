package com.example.neaplus;

import com.example.neaplus.ui.DetailActivity;
import com.example.neaplus.ui.MainActivity;
import com.example.neaplus.ui.SplashActivity;
import com.example.neaplus.ui.WebActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.LooperMode;
import org.robolectric.annotation.internal.DoNotInstrument;

import static android.os.Looper.getMainLooper;
import static org.junit.Assert.assertNotNull;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@LooperMode(LooperMode.Mode.PAUSED)
@Config(manifest=Config.NONE)
@DoNotInstrument
public class IntegratedTest {
    private DetailActivity detailActivity;
    private MainActivity mainActivity;
    private SplashActivity splashActivity;
    private WebActivity webActivity;

    @Before
    public void setUp() throws Exception{
        shadowOf(getMainLooper()).idle();
        // detailActivity = Robolectric.buildActivity(DetailActivity.class).create().get();
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
        splashActivity = Robolectric.buildActivity(SplashActivity.class).create().get();
        webActivity = Robolectric.buildActivity(WebActivity.class).create().get();

    }
    @Test
    public void testActivityShouldNotBeNull() throws Exception{
        // assertNotNull(detailActivity);
        assertNotNull(mainActivity);
        assertNotNull(splashActivity);
        assertNotNull(webActivity);
    }
}
