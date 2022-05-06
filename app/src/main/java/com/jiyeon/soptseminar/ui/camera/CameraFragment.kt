package com.jiyeon.soptseminar.ui.camera

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.jiyeon.soptseminar.R
import com.jiyeon.soptseminar.databinding.FragmentCameraBinding
import com.jiyeon.soptseminar.util.BaseFragment

class CameraFragment : BaseFragment<FragmentCameraBinding>(R.layout.fragment_camera) {

    private lateinit var getResult: ActivityResultLauncher<Intent>

    // 사용자의 응답을 처리하는 권한 콜백을 등록
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // 권한 획득 성공 시
            openGallery()
        } else {
            // 권한 획득 거부 시
            showPermissionContextPopup()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermission()
        getPhotoFromGallery()
    }

    // 권한 요청 함수
    private fun requestPermission(){
        binding.btnInputPhoto.setOnClickListener {
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    // 갤러리 사진 결과 반환 로직
    private fun getPhotoFromGallery() {
        getResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    try {
                        val uri: Uri? = (it.data)?.data
                        Glide.with(this).load(uri).into(binding.ivPhoto)
                    } catch (e: Exception) {

                    }
                } else if (it.resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(requireContext(), "선택 취소", Toast.LENGTH_SHORT).show()
                }
            }
    }

    // 갤러리 열기
    private fun openGallery(){
        val profileIntent = Intent()
        profileIntent.setType("image/*")
        profileIntent.setAction(Intent.ACTION_GET_CONTENT)
        getResult.launch(profileIntent)
    }

    // 권한 요청하기
    private fun showPermissionContextPopup() {
        AlertDialog.Builder(requireContext())
            .setTitle("권한이 필요합니다.")
            .setMessage("프로필 이미지를 바꾸기 위해서는 갤러리 접근 권한이 필요합니다.")
            .setPositiveButton("동의하기") { _, _ ->
                registerForActivityResult(ActivityResultContracts.RequestPermission()){

                }
            }
            .setNegativeButton("취소하기") { _, _ -> }
            .create()
            .show()
    }
}