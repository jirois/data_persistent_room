package com.jinncyapps.roomdata.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.jinncyapps.roomdata.R
import com.jinncyapps.roomdata.data.UserViewModel
import com.jinncyapps.roomdata.databinding.FragmentListBinding


class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentListBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_list, container, false)

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


        return binding.root
    }

}