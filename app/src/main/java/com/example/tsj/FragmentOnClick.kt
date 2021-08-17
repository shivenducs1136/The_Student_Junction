package com.example.tsj

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.tsj.databinding.FragmentHomeBinding
import com.example.tsj.databinding.FragmentOnClickBinding
import com.squareup.picasso.Picasso


class FragmentOnClick : Fragment() {

    private lateinit var binding: FragmentOnClickBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_on_click,container,false)
        return binding.root
     }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var bundleurl=this.arguments
        var imgurl:String=""
        var title:String=""
        var publish:String=""
        var author:String=""
        var content:String=""

        if (bundleurl!=null){
            imgurl=bundleurl.getString("imgurl").toString()
            title=bundleurl.getString("title").toString()
            publish=bundleurl.getString("publish").toString()
            author=bundleurl.getString("author").toString()
            content=bundleurl.getString("content").toString()

        }
        binding.onclickAuthor.text = "Author : " + author
        binding.onclickContent.text = content
        Picasso.with(context)
            .load(imgurl)
            .into(binding.onclickImg)
        binding.onclickPublishedat.text = "At: " + publish
        binding.onclickTitle.text = title

    }
}