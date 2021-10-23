package com.jinncyapps.roomdata.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jinncyapps.roomdata.R
import com.jinncyapps.roomdata.databinding.FragmentAddBinding.inflate
import com.jinncyapps.roomdata.databinding.FragmentListBinding.inflate
import com.jinncyapps.roomdata.databinding.FragmentUpdateBinding
import com.jinncyapps.roomdata.model.User
import com.jinncyapps.roomdata.viewModel.UserViewModel
import java.util.zip.Inflater


class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var binding: FragmentUpdateBinding
    private lateinit var mUserViewModel:UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_update, container, false)

        binding.etUpdateFirstName.setText(args.currentUser.firstName)
        binding.etUpdateLastName.setText(args.currentUser.lastName)
        binding.etUpdateAge.setText(args.currentUser.age.toString())

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnUpdate.setOnClickListener {
            updateUser()
        }

        //setmenu
        setHasOptionsMenu(true)

        return binding.root
    }


    private fun updateUser(){
        val firstName: String = binding.etUpdateFirstName.text.toString()
        val lastName: String = binding.etUpdateLastName.text.toString()
        val age: String = binding.etUpdateAge.text.toString()

        if (inputValid(firstName, lastName, age)){
            val updateUser = User(args.currentUser.id, firstName,lastName, Integer.parseInt(age) )

            mUserViewModel.updateUser(updateUser)

            Toast.makeText(
                context,
                "Updated successfully!",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
           mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),
                    "Successfully removed: ${args.currentUser.firstName}",
                    Toast.LENGTH_LONG
                ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Delete ${args.currentUser.firstName}")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}")
        builder.create().show()

    }
}