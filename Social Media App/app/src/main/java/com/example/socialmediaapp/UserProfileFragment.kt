package com.example.socialmediaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.socialmediaapp.databinding.FragmentUserProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserProfileFragment : Fragment() {
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!
    private val args: UserProfileFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        fetchUserProfile(args.userId)

        return binding.root
    }
    private fun fetchUserProfile(userId: Int) {
        RetrofitClient.instance.getUserProfile(userId).enqueue(object : Callback<UserProfile> {
            override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
                if (response.isSuccessful) {
                    response.body()?.let { userProfile ->
                        // Populate the UI with user profile data
                        binding.textViewName.text = userProfile.name
                        binding.textViewEmail.text = userProfile.email
                        binding.textViewPhone.text = userProfile.phone
                        binding.textViewWebsite.text = userProfile.website
                        binding.textViewCompany.text = userProfile.company.name
                        binding.textViewAddress.text = "${userProfile.address.street}, ${userProfile.address.city}"
                    }
                }
            }
            override fun onFailure(call: Call<UserProfile>, t: Throwable) {
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
