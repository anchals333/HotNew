package com.anchal.hotnew.home.newtab

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anchal.hotnew.database.AppDatabase
import com.anchal.hotnew.databinding.FragmentMainBinding
import com.anchal.hotnew.home.HomeViewModel
import com.anchal.hotnew.utils.DialogUtility
import com.anchal.hotnew.utils.Utility


/**
 * A placeholder fragment containing a simple view.
 */
class NewFragment : Fragment() {

    private lateinit var adapter: NewAdapter
    private lateinit var database: AppDatabase
    private lateinit var utility: Utility
    private lateinit var binding: FragmentMainBinding
    private lateinit var dialogUtility: DialogUtility
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var activityContext: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activityContext = activity as AppCompatActivity
        database = AppDatabase.getDatabase(activityContext)
        utility = Utility.getInstance(activityContext)
        dialogUtility = DialogUtility.getInstance()!!

        homeViewModel.getAllNewList().observe(activityContext, Observer {
            if (it.isNotEmpty()){
                homeViewModel.newList.value = it as MutableList<NewModel>?
                setUpAdapter()
            } else {
                if (utility.isNetworkConnected())
                    getHotList()
            }
        })

        binding.inputSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                filter(p0.toString());
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(): NewFragment {
            return NewFragment()
        }
    }

    private fun getHotList() {
        dialogUtility.showProgressDialog(activityContext,"Please wait...")
        homeViewModel.getNewList(activityContext).observe(activityContext, Observer {
            if (it.first) {
                homeViewModel.newList.value = it.second!!.data
                setUpAdapter()
                homeViewModel.insertNew()
                Toast.makeText(activityContext, "Success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activityContext, "Error", Toast.LENGTH_SHORT).show()

            }

            dialogUtility.hideProgressDialog()

        })
    }

    private fun setUpAdapter() {
        adapter = NewAdapter(
            context!!,
            homeViewModel.newList.value!!,
            homeViewModel.newList.value!!
        )
        binding.recyclerview.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerview.adapter = adapter


        adapter.putOnBottomReachedListener(object : NewAdapter.OnBottomReachedListener{
            override fun onBottomReached(position: Int) {
                Log.d("Bottom reached : ", "$position")
//                viewModel.loading = true
//                viewModel.page.value  =  viewModel.page.value!! + 1
//                if (viewModel.maxPages.value!! >= viewModel.page.value!!){
//                    if (utility.isNetworkConnected()){
//                        viewModel.getLocation(activityContext).observe(activityContext, Observer {
//                            if (it.first) {
//                                viewModel.maxPages.value = it.second!!.pages.toInt()
////                                    viewModel.list.value = it.second!!.data
//                                adapter.updateList(it.second!!.data)
////                                    viewModel.loading = true
//                            }
//                        })
//
//                    }
//
//                }
            }
        })
    }

    private fun filter(text: String) { //new array list that will hold the filtered data
        val filterdNames: ArrayList<NewModel> = ArrayList()
        //looping through existing elements
        for (s in homeViewModel.newList.value!!) { //if the existing elements contains the search input
            if (s.data!!.name.toLowerCase().contains(text.toLowerCase())
                || s.data!!.title.toLowerCase().contains(text.toLowerCase())
                || s.data!!.name.toLowerCase().contains(text.toLowerCase())) { //adding the element to filtered list
                filterdNames.add(s)
            }
        }
        //calling a method of the adapter class and passing the filtered list
        adapter.filterList(filterdNames)
    }

}