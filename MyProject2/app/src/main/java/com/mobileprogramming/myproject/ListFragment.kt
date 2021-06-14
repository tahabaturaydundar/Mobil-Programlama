package com.mobileprogramming.myproject


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ListFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        var diziler = ArrayList<String> ()
        diziler.add("The Lost")
        diziler.add("Revolution")
        diziler.add("The 100")
        diziler.add("Breaking Bad")
        diziler.add("Prison Break")
        diziler.add("Limitless")
        diziler.add("Dark")
        diziler.add("The Rain")

        var adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, diziler)

        view.listView.adapter = adapter


        view.listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

        }



        return view
    }



    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}