package com.example.fila_geometry.pertemuan6.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fila_geometry.data.api.CatFactApiClient
import com.example.fila_geometry.data.api.PhotoApiClient
import com.example.fila_geometry.data.api.NewsApiClient
import com.example.fila_geometry.pertemuan6.fragments.photo.PhotoAdapter
import kotlinx.coroutines.launch
import com.example.fila_geometry.databinding.FragmentHomeBinding
import com.example.fila_geometry.pertemuan6.ui.AuthActivity
import com.example.fila_geometry.pertemuan6.ui.InputDataActivity
import com.example.fila_geometry.pertemuan6.ui.WebViewActivity
import com.example.fila_geometry.pertemuan9.ui.NinthActivity
import com.example.fila_geometry.Message.tutorial.TutorialActivity

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        loadLastSubmission()
    }

    private fun loadLastSubmission() {
        val sharedPref = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val lastNik = sharedPref.getString("last_nik", null)
        
        if (lastNik != null) {
            binding.cardLastSubmission.visibility = View.VISIBLE
            binding.tvLastNik.text = "NIK: $lastNik"
            binding.tvLastNama.text = "Nama: ${sharedPref.getString("last_nama", "")}"
            binding.tvLastService.text = sharedPref.getString("last_service", "Layanan")
            binding.tvLastStatus.text = sharedPref.getString("last_status", "Diproses")
        } else {
            binding.cardLastSubmission.visibility = View.GONE
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "User")
        binding.tvUsernameHome.text = if (username.isNullOrBlank()) "Guest" else username

        // 1. Klik Layanan untuk Input Data
        binding.cardKtp.setOnClickListener { openInputActivity("KTP Digital") }
        binding.cardKk.setOnClickListener { openInputActivity("Kartu Keluarga") }
        binding.cardAkta.setOnClickListener { openInputActivity("Akta Lahir") }
        binding.cardPindah.setOnClickListener { openInputActivity("Pindah Domisili") }

        // 2. Aksi Hapus Pengajuan Terakhir
        binding.btnDeleteSubmission.setOnClickListener {
            val editor = sharedPref.edit()
            editor.remove("last_nik")
            editor.remove("last_nama")
            editor.remove("last_service")
            editor.remove("last_status")
            editor.apply()
            
            binding.cardLastSubmission.visibility = View.GONE
            Toast.makeText(requireContext(), "Data Pengajuan Dihapus", Toast.LENGTH_SHORT).show()
        }

        // 3. Aksi Logout
        binding.btnLogout.setOnClickListener {
            val editor = sharedPref.edit()
            editor.clear()
            editor.apply()

            Toast.makeText(requireContext(), "Berhasil Logout", Toast.LENGTH_SHORT).show()

            val intent = Intent(requireContext(), AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        // 4. Aksi Buka WebView (Website)
        val openWebView = View.OnClickListener {
            val intent = Intent(requireContext(), WebViewActivity::class.java)
            intent.putExtra("url", "http://fila-2sic.alwaysdata.net/")
            startActivity(intent)
        }
        binding.cardInfoPublik.setOnClickListener(openWebView)
        binding.cardWebPortal.setOnClickListener(openWebView)

        // 5. Aksi Pertemuan 9
        binding.cardPertemuan9.setOnClickListener {
            val intent = Intent(requireContext(), NinthActivity::class.java)
            startActivity(intent)
        }

        // 6. Aksi Pertemuan 10
        binding.btnPertemuan10.setOnClickListener {
            val intent = Intent(requireContext(), com.example.fila_geometry.pertemuan10.TenthActivity::class.java)
            startActivity(intent)
        }

        // 7. Aksi Tutorial
        binding.btnTutorial.setOnClickListener {
            val intent = Intent(requireContext(), TutorialActivity::class.java)
            startActivity(intent)
        }

        // 8. Load REST API Data
        loadCatFact()
        loadPhoto()
        loadNews()

        binding.btnRefresh.setOnClickListener {
            loadCatFact()
            loadNews()
        }
    }

    private fun loadCatFact() {
        lifecycleScope.launch {
            try {
                val response = CatFactApiClient.apiService.getCatFact()
                binding.tvCatFact.text = "\"${response.fact}\""
            } catch (e: Exception) {
                binding.tvCatFact.text = "Gagal mengambil fakta kucing."
            }
        }
    }

    private fun loadPhoto() {
        lifecycleScope.launch {
            try {
                val photos = PhotoApiClient.apiService.getPhotos()
                val adapter = PhotoAdapter(photos)
                binding.rvGallery.adapter = adapter
                binding.rvGallery.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Gagal memuat gambar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadNews() {
        lifecycleScope.launch {
            try {
                val response = NewsApiClient.apiService.getNews()
                val adapter = NewsAdapter(response.data.posts)
                binding.rvNews.adapter = adapter
                binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Gagal memuat berita", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openInputActivity(serviceName: String) {
        val intent = Intent(requireContext(), InputDataActivity::class.java)
        intent.putExtra("SERVICE_TYPE", serviceName)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
