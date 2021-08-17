package com.example.tsj

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.Adapter.FeedAdapter
import com.example.tsj.Repository.Repository
import com.example.tsj.Viewmodel.MainViewModel
import com.example.tsj.Viewmodel.MainViewModelFactory
import com.example.tsj.databinding.ActivityMainBinding
import com.example.tsj.databinding.FragmentHomeBinding
import com.example.tsj.model.Feeds

class Home : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MainViewModel
    private val feedAdapter:FeedAdapter by lazy{FeedAdapter(requireContext())}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getFeed(requireContext())
        viewModel.myResponse.observe(viewLifecycleOwner, Observer {
                feedAdapter.setStateWiseTracker(it.articles!!)
            binding.homeProgress.visibility = View.GONE

            Log.d("BOLTy","success"+it.toString())

        })

        viewModel.showProgress.observe(viewLifecycleOwner, Observer {
            if (it)
                binding.homeProgress.visibility = View.VISIBLE
            else
                binding.homeProgress.visibility = View.GONE
        })
        val recyclerView: RecyclerView = view.findViewById(R.id.recview)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView.adapter = feedAdapter


    }


}