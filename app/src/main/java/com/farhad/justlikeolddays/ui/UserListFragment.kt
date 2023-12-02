package com.farhad.justlikeolddays.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.farhad.justlikeolddays.adapter.UserAdapter
import com.farhad.justlikeolddays.api.ApiService
import com.farhad.justlikeolddays.databinding.FragmentUserListBinding
import com.farhad.justlikeolddays.repository.UserRepository
import com.farhad.justlikeolddays.viewmodel.UserListViewModel
import com.farhad.justlikeolddays.viewmodel.UserListViewModelFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory.*

/**
 * A simple [Fragment] subclass.
 * Use the [UserListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserListFragment : Fragment() {

    private lateinit var userListViewModel: UserListViewModel
    private lateinit var userAdapter: UserAdapter

    val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
        .build()
    val apiService = retrofit.create(ApiService::class.java)
    val userRepository = UserRepository(apiService)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentUserListBinding.inflate(inflater, container, false)

        userAdapter = UserAdapter()
        binding.recyclerView.adapter = userAdapter

        userListViewModel = ViewModelProvider(this, UserListViewModelFactory(userRepository))[UserListViewModel::class.java]

        userListViewModel.users.observe(viewLifecycleOwner){
            userAdapter.submitList(it)
        }

        return binding.root
    }
}