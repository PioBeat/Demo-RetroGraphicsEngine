package net.offbeatpioneer.demoapp.retrographicsengine;

import android.animation.Animator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.offbeatpioneer.demoapp.R;
import net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.backgrounds.ParallaxBackgroundState;
import net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.backgrounds.SideScrollerState;
import net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.effects.ExplosionSpriteState;
import net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.effects.RandomCirclesState;
import net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.effects.TiledBackgroundState;
import net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.sprites.BasicSpriteExample;
import net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.datastructures.SpriteQuadTreeExample;
import net.offbeatpioneer.demoapp.retrographicsengine.tutorials.TouchInteractionActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;

public class MainActivity extends AppCompatActivity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(getResources().getString(R.string.title_version, net.offbeatpioneer.retroengine.BuildConfig.VERSION_NAME));
        }
        handler = new Handler();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        SampleListAdapter sampleListAdapter = new SampleListAdapter(createDataset());
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(sampleListAdapter);
        SlideInLeftAnimationAdapter slideLeftAdapter = new SlideInLeftAnimationAdapter(alphaAdapter);
        recyclerView.setAdapter(new ScaleInAnimationAdapter(slideLeftAdapter));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://rge.offbeat-pioneer.net"));
            startActivity(browserIntent);
        });
    }

    public List<SampleItem> createDataset() {
        final int duration = 500;
        List<SampleItem> dataset = new ArrayList<>();
        SampleItem.Builder builder = new SampleItem.Builder();

        dataset.add(builder.create("This is the demo app for the <br/> <b>RetroGraphicsEngine</b>",
                R.color.card_colour_holo_green, view -> {
                })
        );

        dataset.add(builder.create("Quad tree (data structure)", R.color.card_colour_first, view -> {
                    createAnimation(view, duration);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Bundle b = new Bundle();
                            b.putSerializable("currentState", SpriteQuadTreeExample.class);
                            Intent myIntent = new Intent(view.getContext(), FullscreenActivity.class);
                            myIntent.putExtras(b);
                            startActivityForResult(myIntent, 0);
                        }
                    }, duration);

                })
        );

        dataset.add(builder.create("Tutorial: Handling Touch Events", R.color.card_colour_second, new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        createAnimation(view, duration);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(view.getContext(), TouchInteractionActivity.class);
                                        startActivityForResult(intent, 0);
                                    }
                                }, duration);
                            }
                        }, duration);
                    }
                })
        );


//        dataset.add(builder.create("Grid Example", R.color.card_colour_fourth, new View.OnClickListener() {
//                    @Override
//                    public void onClick(final View view) {
//                        createAnimation(view, duration);
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                Bundle b = new Bundle();
//                                b.putSerializable("currentState", SpriteGridGroupExample.class);
//                                Intent intent = new Intent(view.getContext(), FullscreenActivity.class);
//                                intent.putExtras(b);
//                                startActivityForResult(intent, 0);
//                            }
//                        }, duration);
//
//                    }
//                })
//        );

//        dataset.add(builder.create("Explosion sprite with text", R.color.card_colour_fifth, new View.OnClickListener() {
//                    @Override
//                    public void onClick(final View view) {
//                        createAnimation(view, duration);
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                Bundle b = new Bundle();
//                                b.putSerializable("currentState", ExplosionSpriteState.class);
//                                Intent myIntent = new Intent(view.getContext(), FullscreenActivity.class);
//                                myIntent.putExtras(b);
//                                startActivityForResult(myIntent, 0);
//                            }
//                        }, duration);
//
//                    }
//                })
//        );

        dataset.add(builder.create("Animated tile background", R.color.card_colour_third, new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        createAnimation(view, duration);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Bundle b = new Bundle();
                                b.putSerializable("currentState", TiledBackgroundState.class);
                                Intent myIntent = new Intent(view.getContext(), FullscreenActivity.class);
                                myIntent.putExtras(b);
                                startActivityForResult(myIntent, 0);
                            }
                        }, duration);

                    }
                })
        );

        dataset.add(builder.create("Random colored circles", R.color.card_colour_second, new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        createAnimation(view, duration);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Bundle b = new Bundle();
                                b.putSerializable("currentState", RandomCirclesState.class);
                                Intent myIntent = new Intent(view.getContext(), FullscreenActivity.class);
                                myIntent.putExtras(b);
                                startActivityForResult(myIntent, 0);
                            }
                        }, duration);
                    }
                })
        );

        dataset.add(builder.create("Sprite showcase", R.color.card_colour_first, new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        createAnimation(view, duration);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Bundle b = new Bundle();
                                b.putSerializable("currentState", BasicSpriteExample.class);
                                Intent myIntent = new Intent(view.getContext(), FullscreenActivity.class);
                                myIntent.putExtras(b);
                                startActivityForResult(myIntent, 0);
                            }
                        }, duration);
                    }
                })
        );

        dataset.add(builder.create("Background (1) - side scroller", R.color.card_colour_third, new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        createAnimation(view, duration);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Bundle b = new Bundle();
                                b.putSerializable("currentState", SideScrollerState.class);
                                Intent myIntent = new Intent(view.getContext(), FullscreenActivity.class);
                                myIntent.putExtras(b);
                                startActivityForResult(myIntent, 0);
                            }
                        }, duration);
                    }
                })
        );

        dataset.add(builder.create("Background (2) - parallax background", R.color.card_colour_fourth, new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        createAnimation(view, duration);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Bundle b = new Bundle();
                                b.putSerializable("currentState", ParallaxBackgroundState.class);
                                Intent myIntent = new Intent(view.getContext(), FullscreenActivity.class);
                                myIntent.putExtras(b);
                                startActivityForResult(myIntent, 0);
                            }
                        }, duration);
                    }
                })
        );

        dataset.add(builder.create("RetroGraphicsEngine with other android components", R.color.card_colour_fifth, new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        createAnimation(view, duration);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(view.getContext(), SplashScreenExample.class);
                                        startActivityForResult(intent, 0);
                                    }
                                }, duration);
                            }
                        }, duration);
                    }
                })
        );

        return dataset;
    }

    public static void createAnimation(final View view, int duration) {
        view.animate().scaleX(1.2f).scaleY(1.2f).setDuration(duration)
                .setInterpolator(new BounceInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        view.setScaleX(1f);
                        view.setScaleY(1f);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {
                        view.setScaleX(1f);
                        view.setScaleY(1f);
                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                })
                .start();
    }

}
