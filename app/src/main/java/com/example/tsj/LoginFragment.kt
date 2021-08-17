package com.example.tsj

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isEmpty
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.tsj.databinding.FragmentLoginBinding
import com.example.tsj.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    val ref = FirebaseAuth.getInstance()
    private  var email:String=""
    private  var password:String=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


       binding.loginBtn.setOnClickListener{
           closeSoftKeyboard(requireContext(), binding.loginBtn)

           binding.loginProgressBar.visibility = View.VISIBLE
           email = binding.loginEmailTxt.text.toString()
            password = binding.loginPassText.text.toString()
           Log.e("qwerty",email+" +  "+password)

           if( TextUtils.isEmpty(email) == false && TextUtils.isEmpty(password) == false) {
               ref.signInWithEmailAndPassword(
                  email, password
               ).addOnSuccessListener {
                   binding.loginProgressBar.visibility = View.GONE
                   view?.let { Snackbar.make(it,"Logged In", Snackbar.LENGTH_SHORT).show() }

                    val i  = Intent(requireContext(),SplashScreen::class.java)
                   startActivity(i)
                   requireActivity().finish()
               }
                   .addOnFailureListener{e->
                       binding.loginProgressBar.visibility = View.GONE
                       view?.let { Snackbar.make(it,"Login Failed ${e.message}",Snackbar.LENGTH_SHORT).show() }

                   }
           }
           else{
               binding.loginProgressBar.visibility = View.GONE
               Snackbar.make(view,"Please Enter the Details", Snackbar.LENGTH_SHORT).show()
               }
       }
        binding.loginRegisterBtn.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

    }
    private fun closeSoftKeyboard(context: Context, v: View) {
        val iMm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        iMm.hideSoftInputFromWindow(v.windowToken, 0)
        v.clearFocus()
    }
}