package org.techtown.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import org.techtown.myapplication.repository.MainRepository;
import org.techtown.myapplication.ui.CallActivity;

import com.permissionx.guolindev.PermissionX;

public class ScheduleFragment extends Fragment {

    private MainRepository mainRepository;
    private Button callButton;

    private String userName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);

        init(rootView);
        return rootView;
    }

    private void init(View rootView) {
        mainRepository = MainRepository.getInstance();
        callButton = rootView.findViewById(R.id.callButton);
        userName = getArguments().getString("userName");


        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionX.init(requireActivity())
                        .permissions(android.Manifest.permission.CAMERA, android.Manifest.permission.RECORD_AUDIO)
                        .request((allGranted, grantedList, deniedList) -> {
                            if (allGranted) {
                                // Firebase에 로그인하는 코드
                                mainRepository.login(
                                        userName,
                                        requireContext(),
                                        () -> {
                                            // 성공하면 CallActivity로 이동
                                            Intent intent = new Intent(requireActivity(), CallActivity.class);
                                            startActivity(intent);
                                        }
                                );
                            }
                        });
            }
        });
    }
}