package org.techtown.myapplication.ui;

import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.techtown.myapplication.R;
import org.techtown.myapplication.repository.MainRepository;
import org.techtown.myapplication.utils.DataModelType;
import org.webrtc.SurfaceViewRenderer;

public class CallActivity extends AppCompatActivity implements MainRepository.Listener {

    private MainRepository mainRepository;
    private Boolean isCameraMuted = false;
    private Boolean isMicrophoneMuted = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        init();
    }
    private void init() {
        mainRepository = MainRepository.getInstance();

        View rootView = findViewById(android.R.id.content);

        rootView.findViewById(R.id.callBtn).setOnClickListener(v -> {
            // Call 버튼을 눌렀을 때 호출할 코드
            mainRepository.sendCallRequest(
                    ((EditText) rootView.findViewById(R.id.targetUserNameEt)).getText().toString(),
                    () -> Toast.makeText(this, "Could not find the target", Toast.LENGTH_SHORT).show()
            );
        });

        mainRepository.initRemoteView((SurfaceViewRenderer) rootView.findViewById(R.id.remote_view));
        mainRepository.initLocalView((SurfaceViewRenderer) rootView.findViewById(R.id.local_view));
        mainRepository.listener = this;

        mainRepository.subscribeForLatestEvent(data -> {
            if (data.getType() == DataModelType.StartCall) {
                runOnUiThread(() -> {
                    ((TextView) rootView.findViewById(R.id.incomingNameTV))
                            .setText(data.getSender() + " is Calling you");
                    rootView.findViewById(R.id.incomingCallLayout).setVisibility(View.VISIBLE);

                    rootView.findViewById(R.id.acceptButton).setOnClickListener(v -> {
                        // Call을 시작하는 코드
                        mainRepository.startCall(data.getSender());
                        rootView.findViewById(R.id.incomingCallLayout).setVisibility(View.GONE);
                    });

                    rootView.findViewById(R.id.rejectButton).setOnClickListener(v ->
                            rootView.findViewById(R.id.incomingCallLayout).setVisibility(View.GONE)
                    );
                });
            }
        });

        rootView.findViewById(R.id.switch_camera_button).setOnClickListener(v -> mainRepository.switchCamera());

        rootView.findViewById(R.id.mic_button).setOnClickListener(v -> {
            if (isMicrophoneMuted) {
                // 마이크 끄기
                rootView.findViewById(R.id.mic_button).setBackgroundResource(R.drawable.ic_baseline_mic_off_24);
            } else {
                // 마이크 켜기
                rootView.findViewById(R.id.mic_button).setBackgroundResource(R.drawable.ic_baseline_mic_24);
            }
            mainRepository.toggleAudio(isMicrophoneMuted);
            isMicrophoneMuted = !isMicrophoneMuted;
        });

        rootView.findViewById(R.id.video_button).setOnClickListener(v -> {
            if (isCameraMuted) {
                // 비디오 끄기
                rootView.findViewById(R.id.video_button).setBackgroundResource(R.drawable.ic_baseline_videocam_off_24);
            } else {
                // 비디오 켜기
                rootView.findViewById(R.id.video_button).setBackgroundResource(R.drawable.ic_baseline_videocam_24);
            }
            mainRepository.toggleVideo(isCameraMuted);
            isCameraMuted = !isCameraMuted;
        });

        rootView.findViewById(R.id.end_call_button).setOnClickListener(v -> {
            mainRepository.endCall();
            finish();
        });
    }

    @Override
    public void webrtcConnected() {
        runOnUiThread(() -> {
            findViewById(R.id.incomingCallLayout).setVisibility(View.GONE);
            findViewById(R.id.whoToCallLayout).setVisibility(View.GONE);
            findViewById(R.id.callLayout).setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void webrtcClosed() {
        runOnUiThread(this::finish);
    }
}