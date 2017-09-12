package com.example.apollinariia.ar_wikitude_12092017;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wikitude.NativeStartupConfiguration;
import com.wikitude.WikitudeSDK;
import com.wikitude.common.camera.CameraSettings;
import com.wikitude.common.rendering.InternalRendering;
import com.wikitude.common.rendering.RenderExtension;
import com.wikitude.common.rendering.RenderSettings;
import com.wikitude.tracker.ImageTarget;
import com.wikitude.tracker.ImageTracker;
import com.wikitude.tracker.ImageTrackerListener;
import com.wikitude.tracker.TargetCollectionResource;
import com.wikitude.tracker.TargetCollectionResourceLoadingCallback;

public class MainActivity extends Activity implements ImageTrackerListener, InternalRendering {


    private final String WIKITUDE_SDK_KEY ="ZMzDJtHlzabzvPky3lRFaTvALN4vLQpm3KpMHie0nOGr3HKB6ebvsIUhGS9oit1H/oJpvuWb2jgqLKhjf/GlhlhuFRN1ZGovqe5NyqI50F/1ZHslewZCMhqmdaPwpcsERHxPUOp+OCpZnvzwAoi4XZ2sWku24LPDEexOrrlguL9TYWx0ZWRfXw9xh+SHOPrV+aOIGJQV9D/EZTPdSvlguJbNsdhhFHaNa4cdemffmQjRIcf0vjIec508pjQP298VmaJ4+hTNAzRICVLGkftCzdyE88MOtyvm6VzWDj1bFgXsjj8/l94mNIGXwMeLoOHoqedgqvcgdka38s97D35n3HesWgS7vtrfQkuJFw4TuybMCdeSAUlL88QvkL+V4ljOLno502gAAcvhayQr5wxy87u/HiVFp04K/uV3hqC9TqsdtsKsgPE0d9xf2JJYovrlpzaEGL3RY6rZqP3/xTOvcizMY/yoJ14cTqsOEKGjMx3ChuPuXpSzMv8pbfM5JuYXuXVHSog9tJr0//MN+jqtRxjmVuANRWxbgXJAW6NUlO4ecyC/pwqQEybJECU1Q7N38ETVc2a3gteFHrMX1jV5kxsg93uGl5095wDZAF0x87KQ/4+9Mq9LuLQ/dy997Pg6+hI5+AAdNoxygoFLEXavwR6ws2lWHXicjZlU1nAWE2LEaw1DSm6UfVyAetiQcubK";
    private WikitudeSDK mWikitudeSDK;
    private TargetCollectionResource mTargetCollectionResource;
    private CustomRenderExtension mRenderExtension;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWikitudeSDK = new WikitudeSDK(this);
        NativeStartupConfiguration startupConfiguration = new NativeStartupConfiguration();
        startupConfiguration.setLicenseKey(WIKITUDE_SDK_KEY);
        startupConfiguration.setCameraPosition(CameraSettings.CameraPosition.BACK);
        startupConfiguration.setCameraResolution(CameraSettings.CameraResolution.AUTO);
        mWikitudeSDK.onCreate(getApplicationContext(), this, startupConfiguration);

        mTargetCollectionResource =
                mWikitudeSDK.getTrackerManager().createTargetCollectionResource("file:///android_asset/tracker.wtc", new TargetCollectionResourceLoadingCallback() {
        @Override
        public void onError(int errorCode, String errorMessage) {
        }
        @Override
        public void onFinish() {
            mWikitudeSDK.getTrackerManager().createImageTracker(mTargetCollectionResource,
                    MainActivity.this, null);
        }
        });
    setContentView(mWikitudeSDK.setupWikitudeGLSurfaceView());
    }

    public RenderExtension provideRenderExtension() {
        mRenderExtension = new CustomRenderExtension();
        return mRenderExtension;
    }

    @Override
    public void onRenderingApiInstanceCreated(RenderSettings.RenderingAPI renderingAPI) {

    }

    @Override
    public void onTargetsLoaded(ImageTracker imageTracker) {

    }

    @Override
    public void onErrorLoadingTargets(ImageTracker imageTracker, int i, String s) {

    }

    @Override
    public void onImageRecognized(ImageTracker imageTracker, ImageTarget imageTarget) {

    }

    public void onImageTracked(ImageTracker imageTracker, ImageTarget imageTarget) {
        mRenderExtension.setCurrentlyRecognizedTarget(imageTarget);
    }
    public void onImageLost(ImageTracker imageTracker, ImageTarget imageTarget) {
        mRenderExtension.setCurrentlyRecognizedTarget(null);
    }

    @Override
    public void onExtendedTrackingQualityChanged(ImageTracker imageTracker, ImageTarget imageTarget, int i, int i1) {

    }

    protected void onResume() {
        super.onResume();
        mWikitudeSDK.onResume();
    }
    protected void onPause() {
        super.onPause();
        mWikitudeSDK.onPause();
    }
    protected void onDestroy() {
        super.onDestroy();
        mWikitudeSDK.clearCache();
        mWikitudeSDK.onDestroy();
    }

}
