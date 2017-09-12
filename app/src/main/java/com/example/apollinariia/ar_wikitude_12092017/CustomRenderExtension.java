package com.example.apollinariia.ar_wikitude_12092017;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.wikitude.common.rendering.RenderExtension;
import com.wikitude.tracker.ImageTarget;
import com.wikitude.tracker.Target;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Apollinariia on 9/12/2017.
 */

public class CustomRenderExtension implements GLSurfaceView.Renderer, RenderExtension {
    private Target mCurrentlyRecognizedTarget = null;
    private final float[] mMVPMatrix = new float[16];
    private Triangle mTriangle;
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        mTriangle = new Triangle();
    }

    public void onDrawFrame(GL10 gl10) {
        if (mCurrentlyRecognizedTarget != null) {
            Matrix.multiplyMM(mMVPMatrix, 0, mCurrentlyRecognizedTarget.getProjectionMatrix(), 0,
                    mCurrentlyRecognizedTarget.getViewMatrix(), 0);
            mTriangle.draw(mMVPMatrix);
        }
    }
    public void setCurrentlyRecognizedTarget(final ImageTarget currentlyRecognizedTarget) {
        mCurrentlyRecognizedTarget = currentlyRecognizedTarget;
    }
    public static int loadShader(int type, String shaderCode){
    // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
    // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);
    // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i1) {
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void useSeparatedRenderAndLogicUpdates() {

    }

    @Override
    public void onUpdate() {

    }
}
