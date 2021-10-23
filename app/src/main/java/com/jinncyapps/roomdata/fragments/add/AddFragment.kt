package com.jinncyapps.roomdata.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jinncyapps.roomdata.R
import com.jinncyapps.roomdata.data.User
import com.jinncyapps.roomdata.data.UserViewModel
import com.jinncyapps.roomdata.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var binding: FragmentAddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = DataBindingUtil.inflate(inflater,R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        binding.btnAdd.setOnClickListener {
            addUserToDatabase()
        }


        return binding.root
    }

    private fun addUserToDatabase(){
        val firstName: String = binding.etFirstName.text.toString()
        val lastName: String = binding.etLastName.text.toString()
        val age: String = binding.etAge.text.toString()

        if (inputValid(firstName, lastName, age)){
           val user = User(0,firstName,lastName, Integer.parseInt(age) )

            mUserViewModel.addUser(user)

            Toast.makeText(
                context,
                "User successfully added!",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        } else {
            Toast.makeText(
                context,
                "Please fill out all fields",
                Toast.LENGTH_LONG
            ).show()
        }
    }


    private fun inputValid(firstName: String, lastName: String, age: String): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(age))
    }
}