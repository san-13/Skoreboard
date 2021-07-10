package com.sv.skoreboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sv.skoreboard.databinding.ActivityMatchSettingsBinding

class MatchSettings : AppCompatActivity() {
    lateinit var binding: ActivityMatchSettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun MatchStart(view: View) {
            val intent = Intent(this, Scoreboard::class.java)
            intent.putExtra("overs", binding.overs.text.toString())
            intent.putExtra("wickets", binding.wicktet.text.toString())
            if (binding.bhome.isChecked) {
                intent.putExtra("teamname", binding.homeTeam.text.toString())
                intent.putExtra("bteamname",binding.awayTeam.text.toString())
            } else if (binding.baway.isChecked) {
                intent.putExtra("teamname", binding.awayTeam.text.toString())
                intent.putExtra("bteamname",binding.homeTeam.text.toString())
            }

            startActivity(intent)
    }
}