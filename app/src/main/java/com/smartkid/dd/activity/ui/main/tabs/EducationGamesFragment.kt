package com.smartkid.dd.activity.ui.main.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartkid.dd.R
import com.smartkid.dd.activity.ui.category.IDENTIFICATION


class EducationGamesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val id = this.activity?.intent?.getStringExtra(IDENTIFICATION)
        if(id.equals("6284eededaac815be2cbe1dd")) {
            //instrument
            return inflater.inflate(R.layout.fragment_instrument, container, false)
        } else if(id.equals("6284efbddaac815be2cbe1de")){
            //animaux
            inflater.inflate(R.layout.fragment_animal_game, container, false)
            return AnimalGameFragment(this).onCreateView(inflater, container, null)
        } else if(id.equals("6284efcfdaac815be2cbe1df")){
            //number-letter
            return inflater.inflate(R.layout.fragment_letter_number, container, false)
        } else if(id.equals("6284efdfdaac815be2cbe1e0")) {
            //Pays
            return inflater.inflate(R.layout.fragment_pays, container, false)
        }
        return inflater.inflate(R.layout.fragment_pays, container, false)
    }
}