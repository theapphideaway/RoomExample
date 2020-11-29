package com.example.roomexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainFragment : Fragment() {

    val names = ArrayList<String>()

    var arrayListNames = ArrayList<Name>()

    private lateinit var submitBTN: Button
    private lateinit var nameET: EditText
    private lateinit var namesRV: RecyclerView
    private lateinit var adapter: RecyclerView.Adapter<NamesAdapter.NamesViewHolder>

    private val nameViewModel: NameViewModel by viewModels {
        NameViewModelFactory((requireActivity().application as RoomExample).repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false).apply{
            submitBTN = findViewById(R.id.submit_btn)
            nameET = findViewById(R.id.name_et)
            namesRV = findViewById(R.id.names_rv)



            nameViewModel.allNames.observe(viewLifecycleOwner){names ->
                names.let {
                    adapter = NamesAdapter(it)
                    namesRV.adapter = adapter
                    namesRV.layoutManager = LinearLayoutManager(requireContext())
                }
            }


            submitBTN.setOnClickListener{
                val name = Name(nameET.text.toString())
                nameViewModel.insert(name)
                nameET.text.clear()
            }
        }
    }
}