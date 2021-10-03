package com.example.bookstore.ui.MainViewModel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.R
import com.example.bookstore.databinding.FragmentMainBinding
import com.example.bookstore.model.data.BookShopItem
import com.example.bookstore.ui.Adapter.MainRecyclerView
import com.example.bookstore.ui.Adapter.onBookClickListener


class MainFragment : Fragment(), onBookClickListener {

    private lateinit var binding: FragmentMainBinding
    val mainRecyclerView: MainRecyclerView by lazy { MainRecyclerView() }
    private lateinit var mainViewModel: MainViewModel



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.mainRecyclerView.adapter = mainRecyclerView
        mainRecyclerView.listener = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        getDataFromAPI()

        mainViewModel.bookLiveData.observe(viewLifecycleOwner,
        { if (it != null) {
                mainRecyclerView.setData(it)
                binding.progressBar.visibility = View.GONE
            }
        else {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        })

    }






    fun getDataFromAPI(){
        mainViewModel.getAllBookFromAPI()
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onBookClick(book: BookShopItem) {
        Toast.makeText(context, "the book name is ${book.name} ", Toast.LENGTH_SHORT).show()
    }
}