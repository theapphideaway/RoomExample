package com.example.roomexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainFragment : Fragment() {

    val names = ArrayList<String>()

    private lateinit var submitBTN: Button
    private lateinit var nameET: EditText
    private lateinit var namesRV: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false).apply{
            submitBTN = findViewById(R.id.submit_btn)
            nameET = findViewById(R.id.name_et)
            namesRV = findViewById(R.id.names_rv)

            val adapter = NamesAdapter(names)
            namesRV.adapter = adapter
            namesRV.layoutManager = LinearLayoutManager(requireContext())


            submitBTN.setOnClickListener{
                names.add(nameET.text.toString())
                adapter.notifyDataSetChanged()
                nameET.text.clear()
            }
        }
    }
}