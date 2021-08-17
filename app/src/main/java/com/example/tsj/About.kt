package com.example.tsj

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tsj.databinding.FragmentAboutBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class About : Fragment() {

    private lateinit var binding : FragmentAboutBinding
    private val ref =FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_about,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ppofileLogoutBtn.setOnClickListener {
            ref.signOut()
            val i = Intent(requireContext(),Login::class.java)
            startActivity(i)
            requireActivity().finish()
        }
        binding.meBtn.setOnClickListener {
            Snackbar.make(requireView(),"Hi, So this is my App \n  I can add many more features like \n one tap login with google \n alert boxes \n Email & Password Validations \n Hope It will meet your Standards \n Excited to work with this company.",Snackbar.LENGTH_LONG).show()
        }
        binding.profileShareBtn.setOnClickListener{
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, " Zomato Task ")
            val app_url = "The Student Junction \n Android Development(Kotlin) Enthusiast Shivendu Mishra \n I can do KOTLIN All Day :)"
            shareIntent.putExtra(Intent.EXTRA_TEXT, app_url)
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }

        binding.aboutMail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plane"
            intent.putExtra(Intent.EXTRA_SUBJECT, "Congratulations !")
            intent.putExtra(Intent.EXTRA_TEXT, "Hello Shivendu \n shivenduaps986@gmail.com")

            startActivity(Intent.createChooser(intent, "Send Email"))
        }
        binding.aboutGithub.setOnClickListener {
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://github.com/shivenducs1136")
            startActivity(openURL)
        }
        binding.aboutLinkedin.setOnClickListener {
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.linkedin.com/feed/update/urn:li:activity:6829024046747828224/")
            startActivity(openURL)
        }
    }



}