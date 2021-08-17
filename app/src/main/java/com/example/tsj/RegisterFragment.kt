package com.example.tsj

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tsj.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    val ref = FirebaseAuth.getInstance()
    private  var email:String=""
    private  var password:String=""
    private  var cnfpassword:String=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_register,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.registerBtn.setOnClickListener {
            binding.registerProgressBar.visibility = View.VISIBLE
            closeSoftKeyboard(requireContext(), binding.registerBtn)
            email = binding.registerEmailTxt.text.toString().trim()
            password = binding.registerPassText.text.toString().trim()
            cnfpassword = binding.cnfRegPassText.text.toString().trim()
            Log.e("QQwerty",email+" +  "+password)

            if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(cnfpassword) && !TextUtils.isEmpty(password))
            {
                ref.createUserWithEmailAndPassword(
                    email
                    ,password
                ).addOnSuccessListener {
                    binding.registerProgressBar.visibility = View.GONE
                    view?.let { Snackbar.make(it,"User Registered ", Snackbar.LENGTH_SHORT).show() }
                    val i  = Intent(requireContext(),SplashScreen::class.java)
                    startActivity(i)
                    requireActivity().finish()
                }
                    .addOnFailureListener{e->
                        binding.registerProgressBar.visibility = View.GONE
                        view?.let { Snackbar.make(it,"Registration Failed ${e.message}",Snackbar.LENGTH_SHORT).show() }

                    }
            }
            else{
                binding.registerProgressBar.visibility = View.GONE
                Snackbar.make(view,"Please Enter the Details",Snackbar.LENGTH_SHORT).show()
            }
        }



    }
    private fun closeSoftKeyboard(context: Context, v: View) {
        val iMm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        iMm.hideSoftInputFromWindow(v.windowToken, 0)
        v.clearFocus()
    }
}