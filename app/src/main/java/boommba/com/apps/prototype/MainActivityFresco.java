package boommba.com.apps.prototype;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;

import jp.wasabeef.fresco.processors.BlurPostprocessor;

public class MainActivityFresco extends AppCompatActivity {

    //variables for image blur using fresco
    private SimpleDraweeView simpleDraweeView;
    //    1.Processor
    private Postprocessor postprocessor;
    //2.Image Request
    private ImageRequest imageRequest;
    //3.Controller
    private PipelineDraweeController controller;

    //static variables
    private int BLUR_PRECENTAGE = 50;
    private String IMAGE_URL = "http://behemppy.com/hotelshivasdream.com/astrokathmandu/api/users/system.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialize fresco
        Fresco.initialize(this);
        setContentView(R.layout.activity_main_fresco);

        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.sdv_image);

        //INSTANTIATE BLUR POST PROCESSOR
        postprocessor = new BlurPostprocessor(this, BLUR_PRECENTAGE);

        //INSTATNTING IMAGE REQUEST USING POST PROCESSOR AS PARAMETER
        imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(IMAGE_URL))
                .setPostprocessor(postprocessor)
                .build();

        //INSTANTATE CONTROLLOR()
        controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setOldController(simpleDraweeView.getController())
                .build();

        //LOAD BLURRED IMAGE ON SimpleDraweeView(VIEW)
        simpleDraweeView.setController(controller);
    }
}
