package com.sv.skoreboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.sv.skoreboard.databinding.ActivitySkoreboardcBinding

class Skoreboardc : AppCompatActivity() {
    lateinit var binding: ActivitySkoreboardcBinding
    var target=0
    var overs=0
    var wickets=0
    var run =0
    var balls=0
    var cover=0
    var cwicket=0
    var crr:Double=0.00
    var team="BTeam"
    var bteam="Ateam"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySkoreboardcBinding.inflate(layoutInflater)
        setContentView(binding.root)
        target= intent?.extras?.getInt("total")!!+1
        overs=intent?.extras?.getInt("overs")!!
        wickets=intent?.extras?.getInt("wickets")!!
        team=intent?.extras?.getString("teamname").toString()
        bteam=intent?.extras?.getString("bteamname").toString()
        binding.Teamname.text=team
        binding.sbcscore.text="$run | $cwicket"
        binding.sbcover.text="$cover.$balls of $overs"
        binding.sbcrunrate.text="$crr"
    }
    fun bscore(x:Int){
        if(run>=target){
            binding.status.text="$team wins"
        }
        else if(cwicket==wickets){                           //Check All out
            Toast.makeText(this,"All out!", Toast.LENGTH_SHORT).show()
            binding.status.text="$bteam wins"
        }
        else if(cover==overs){                          //Check Overs
            Toast.makeText(this,"Innings Over", Toast.LENGTH_SHORT).show()
            binding.status.text="$bteam wins"
        }
        else if(binding.sbcwicket.isChecked && binding.sbcwide.isChecked) {      //wide+wicket
            run += x + 1
            cwicket += 1
        }
        else if(binding.sbcwicket.isChecked) //Wicket
        {
            if(binding.sbcnoball.isChecked || binding.sbcwide.isChecked){ //wicket +noball|wide
                run+=x+1
                cwicket+=1
            }
            else {
                run += x
                cwicket += 1
                if (balls == 5) {
                    cover += 1
                    balls = 0
                } else {
                    balls += 1
                }
            }
        }
        else if(binding.sbcwide.isChecked) //Wide
        {   run += x+1
        }
        else if(binding.sbcnoball.isChecked) //No Ball
        {
            run+= x+1
        }

        else if(binding.sbcbye.isChecked){ //bye
            run+=x
            if(balls==5)
            {cover+=1
                balls=0}
            else{balls+=1}
        }
        else{                           //normal
            run+=x
            if(balls==5)
            {cover+=1
                balls=0}
            else{balls+=1}
        }
        crr=(run.toDouble()/(cover.toDouble()+(balls.toDouble()/6)))
        binding.sbcscore.text="$run | $cwicket"
        binding.sbcover.text="$cover.$balls of $overs"
        binding.sbcrunrate.text="$crr"
        binding.sbcwide.isChecked=false
        binding.sbcnoball.isChecked=false
        binding.sbcbye.isChecked=false
        binding.sbcwicket.isChecked=false
        if(run>=target){
            binding.status.text="$team wins"
        }
        else if(cwicket==wickets){                           //Check All out
            Toast.makeText(this,"All out!", Toast.LENGTH_SHORT).show()
            binding.status.text="$bteam wins"
        }
        else if(cover==overs){                          //Check Overs
            Toast.makeText(this,"Innings Over", Toast.LENGTH_SHORT).show()
            binding.status.text="$bteam wins"
        }

    }
    fun bfour(view: View) {
        bscore(4)
    }

    fun btwo(view: View) {
        bscore(2)
    }
    fun bone(view: View) {
        bscore(1)
    }
    fun bzero(view: View) {
        bscore(0)
    }
    fun bsix(view: View) {
        bscore(6)
    }
    fun bthree(view: View) {
        bscore(3)
    }
    fun bfive(view: View) {
        bscore(5)
    }

    fun bcbwide(view: View) {
        if (binding.sbcnoball.isChecked || binding.sbcbye.isChecked) {
            binding.sbcwide.isChecked = false
        }
    }

    fun bcbnoball(view: View) {
        if(binding.sbcwide.isChecked){
            binding.sbcnoball.isChecked=false
        }
    }

    fun bcbbye(view: View) {
        if(binding.sbcwide.isChecked){
            binding.sbcwide.isChecked=false
        }
    }

}