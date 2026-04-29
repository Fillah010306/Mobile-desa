package com.example.fila_geometry.pertemuan6.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.fila_geometry.databinding.FragmentProfileBinding
import com.example.fila_geometry.pertemuan6.ui.AuthActivity
import java.io.File
import java.io.FileOutputStream

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val internalUri = saveImageToInternalStorage(it)
            if (internalUri != null) {
                binding.ivProfilePicture.setImageURI(internalUri)
                binding.ivProfilePicture.clearColorFilter()
                saveProfilePicture(internalUri.toString())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "User")
        val profileUri = sharedPref.getString("profile_uri", null)

        binding.tvProfileName.text = username
        
        profileUri?.let {
            binding.ivProfilePicture.setImageURI(Uri.parse(it))
            binding.ivProfilePicture.clearColorFilter()
        }

        // Set click listeners for all profile picture elements
        val startPicker = View.OnClickListener {
            pickImageLauncher.launch("image/*")
        }
        binding.btnChangePicture.setOnClickListener(startPicker)
        binding.cardProfilePicture.setOnClickListener(startPicker)
        binding.ivProfilePicture.setOnClickListener(startPicker)

        binding.btnLogoutProfile.setOnClickListener {
            val editor = sharedPref.edit()
            editor.putBoolean("isLogin", false)
            editor.remove("username")
            editor.apply()

            Toast.makeText(requireContext(), "Berhasil Logout", Toast.LENGTH_SHORT).show()

            val intent = Intent(requireContext(), AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        }
        
        // Setup dummy settings items
        binding.itemSettings.apply {
            tvServiceName.text = "Pengaturan Akun"
            root.setOnClickListener { Toast.makeText(context, "Pengaturan", Toast.LENGTH_SHORT).show() }
        }
        binding.itemPrivacy.apply {
            tvServiceName.text = "Privasi & Keamanan"
            root.setOnClickListener { Toast.makeText(context, "Privasi", Toast.LENGTH_SHORT).show() }
        }
        binding.itemHelp.apply {
            tvServiceName.text = "Pusat Bantuan"
            root.setOnClickListener { Toast.makeText(context, "Bantuan", Toast.LENGTH_SHORT).show() }
        }
    }

    private fun saveImageToInternalStorage(uri: Uri): Uri? {
        return try {
            val inputStream = requireContext().contentResolver.openInputStream(uri)
            val file = File(requireContext().filesDir, "profile_picture.jpg")
            val outputStream = FileOutputStream(file)
            inputStream?.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            Uri.fromFile(file)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun saveProfilePicture(uriString: String) {
        val sharedPref = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        sharedPref.edit().putString("profile_uri", uriString).apply()
        Toast.makeText(requireContext(), "Foto profil diperbarui", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}